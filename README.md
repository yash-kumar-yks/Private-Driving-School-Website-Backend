# Private-Driving-School-Website-Backend
application.properties->{ 

spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update

}


API ENDPOINTS:
GET http://localhost:8080/api/blogs
GET http://localhost:8080/api/users/john.doe@example.com/blogs
POST http://localhost:8080/api/users
GET http://localhost:8080/api/email/{emailname}
