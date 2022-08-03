# test-tokopedia

Automation Assignment #1
========================

Problem Statement
------
- Given Order object which will be sent & returned as JSON object
- Given API /processOrder() that receives the Order request and returns Order response with updated "order_status" and "last_updated_timestamp"

Design
------
- Developed using Intellij IDE, promgramming language in Java
- The automation framework requires a few basic components, which are: Test Runner, Test Assertion, and Logging Output
- TestNG is used as the test framework because it has the necessary tools to run our test with simplified functions for. For example, we wouldn't need a main class to execute our test with the help of Annotations. TestNG also provides the test reports after running our tests
- REST Assured Library is used to communicate with our API(s). It supports BDD syntax should we need it in future developments. It's also easy to integrate with TestNG
- We can also utilize Assertion tools that comes with the TestNG, JUnit, or Hamcrest library


Structure
------
- Dependencies are stated in pom.xml
- Definition for test runner in testng.xml
- Classes in src/test/java:
  * HttpAdapter (package that contains anything related to our API caller)
    * HttpHelper --> contains methods which will build our response and initiate connection to the endpoint
    * ProcessOrderAPI --> contains methods for our request builder
  * TestingApp  (package that contains anything related to our test cases)
    * OrderTest --> contains the test cases for processOrder API
  * Util (package that contains any common utilization methods)
    * AssertionUtil --> contains methods to do assertion, such as response and header assertion
    * AuthUtil --> contains method to get authorization key for the API call
  * Resources
    * test-data --> contains the request JSON to be sent. variations of our requests are compiled in this JSON file, and for different test cases we only need to .get() according to the param name


Run Instruction
------
1. Define the API's endpoint and request builder in HttpAdapter.ProcessOrderAPI
2. Define our test cases in TestingApp.OrderTest using @Test annotations
3. Define the checkpoints (assertions) that we need to complete our test
4. Configure our test classes in the testng.xml file in the project root directory
5. Create new Run/Debug configurations - Test Kind: Suite, Suite: (your testng.xml directory) - Apply
   1. in Listereners, tick the "Use Default Reporters" to generate test-output
6. Run the test



Improvement Notes
------
1. Need to improve test reporting. A possible solution to use Allure, because it can visualize our test report
2. Need to improve the test input to be dynamic (e.g get order_id value from other API result)
3. Can utilize more Annotations (e.g grouping, priority) as the test case amount increases
4. Can be integrated using CI tools such as Jenkins
