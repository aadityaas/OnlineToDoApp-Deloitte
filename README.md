# OnlineToDoApp-Deloitte

This is a spring boot stand-alone application, developed to manage simple to do list online.

Once run command is executed on command prompt, access the application on web browser at: http://localhost:8082/login


**Notes:**
1.) This application is using in-memory database to persist the records, so every time the application is shutdown all previously stored records will be lost.
2.) There are 3 in-memory user list configured to support multiple user. Below are username and password:
	Username: pwd	Password: pwd123
	Username: user1	Password: user1Pass
	Username: user2	Password: user2Pass

**Pre-requisite to run this application:**

You need to have maven installed on your machine on which you want to run this application.
Follow steps given at https://maven.apache.org/install.html to install maven(if required)

**Steps to run application:**

1.) Open command prompt in administrative mode

2.) Navigate to the project root directory(where pom.xml resides).
	e.g. :  cd  C:\Software\Workspace-Eclipse2020\OnlineToDoApp-Deloitte
	
3.) run maven command <mvn clean package>
	This command will:
	- remove any pre existing directory
	- run Test
	- package executable jar and place that under target folder
	
If you face any issue with the test you can skip the test by below command:
	<mvn clean install -Dmaven.test.skip=true>

4.) There are two options to run this application now:
    - Java run option:
      <java -jar target/OnlineToDoApp-Deloitte-0.0.1-SNAPSHOT.jar>

	- Maven run option(preferred):
      <mvn spring-boot:run> 
      
All steps as mentioned in requirement document are functional. 
I have taken help from Internet for standard configuration(e.g. date picker, bootstrap css and spring security configuration)

I preferred to chose SpringBoot to develop this application as it will remove a lot of over head to deploy and run the application.
You can import this project as existing maven project in your eclipse and run as 'Spring Boot App' or
Run via command prompt as instructed above and access via web browser.

I have followed Spring MVC architecture to develop this application to separate out all layers(Controller, Service, Repository).
SpringBoot provides default support to hibernate ORM tool so, we are not writing any query but using the already existing JPARepository methods to store, retrieve, delete records in db.

You can access h2 database at: http://localhost:8082/h2-console/ and check the database entries as well.
Provide below details to connect to h2 db:
 - Driver class = org.h2.Driver
 - JDBC URL = jdbc:h2:mem:deloitte
 - Username = sa
 - Password = <Leave this field blank>

I have not implemented extensive unit tests but it can be extended from already existing ones.

