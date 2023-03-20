**Overview**

Automation for “Showing flights status based on flight routes” has been performed on the provided [Eurowings](https://www.eurowings.com/en/information/at-the-airport/flight-status.html) webpage.. Prioritizing the testcase flow below.
1. If the cookies page is displayed, the cookies are accepted. 
2. Flight status from CGN to BER for today are requested and the result is verified. 
3. Flight status from CGN to BER for tomorrow are requested and the result is verified.
4. Flight status from CGN to BER for day after tomorrow are requested and the result is verified.
5. Validates flight status for an unavailability route displays an appropriate message.

**Pre condition**
1. Intellij IDE with Maven, selenium and testNG.
2. JDK

**Technology used **
1. Maven - Aids in downloading dependencies Building the project
2. Java
3. Selenium
4. TestNG - Readable, Grouping using annotation, reporting
5. Extent report

**Best practices**
1. Uses Page object model
2. Use Reusable Request
3. Failure screenshots can be found in screenshot folder
4. The Project is CI/CD friendly. Jobs can be scheduled using the TestNG XML file
5. Report are generated and stored in report folder with the test suite name

**Components**
Page objects are in the directory src/main/java/pages
Test classes are in the directory src/test/java/tests
Listener class is in the directory src/main/java/util

**How to Run**
1. Clone the repository from the branch
2. Run the TestNG.xml file in intellij OR
3. Run mvn test in intellj / terminal

Future improvement
1. Report can be made even more explanatory
2. Edge testcases and more scenarios can be added
3. Support for multiple browser coverage 
