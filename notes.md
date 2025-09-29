
### Folder Structure 
- Model (Data model)
- Repository (to perform DB operations)
- Services (contains business login)
- Controllers (API endpoints)

### Connection  to DB (mongo compass)
-  add in pom the dependency 
- add the uri and db name in app.prop

### Model Class
- actually used to define your schema
- @Document for  annotation to specify the collection name
- @Id to mark a field as a primary key, in our case it wil mark _id to id
- If collection dose not exists mongo wil create it
- if not marked @Id it will still create it
- use Lombok @Data for getters and setters.

### Repository Interface
- why ? cause it auto generates the implementation at the runtime,
u dont have to write pre-existing built-in functions by mongo and also
u can add u own custom mongo data extract functions like saveAll(), findAll(),
deleteById(), etc
- If u declare it as an interface spring will provide the class for it.
Its called **REPOSITORY ABSTRACTION**, u focus on what you want and not how its done
- why we used interface and extended as it provides a prebuilt functions.
- Custom finder names must match model field names (e.g. findByUserId maps to userId) must be added at this level.

### Service Class
- Services separate business rules from controllers and data access. This keeps controllers skinny (only handle HTTP) and repositories skinny (only DB).
- @Service annotation to define them.
- Write business logic here and use repositories for DB operations.
- use proper controllers and correct function calls.
- Throw exceptions correctly and function names accordingly.
- Whenever u call findById() store it in Optional<T> and then use .get() to extract that object. 

### Industry-level best practices you should adopt now


**Code style & architecture**

* Use **packages per layer**: `controller`, `service`, `repository`, `model`, `dto`, `exception`, `config`.
* Use **constructor injection** and mark dependencies `final`.
* Prefer **small focused services** (SRP).

**DTOs & Controllers**

* Never expose DB entity directly in controller responses. Use **DTOs** for API contracts.
* Use `@Valid` + validation annotations (`@NotBlank`, `@Min`, etc.) on request DTOs.

**Error handling**

* Create custom exceptions (e.g. `ItemNotFoundException`, `OutOfStockException`, `PaymentMismatchException`).
* Use a **global handler** (`@ControllerAdvice`) to convert exceptions into consistent JSON error responses with appropriate HTTP codes.

**Atomicity & Concurrency (very important for buy flow)**

* Buying reduces stock → **race conditions** possible if 2 users buy same item simultaneously.
* Fixes:

    * Use **atomic DB operation** (update with conditional: decrement if quantity >= purchaseQty) — avoids read-modify-write races.
    * Or use **optimistic locking** (`@Version`) or **Mongo transactions** (if you run a replica set). Choose solution based on complexity.
* Never rely on “read, check, write” without atomicity for stock changes.

**Validation & Sanity checks**

* Validate input DTOs early (price > 0, quantity > 0).
* Verify business invariants in service (e.g., totalPricePaid >= expectedPrice).

**Logging & Observability**

* Use `slf4j` (`private static final Logger log = LoggerFactory.getLogger(...)`).
* Log at appropriate levels (info for major actions, warn for unexpected but handled, error for exceptions).

**Testing**

* Write **unit tests** for services (mock repositories).
* Add **integration tests** for controllers and DB (use embedded Mongo or Testcontainers for real Mongo).

**Security & Roles**

* Later: add roles (`ADMIN` vs `USER`) and secure endpoints (JWT). For now, plan endpoints with role separation.

**Naming & HTTP semantics**

* Use RESTful endpoints and correct HTTP codes:

    * `POST /admin/items` → **201 Created** (return Location header)
    * `GET /items` → **200 OK**
    * `GET /items/{id}` → **200 OK** or **404 Not Found**
    * `PUT /admin/items/{id}` → **200 OK** (or 204)
    * `DELETE /admin/items/{id}` → **204 No Content**
    * `POST /user/orders` (or `/user/buy`) → **201 Created**
* For updates: decide between `PUT` (full update) and `PATCH` (partial).

**Clean error responses**

* Return a structured JSON error:

  ```json
  { "timestamp": "...", "status": 404, "error": "Not Found", "message": "Item not found", "path": "/items/123" }
  ```
