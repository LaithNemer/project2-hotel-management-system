# Hotel Management System

## Project Overview
This project implements a Hotel Management System designed to streamline operations for both hotel staff and guests. It provides functionalities for customers to search for available rooms, make reservations, check-in/check-out, and generate invoices. Admins can manage employees, room availability, housekeeping schedules, and oversee overall hotel operations.

### Students
- **Student 1**: Yazan Ramzi AL-Karazon (1181463)
- **Student 2**: Layth Nemer

## Project Resources

### Entities and Attributes

#### 1. User Entity
- **Description**: Represents both customers and admins with authentication details.
- **Attributes**:
  - `id`: Unique identifier for the user.
  - `username`: Username for user login.
  - `password`: Password for user login.
  - `email`: User's email address.
  - `phone`: User's phone number.
  - `age`: User's age.
  - `role`: Role assigned to the user (e.g., USER, ADMIN, CUSTOMER).
  - `admin`: Admin profile associated with the user.
  - `customer`: Customer profile associated with the user.

#### 2. Role Entity
- **Description**: Represents roles and permissions for users.
- **Roles**:
  - `USER`: Basic user role with no special permissions.
  - `ADMIN`: Admin role with permissions to manage hotel operations.
  - `CUSTOMER`: Customer role with permissions to manage reservations.
- **Attributes**:
  - `permissions`: Set of permissions associated with the role.
  - `authorities`: List of granted authorities derived from permissions.

#### 3. Admin Entity
- **Description**: Represents administrators managing hotel operations.
- **Attributes**:
  - `id`: Unique identifier for the admin.
  - `role`: Role of the admin.
  - `salary`: Salary of the admin.
  - `room`: List of rooms managed by the admin.
  - `user`: User profile associated with the admin.

#### 4. Customer Entity
- **Description**: Represents individuals who book rooms and stay at the hotel.
- **Attributes**:
  - `id`: Unique identifier for the customer.
  - `reservations`: List of reservations made by the customer.
  - `user`: User profile associated with the customer.

#### 5. Employee Entity
- **Description**: Represents hotel employees.
- **Attributes**:
  - `id`: Unique identifier for the employee.
  - `name`: Name of the employee.
  - `age`: Age of the employee.
  - `salary`: Salary of the employee.
  - `email`: Email of the employee.
  - `phone`: Phone number of the employee.
  - `password`: Password for employee login.
  - `role`: Role assigned to the employee.

#### 6. ChangePassword Entity
- **Description**: Represents the entity for changing passwords.
- **Attributes**:
  - `oldpassword`: The old password.
  - `newpassword`: The new password.
  - `confirmpassword`: Confirmation of the new password.

#### 7. Invoice Entity
- **Description**: Represents billing information associated with customer reservations.
- **Attributes**:
  - `id`: Unique identifier for the invoice.
  - `reservation`: Reservation linked to the invoice.
  - `totalprice`: Total amount to be paid.
  - `numberofroom`: Number of rooms included in the invoice.
  - `status`: Payment status (e.g., Paid, Unpaid).

#### 8. Permission Entity
- **Description**: Represents permissions for different roles.
- **Attributes**:
  - `permission`: Permission description.

#### 9. Reservation Entity
- **Description**: Represents a booking made by a customer.
- **Attributes**:
  - `id`: Unique identifier for the reservation.
  - `customer`: Customer making the reservation.
  - `rooms`: List of rooms reserved.
  - `checkInDate`: Date for check-in.
  - `checkOutDate`: Date for check-out.
  - `status`: Reservation status (e.g., confirmed, canceled).
  - `arrivalDate`: Arrival date.
  - `exitDate`: Exit date.
  - `invoice`: Invoice generated for the reservation.

#### 10. Room Entity
- **Description**: Represents physical rooms available in the hotel.
- **Attributes**:
  - `roomNumber`: Unique identifier for the room.
  - `status`: Current status of the room (e.g., available, occupied).
  - `type`: Type of room (e.g., suite, standard).
  - `capacity`: Maximum number of occupants.
  - `price`: Cost per night.
  - `description`: Description of the room.
  - `availability`: Availability status.
  - `size`: Size of the room in square feet.
  - `reservation`: Reservation associated with the room.
  - `task`: Task assigned to the room.
  - `admin`: Admin responsible for the room.

#### 11. Scheduling Entity
- **Description**: Manages scheduling and tracking of housekeeping tasks.
- **Attributes**:
  - `id`: Unique identifier for the scheduling.
  - `status`: Current status of scheduling.
  - `note`: Additional notes related to scheduling.
  - `employeeName`: Name of the employee assigned to scheduling.
  - `admin`: Admin responsible for scheduling.
  - `tasks`: List of tasks scheduled.

#### 12. Task Entity
- **Description**: Represents tasks assigned to rooms by housekeeping.
- **Attributes**:
  - `id`: Unique identifier for the task.
  - `startDate`: Date when the task starts.
  - `endDate`: Date when the task ends.
  - `type`: Type of task.
  - `scheduling`: Scheduling associated with the task.
  - `room`: Room assigned to the task.

 ## ER Diagram

 

## Build and Run Instructions

### Prerequisites
- Java Development Kit (JDK) 11 or higher
- Apache Maven
- Docker (for containerization)

### Steps

1. **Clone the repository**:
   ```bash
   git clone https://github.com/LaithNemer/project2-hotel-management-system.git
   cd project2-hotel-management-system
   ```

2. **Build the project**:
   ```bash
   mvn clean package
   ```

3. **Run the application**:
    - **Using Maven**:
      ```bash
      mvn spring-boot:run
      ```
    - **Using Docker**:
      ```bash
      docker-compose up
      ```
      This will pull the Docker image from DockerHub (replace with your DockerHub repository link) and start the application.

4. **Access the application**:
    - Once the application is running, you can access the endpoints via `http://localhost:8080` (replace with your application's port if different).

## API Documentation

The APIs are documented using OpenAPI Specification (OAS 3.1.0). You can explore and interact with the APIs using Swagger UI:

- **Swagger UI**: Access the API documentation and test endpoints interactively using Swagger UI at [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html).

## Postman Collection
You can import the Postman collection by running the application and importing this link in Postman (`/v3/api-docs`), which includes a complete testing scenario for the system interface.

## Database Initialization
The database structure is automatically initialized upon application startup. Initial data is populated to enable the use of APIs without issues.

## Data Validation and Exception Handling
Data validation and proper exception handling are implemented to ensure data integrity and error-free operation of the application.

## Security
APIs are secured using JWT (JSON Web Token) for authentication. Certain APIs are accessible publicly, while others require authenticated access based on user roles (customer, admin).

## DockerHub

To pull the Docker image for the Hotel Management System project (version 3) from DockerHub, use the following command:

```bash
docker pull azankarazon/project2-hotel-management-system:tagname
```

## Learning Outcomes
Through this project, we have learned about effective database design, RESTful API development using Spring Boot, Docker containerization, API documentation with OpenAPI, and implementing security features like JWT authentication.
