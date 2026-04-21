# Playwright Automation Framework

A robust, scalable Java-based automation framework designed for end-to-end testing of Web, API, and Database layers. This project leverages **Playwright** for high-performance web automation, **RestAssured** for API testing, and **TestNG** for test orchestration.

## 🚀 Tech Stack
- **Language:** Java 21
- **Web Automation:** Playwright
- **API Testing:** RestAssured
- **Test Runner:** TestNG
- **Reporting:** Allure Report & Extent Reports
- **Database:** MongoDB & MySQL (JDBC)
- **Cloud Execution:** BrowserStack Integration
- **Build Tool:** Maven

## Prerequisites
- **Java:** 21 or higher
- **Maven:** 3.2 or higher

## Getting Started
1. Clone the repository:
   ```bash
   git clone <repository-url>
   ```
2. Navigate to the project directory:
   ```bash
   cd playwright-automation-framework
   ```
3. Install dependencies:
   ```bash
   mvn clean install
   ```
4. Install Playwright Browsers:
   ```bash
   mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="install"
   ```

## Running Tests
Run tests using Maven with various parameters:
```bash
# Run Web tests on Chrome in QA environment
mvn test -DTestSuite="WEB" -DsuitexmlfilePath="testng.xml" -Dbrowser="chrome" -Denvironment="qa"

# Run API tests
mvn test -DTestSuite="API" -DsuitexmlfilePath="testng_api.xml"
```

## Project Structure
* `src/main/java/PlaywrightPageObject`: Page Object Models for web interactions.
* `src/main/java/DatabaseUtilities_Actions`: Handlers for MySQL and MongoDB.
* `src/main/java/FactoryClasses`: Factories for Browser instances and URL management.
* `src/main/java/Pojos`: Plain Old Java Objects for API Request/Response mapping.
* `src/test/java/tests`: TestNG test classes categorized by feature.
* `src/test/resources`: Configuration properties, SQL queries, and test data.

## 📊 Reporting
- **Extent Reports:** Generated in `target/extent_report/`.
- **Allure Reports:** Generate after execution using:
  ```bash
  allure serve allure-results
  ```
- **Traces:** Playwright traces are saved in `target/` for failed tests.

## 🛠 Playwright Codegen
To record new test cases using the Playwright inspector:
```bash
mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="codegen https://example.com"
```

## 🌐 BrowserStack Integration
Update `browserstack.yml` with your credentials or set them as environment variables:
* `BROWSERSTACK_USERNAME`
* `BROWSERSTACK_ACCESS_KEY`

## 🔗 Zephyr Integration
1. Set `ZEPHYR_API_TOKEN` as an environment variable.
2. Run `ZephyrIntegration.java` to sync test cases.
3. Enable `ZEPHYR_UPDATE_RESULT=true` in `Zephyr.properties` to push results automatically.

## References
* Playwright Java Documentation
* JSON Schema to POJO