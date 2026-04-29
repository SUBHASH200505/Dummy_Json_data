# 🚀 Dummy JSON API Automation Framework

This is a **modular, data-driven API automation framework** built using **Java, RestAssured, Cucumber, TestNG, and Maven**.

The framework is designed to automate multiple API modules with reusable components and Excel-based test data.

---

## 📌 Project Overview

This framework supports automation for multiple API modules:

* 🔐 Authentication
* 🛒 Cart
* 💬 Comments
* 📦 Products
* 🍽 Recipes
* ✅ Todos

---

## 🧰 Tech Stack

* Java
* RestAssured
* Cucumber (BDD)
* TestNG
* Maven
* Apache POI (Excel handling)

---

## 📁 Project Structure

```text
src/main/java
 └── Utils
      ├── ApiUtil.java
      ├── ConfiReader.java
      └── ExcelUtil.java

src/test/java
 ├── Hooks
 │     └── Hooks.java
 ├── Runner
 │     └── TestRunner.java
 └── stepdefinitions
       ├── AuthStepDefinition.java
       ├── CartStepDefinition.java
       ├── CommentsStepDefinition.java
       ├── ProductStepDefinition.java
       ├── RecipeStepDefinition.java
       └── TodosStepDefinition.java

src/test/resources
 ├── features
 │     ├── Auth.feature
 │     ├── Cart.feature
 │     ├── Comments.feature
 │     ├── Product.feature
 │     ├── recipe.feature
 │     └── ToDo.feature
 │
 ├── config.properties
 ├── extent.properties
 └── hell.xlsx
```

---

## ⚙️ Framework Design

This framework follows:

✔ Data-Driven Testing (Excel)
✔ BDD Approach (Cucumber)
✔ Modular Design (Each API module separated)
✔ Reusable Utilities

---

## 🔄 Execution Flow

```text
Feature File → Step Definition → Excel Data → API Call → Validation
```

---

## ▶️ How to Run

### Using Maven

```bash
mvn clean test
```

### Using Eclipse

* Right-click `TestRunner.java`
* Run As → TestNG Test

---

## 📊 Reporting

* Uses **Extent Reports**
* Configured via `extent.properties`

---

## 🌿 Git Workflow

* `main` → stable code
* Feature branches → module-based development

Example:

* `feature/products`
* `feature/carts`
* `feature/auth`

---

## 🔧 Jenkins Integration

Each module can include its own `Jenkinsfile` for CI/CD execution.

---

## 👥 Team Collaboration

* Common utilities shared across modules
* Each module handled by different team members
* Code merged using Pull Requests

---

## 📌 Best Practices

✔ Reusable code
✔ Clean modular structure
✔ Excel-driven testing
✔ Separate feature files per module

---

## 🚀 Future Enhancements

* Parallel execution
* Advanced reporting
* API chaining
* Token management

---

## 🧠 Summary

This framework is designed to be:

✔ Scalable
✔ Maintainable
✔ Reusable
✔ Industry-ready

---

⭐ A complete API automation solution using modern tools and best practices.
