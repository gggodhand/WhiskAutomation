# Automation example for API and GUI tests

Implemented using: 
 * **Maven** 
 * **Selenidie** 
 * **Kotlin** 
 * **RestAssured**
 * **TestNG** 
 * **Allure** 

To run tests locally execute:
1. `mvn clean test` - will execute all tests
   `mvn clean test -DsuiteXml=gui.xml` - only UI tests
   `mvn clean test -DsuiteXml=api.xml` - only API tests

2. `mvn io.qameta.allure:allure-maven::serve` - to see reposts
   
