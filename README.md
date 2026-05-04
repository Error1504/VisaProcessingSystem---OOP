# 🛂 Visa Processing System

> A console-based Java application for managing visa applications, built as a course project for **Object Oriented Programming (CMPS_251)**.

---

## 📋 Table of Contents
- [About the Project](#about-the-project)
- [Features](#features)
- [Project Structure](#project-structure)
- [Class Overview](#class-overview)
- [Data Files](#data-files)
- [Getting Started](#getting-started)
- [How to Run](#how-to-run)
- [Built With](#built-with)
- [Team](#team)

---

## 📌 About the Project

The **Visa Processing System** simulates a real-world visa management workflow. It allows officers to manage applicants, process different types of visa applications, and track application statuses — all using core OOP concepts such as inheritance, encapsulation, and polymorphism.

---

## ✨ Features

- Add and manage **applicants** with personal details
- Process three types of visas:
  - 🎓 Student Visa
  - ✈️ Tourist Visa
  - 💼 Work Visa
- Assign **officers** to handle applications
- Track **application status** throughout the process
- Load and save data using **CSV files**

---

## 🗂️ Project Structure

```
VisaProcessingSystem---OOP/
│
├── courseProject/
│   └── src/
│       └── draft1/
│           ├── Applicant.java
│           ├── Application.java
│           ├── DataLoader.java
│           ├── DataManager.java
│           ├── Officer.java
│           ├── Status.java
│           ├── StudentVisa.java
│           ├── TouristVisa.java
│           ├── WorkVisa.java
│           ├── VisaOfficeSystem.java
│           └── StringTester.java
│
│   └── CourseProject/
│       ├── applicants.csv
│       ├── applications.csv
│       └── officers.csv
│
└── .gitignore
```

---

## 🧩 Class Overview

| Class | Description |
|---|---|
| `Applicant` | Stores applicant personal info (name, passport, nationality, birthdate) |
| `Application` | Represents a visa application linked to an applicant |
| `StudentVisa` | Handles student visa logic, extends Application |
| `TouristVisa` | Handles tourist visa logic, extends Application |
| `WorkVisa` | Handles work visa logic, extends Application |
| `Officer` | Represents a visa processing officer |
| `Status` | Enum/class tracking application status |
| `DataLoader` | Reads data from CSV files |
| `DataManager` | Manages in-memory data and operations |
| `VisaOfficeSystem` | Main entry point and system controller |
| `StringTester` | Utility class for string validation |

---

## 📁 Data Files

| File | Description |
|---|---|
| `applicants.csv` | Stores applicant records |
| `applications.csv` | Stores visa application records |
| `officers.csv` | Stores officer records |

---

## 🚀 Getting Started

### Prerequisites
- Java **JDK 21** or higher
- Eclipse IDE (recommended) or any Java IDE

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/Error1504/VisaProcessingSystem---OOP.git
   ```

2. Open **Eclipse IDE**

3. Go to **File** → **Import** → **Existing Projects into Workspace**

4. Select the cloned folder and click **Finish**

---

## ▶️ How to Run

1. Navigate to `VisaOfficeSystem.java`
2. Right-click → **Run As** → **Java Application**
3. Follow the console prompts to interact with the system

---

## 🛠️ Built With

- **Java 21**
- **Eclipse IDE**
- **CSV** for data persistence
- OOP principles: Inheritance, Encapsulation, Polymorphism, Abstraction

---

## 👥 Team

| Name | GitHub |
|---|---|
| Member 1 | [@Error1504](https://github.com/Error1504) |
| Member 2 | [@abdulhadiasifhassan-coder](https://github.com/abdulhadiasifhassan-coder) |
| Member 3 |  [@ayhamawm](https://github.com/ayhamawm) |
| Member 4 |  [@DefNotHash](https://github.com/defnothash) |


> 📚 Course: CMPS_251 — Object Oriented Programming
