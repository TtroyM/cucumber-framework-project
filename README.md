# Selenium Cucumber Automation Framework

This project is a Java-based test automation framework built to validate core user flows on a sample e-commerce application using Behavior Driven Development (BDD).

---

## Tech Stack
- Java
- Selenium WebDriver
- Cucumber (BDD)
- TestNG
- Maven
- Extent Reports

---

## Project Structure
- `features/` – Gherkin feature files
- `pageObjects/` – Page Object Model classes
- `stepDefinitions/` – Step definitions for Cucumber scenarios
- `runners/` – TestNG and Cucumber test runners
- `utils/` – Driver setup, context, and shared utilities
- `resources/` – Configuration files (properties, reporting)

---

## How to Run Tests

### Run all tests
```bash
mvn clean test

```

### Run tests by tag
```bash
mvn test -Dcucumber.filter.tags="@Search"
