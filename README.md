# AlterKC
AlterKC app is designed as Accenture bootcamp student project. 
Main purpose of this app is to improve the way knowledge checks in bootcamp are performed.
App is designed in a way that there are two types of users. First type of user is test administrator
This type of user can create tests and allow regular users to take tests. Regular users are ablet to take tests.

###Test Administrator
Test administrator for AlterKC is only user with test administration rights. Administrator password is set by default in the database.
After login administrator is redirected to administration page. From this page he is able to perform following tasks:
1. View list of all registered users, their scores in each test. Score for each test in such way that each answer for question is 1 point.
Test score is (answer points / answer count). Administrator views total result aswell - total result
is calculated score from individual test / total test count.
2. Test administrator is able to remove users.
3. Test management table. New tests can be added to table. On test creation administrator needs to set:
- test name
- test duration (in minutes)
4. After test is created administrator is redirected to test management page. Here it is possible to add questions to the test.
