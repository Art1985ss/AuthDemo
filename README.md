# AlterKC
AlterKC app is designed as Accenture bootcamp student project. 
Main purpose of this app is to improve the way users are taking knowledge checks in bootcamp.
Application is designed in a way that there are two types of users. First type of user is test administrator,
who can create tests and allow regular users to access them. 
Regular users are able to take tests.

### Test Administrator
Test administrator for AlterKC is the only user with test administration rights. 
Administrator password is set by default in the database.
After login in administrator is redirected to test administration page. 
From this page administrator is able to perform following tasks:
1. View list of all registered users, their scores in each individual test and total score. 
2. Score for each test is calculated in a such way, that each correct answer for question is 1 point,
wrong answer is 0 points. And test score is (sum of points) / (answer count).
Total score = (individual test score) / (total test count).
3. Test administrator is able to remove users.
4. New tests can be added to the test management table. On test creation administrator needs to set:
    - test name
    - test duration (in minutes)
4. After test is created administrator is redirected to test management page. Here it is possible to add questions to the test.
Before adding question you need to set count of possible answers. 
In question adding form you provide question text, possible answers and set corresponding row letter for correct answer.
5. When tests are set up, for students are to take test, it has to be enabled by administrator from
tests management table.

### Regular User
Regular users can take tests and review their scores. Main functionality for regular user:
1. New users can register, existing users after login in are redirected to user's main page.
2. Users are able to see results for tests they have taken.
3. When test administrator enables tests, link to the test enabled appears on users page.
4. Users provide answers for tests and submit whole test when finished. There is countdown timer
on top of the page. When time is up, test form is submitted automatically, saving users current progress.
5. When test is submitted student is redirected to page with correct answers.
6. Regular user is prevented from taking test again if he has submitted it once.

### Deployment of the application
In order to use this application no local computer, preferable way is to build it from source, which can be downloaded from
https://github.com/Art1985ss/AuthDemo.git 
Main steps for setting up application would be:
1. Application uses MySQL database as persistent data storage, therefore MySQL server needs to be set up before (more info here https://dev.mysql.com/doc/mysql-getting-started/en/).
2. User needs to create database named 'test' on mysql server.
3. Following parameters needs to be configured in applications /src/main/application.properties file:
    - spring.datasource.url (in case of local server jdbc:mysql://localhost:3306/test).
    - spring.datasource.username (username for connecting to database)
    - spring.datasource.password (password for connecting to database)
    - flyway.user (same username as described before)
    - flyway.password (same password as described before)
    - flyway.url (same database url)
4. Necessary database table structure for the application is created automatically on first initialization of application 
using flyway database migration tool. 
5.By default database contains one user for test administration - username "admin" and password "admin". This
can be changed either in database itself or in a flyway migrations file located in /src/main/resources/db.migration.
6. Application is build using gradle build tool (https://gradle.org/). 
7. After executing gradle build war file is generated, which can be run on computer 
executing from terminal using $java -jar filename.war (where filename is actual name of the file), jre has to be installed first. 
8. After starting application in commandline it is possible to open it in the local network with corresponding 
local network ip address and port 5000.
