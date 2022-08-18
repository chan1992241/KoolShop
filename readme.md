# How to setup this project
Setup database connection to PostgresSQL
1. Create database called "classicmodels"
2. Load data to classicmodels database.
```
psql -U postgres classicmodels < "path to classicmodels_dataset.sql""
```
3. Setup Wildfly server datasource
* remember to change your postgres user and password
```
<datasource jndi-name="java:/classicmodels" pool-name="classicmodelsPool">
    <connection-url>jdbc:postgresql://localhost:5432/classicmodels</connection-url>
    <driver-class>org.postgresql.Driver</driver-class>
    <driver>postgresql</driver>
    <security>
        <user-name>Postgres user</user-name>
        <password>Postgres password</password>
    </security>
</datasource>
```
4. Change persistence xml database information
* change postgres user and password in properties tag
* change jta-data-source
```
<persistence-unit name="default">
        <jta-data-source>java:/classicmodels</jta-data-source>
        <class>com.example.assignment.model.entity.Customer</class>
        <class>com.example.assignment.model.entity.Employee</class>
        <class>com.example.assignment.model.entity.Office</class>
        <class>com.example.assignment.model.entity.Order</class>
        <class>com.example.assignment.model.entity.Orderdetail</class>
        <class>com.example.assignment.model.entity.OrderdetailId</class>
        <class>com.example.assignment.model.entity.Payment</class>
        <class>com.example.assignment.model.entity.PaymentId</class>
        <class>com.example.assignment.model.entity.Product</class>
        <class>com.example.assignment.model.entity.Productline</class>
        <properties>
            <property name="javax.persistence.jdbc.driver" value="org.postgresql.Driver"/>
            <property name="javax.persistence.jdbc.url" value="jdbc:postgresql://localhost:3306/ecommerce"/>
            <property name="javax.persistence.jdbc.user" value="postgres"/>
            <property name="javax.persistence.jdbc.password" value="jinyeeU"/>
        </properties>
    </persistence-unit>
```
