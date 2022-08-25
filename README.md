# springbootdemo

These are practice code examples based on Ken Kousen's training material for spring-boot
https://github.com/kousen?tab=repositories


Some TODOs:  
If you want to see the H2 console, add the Spring DevTools dependency and the Web dependency to your project.  
Then, to be sure to see the proper URL for the database (assuming you don’t set it in application.properties), add the following log level:  
logging.level.org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration=debug  

Then you can go to "http://localhost:8080/db-console" and log in with the URL shown in the log, the user name "sa", and no password.

Once the tests are running, add two dependencies to the Gradle build file:  
one for the **Spring Data Rest project** (which will expose the data via a REST interface)   
and for the **HAL browser**, which will give us a convenient client to use.  
implementation 'org.springframework.boot:spring-boot-starter-data-rest'  
implementation 'org.springframework.data:spring-data-rest-hal-explorer'  

After rebuilding the project, start up the application (using the class with the main method) and navigate to http://localhost:8080    
Spring will insert the HAL browser at that point to allow you to add, update, and remove individual elements.
