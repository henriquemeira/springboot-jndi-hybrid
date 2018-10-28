# springboot-jndi-hybrid
Spring Boot 2 Application with hybrid configuration JNDI and URL/USERNAME/PASS

## Running

mvn springboot:run

## Package

mvn clean package

### Running WAR package file

java -jar target/springboot-jndi.war

### Runing WAR package file changing database configuration (Example: PostgreSQL)

java -Dspring.datasource.driver-class-name=org.postgresql.Driver -Dspring.datasource.url=jdbc:postgresql://serverhostname:5432/dbname -Dspring.datasource.username=UserName -Dspring.datasource.password=UserPass -jar target/springboot-jndi.war

## Deploy to container server

### Tomcat

Before package the WAR file, change the spring.datasource.jndi-name=jdbc/MyJndiName property value as you need.

Edit ${TOMAT_HOME}/conf/server.xml and add the bellow between <GlobalNamingResources/> tag:

    <Resource auth="Container"
              name="jdbc/MyJndiName"
              driverClassName="DriverName"
              maxActive="20"
              maxIdle="0"
              maxWait="10000"
              password="UserPass"
              username="UserName"
              type="javax.sql.DataSource"
              url="jdbc:postgresql://serverhostname/dbname"/>

Edit ${TOMAT_HOME}/conf/context.xml and add the bellow between <Context/> tag:

    <ResourceLink auth="Container" name="jdbc/MyJndiName" global="jdbc/MyJndiName" type="javax.sql.DataSource" />

Where:

  MyJndiName = the jndi name
  DriverName = JDBC driver class name
  UserName = Database user name
  PassName = Database user password
  Serverhostname = Server host name and port
  Dbname = Database Name

Package the WAR file and deploy to Tomcat webapp directory ${TOMCAT_HOME/webapps


