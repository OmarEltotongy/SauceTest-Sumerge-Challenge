# Selenium WebDriver Test Automation for Sauce Labs Demo

This project contains automated tests using Selenium WebDriver and TestNG for the Sauce Labs demo website. These tests cover login scenarios with various credentials to validate functionality and error handling.

## Project Details

- **Challenge Task:** Automation Challenge for Associates and Quality Engineers at Sumerge.
- **Objective:** Implement automated tests using Selenium WebDriver to validate login functionality on the Sauce Labs demo website.
- **Submitted By:** Omar Eltoutongy

## Getting Started

### Prerequisites

- JDK (Java Development Kit) installed on your machine
- Maven (for managing dependencies and building the project)
- Microsoft Edge WebDriver executable (`msedgedriver.exe`) downloaded and placed in a directory accessible by your system. You can download it from [Microsoft Edge WebDriver](https://developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/).

### Setting Up the Project

1. **Clone the repository:**
   ```bash
   git clone <https://github.com/OmarEltotongy/SauceTest-Sumerge-Challenge.git>
   cd <SauceTest-Sumerge-Challenge>
   ```

2. **Set up the WebDriver:**
   - Download the Microsoft Edge WebDriver from the provided link and place it in a directory of your choice.
   - Update the `System.setProperty("webdriver.edge.driver", "path_to_edgedriver.exe");` line in `SauceTest.java` with the correct path to `msedgedriver.exe`.

3. **Install dependencies:**
   This project uses Maven for dependency management. Run the following command to install dependencies:
   ```bash
   mvn clean install
   ```

### Running the Tests

To execute the tests, run the following Maven command:
```bash
mvn test
```

This command will run all the TestNG tests specified in the project.

### Test Details

- **`SauceTest.java`:** Contains the following test cases:
  - `testLoginPageElements()`: Verifies the presence of login page elements.
  - `testValidCredentials()`: Tests login with valid credentials.
  - `testInvalidCredentials()`: Tests login with invalid credentials.
  - `testEmptyCredentials()`: Tests login with empty username and password.

### Reporting

After running the tests, you can view the TestNG HTML reports in the `target/surefire-reports` directory. These reports provide details about the test execution status and any failures encountered.

## Additional Notes

- Ensure all dependencies are correctly installed and configured before running the tests.
- Adjust paths and configurations as per your local environment setup.


## Submission Details

- **Submitted Date:** 19/6/2024
- **Submitted By:** OmarEltoutongy
- **Email:** OmarEltoutongy@outlook.com
