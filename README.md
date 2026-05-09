# <p align="center">📊 <strong>SMART BUDGET ANALYZER API</strong> 📊</p>

<p align="center">
  <strong><big>A Spring Boot-based REST API that helps users track daily transactions and manage monthly budgets. The system calculates total expenses and compares them with a predefined budget to provide real-time budget status.</big></strong>
</p>

<br>

## 📋 TABLE OF CONTENTS

| # | Section |
|---|---------|
| 1 | [🚀 Features](#-features) |
| 2 | [🧱 Project Structure](#-project-structure) |
| 3 | [📸 Screenshots](#-screenshots) |
| 4 | [⚙️ Tech Stack](#️-tech-stack) |
| 5 | [🧠 Architecture](#-architecture) |
| 6 | [🧩 Entities](#-entities) |
| 7 | [🔌 API Endpoints](#-api-endpoints) |
| 8 | [🗄️ Database Configuration](#️-database-configuration) |
| 9 | [▶️ How to Run](#️-how-to-run) |
| 10 | [🧪 Testing](#-testing) |
| 11 | [🎯 Future Improvements](#-future-improvements) |
| 12 | [👨‍💻 Author](#-author) |

<br>

## 🚀 FEATURES

| # | Feature | Description |
|---|---------|-------------|
| ✅ | **Add Transactions** | Add income and expense transactions |
| ✅ | **View Transactions** | View all transactions |
| ✅ | **Set Budget** | Set monthly budget limits |
| ✅ | **Auto Calculation** | Automatic expense calculation |
| ✅ | **Budget Status** | Check (Within Budget / Exceeded) |
| ✅ | **Update/Delete** | Modify or remove transactions |
| ✅ | **Category Filter** | Filter by category (Food, Rent, etc.) |
| ✅ | **Date Range Filter** | Filter transactions by date |
| ✅ | **Monthly Summary** | View monthly spending summary |
| ✅ | **Top Categories** | See top spending categories |

<br>

## 🧱 PROJECT STRUCTURE

```
com.example.BudgetAnalyzer/
├── controller/
│   └── TransactionController.java
├── service/
│   └── TransactionService.java
├── repository/
│   ├── TransactionRepository.java
│   └── BudgetRepository.java
├── model/
│   ├── Transaction.java
│   ├── Budget.java
│   └── TransactionType.java
└── BudgetAnalyzerApplication.java
```

<br>

## 📸 Screenshots

Click any link below to view screenshots:

### Application & Database
- [📷 Application Running](screenshot/01-terminal-running.png)
- [📷 Database Tables](screenshot/02-database-tables.png)

### Transaction CRUD Operations
- [📷 Add Transaction](screenshot/03-add-transaction.png)
- [📷 Get All Transactions](screenshot/04-get-all-transactions.png)
- [📷 Update Transaction](screenshot/05-update-transaction.png)
- [📷 Delete Transaction](screenshot/06-delete-transaction.png)

### Budget Management
- [📷 Set Overall Budget](screenshot/07-set-budget.png)
- [📷 Budget Status - Within Limit](screenshot/8-budget-status-normal.png)
- [📷 Budget Exceeded Alert](screenshot/9-budget-exceeded.png)

### Analytics & Reports
- [📷 Monthly Summary](screenshot/10-monthly-summary.png)
- [📷 Top Spending Categories](screenshot/11-top-categories.png)

### Filtering Features
- [📷 Transactions by Date Range](screenshot/12-date-range-filter.png)
- [📷 Transactions by Category (Food)](screenshot/13-category-filter-food.png)

> 💡 **Click any link** to view the screenshot in your browser
> 📁 All screenshots are in the [`screenshot`](screenshot) folder

<br>

## ⚙️ TECH STACK

| Technology | Purpose |
|------------|---------|
| **Java** | Programming Language |
| **Spring Boot** | Framework |
| **Spring Data JPA** | Database ORM |
| **Hibernate** | JPA Implementation |
| **MySQL** | Database |
| **Maven** | Build Tool |
| **Postman** | API Testing |

<br>

## 🧠 ARCHITECTURE

```
Client (Postman)
       ↓
  Controller
   (API Layer)
       ↓
    Service
 (Business Logic)
       ↓
   Repository
      (JPA)
       ↓
  MySQL Database
```

<br>

## 🧩 ENTITIES

### ➤ Transaction
Stores amount, type (INCOME/EXPENSE), category, and date

| Field | Type | Description |
|-------|------|-------------|
| id | Long | Primary Key |
| description | String | Transaction description |
| amount | Double | Transaction amount |
| type | Enum | INCOME or EXPENSE |
| category | String | Food, Rent, Shopping, etc. |
| date | LocalDate | Transaction date |

### ➤ Budget
Stores monthly budget limit

| Field | Type | Description |
|-------|------|-------------|
| id | Long | Primary Key |
| amount | Double | Budget limit |
| month | String | Month-Year (e.g., MAY-2026) |

<br>

## 🔌 API ENDPOINTS

### Transaction Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/transaction` | Add new transaction |
| GET | `/api/transactions` | Get all transactions |
| GET | `/api/transaction/{id}` | Get transaction by ID |
| PUT | `/api/transaction/{id}` | Update transaction |
| DELETE | `/api/transaction/{id}` | Delete transaction |

### Budget Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/budget?amount=2000` | Set monthly budget |
| GET | `/api/budget-status` | Check budget status |

### Filter Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/transactions/category/{category}` | Filter by category |
| GET | `/api/transactions/date-range` | Filter by date range |
| GET | `/api/monthly-summary` | Get monthly summary |
| GET | `/api/top-categories` | Get top spending categories |

### Request Examples

**Add Transaction:**
```json
POST /api/transaction
{
  "description": "Weekly grocery",
  "amount": 850.50,
  "type": "EXPENSE",
  "category": "Food",
  "date": "2026-05-08"
}
```

**Response:**
```json
{
  "id": 1,
  "description": "Weekly grocery",
  "amount": 850.50,
  "type": "EXPENSE",
  "category": "Food",
  "date": "2026-05-08"
}
```

**Set Budget:**
```
POST /api/budget?amount=20000
```

**Budget Status Response:**
```
✅ Within Budget
```
or
```
⚠️ Budget Exceeded!
```

<br>

## 🗄️ DATABASE CONFIGURATION

```properties
# MySQL Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/expense_tracker
spring.datasource.username=root
spring.datasource.password=yourpassword

# JPA Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

<br>

## ▶️ HOW TO RUN

1. **Clone the repository**
   ```bash
   git clone https://github.com/king13692468/budget-analyzer.git
   cd budget-analyzer
   ```

2. **Open in IntelliJ IDEA** (or any IDE)

3. **Create MySQL database**
   ```sql
   CREATE DATABASE expense_tracker;
   ```

4. **Update `application.properties`** with your MySQL credentials

5. **Run the application**
   ```bash
   mvn spring-boot:run
   ```
   Or run `BudgetAnalyzerApplication.java` in your IDE

6. **Access the API** at `http://localhost:8080`

<br>

## 🧪 TESTING

Use **Postman** or any API client to test the endpoints:

| Action | Method | Endpoint | Example |
|--------|--------|----------|---------|
| Add expense | POST | `/api/transaction` | Send JSON with expense details |
| Add income | POST | `/api/transaction` | Send JSON with income details |
| View all | GET | `/api/transactions` | - |
| Set budget | POST | `/api/budget?amount=20000` | - |
| Check status | GET | `/api/budget-status` | - |
| Filter by category | GET | `/api/transactions/category/Food` | - |

### Sample Test Flow

1. **Set budget:** `POST /api/budget?amount=20000`
2. **Add expense:** `POST /api/transaction` (grocery: ₹850)
3. **Add expense:** `POST /api/transaction` (rent: ₹10000)
4. **Check budget status:** `GET /api/budget-status` → Should show "Within Budget"
5. **Add expense:** `POST /api/transaction` (shopping: ₹10000)
6. **Check budget status:** `GET /api/budget-status` → Should show "Budget Exceeded!"

<br>

## 🎯 FUTURE IMPROVEMENTS

- [ ] 🔐 Add user authentication (login/signup)
- [ ] 📈 Add category-wise analytics with charts
- [ ] 🎨 Add frontend UI (React or Thymeleaf)
- [ ] 🔍 Add advanced transaction filters
- [ ] 📧 Email notifications for budget alerts
- [ ] 📊 Export reports as PDF/Excel
- [ ] 📱 Mobile app integration
- [ ] 💰 Multiple currency support
- [ ] 📅 Recurring transactions
- [ ] 🏦 Bank statement import

<br>

## 👨‍💻 AUTHOR

**Md Shadab Mobin**

<div align="center">

[![GitHub](https://img.shields.io/badge/GitHub-king13692468-181717?style=for-the-badge&logo=github)](https://github.com/king13692468)
[![Portfolio](https://img.shields.io/badge/Portfolio-Live_Demo-28a745?style=for-the-badge&logo=railway)](https://portfolio-production-7853.up.railway.app/)

</div>

<br>

## ⭐ SHOW YOUR SUPPORT

If you found this project helpful, please give it a ⭐ on GitHub!

<div align="center">

### Built with ❤️ using Spring Boot

</div>
