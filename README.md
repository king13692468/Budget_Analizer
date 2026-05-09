# <p align="center">📊 <strong>SMART BUDGET ANALYZER API</strong> 📊</p>

<p align="center">
  <strong><big>A Spring Boot-based REST API that helps users track daily transactions and manage monthly budgets. The system calculates total expenses and compares them with a predefined budget to provide real-time budget status.</big></strong>
</p>

<br>

## <strong>🚀 FEATURES</strong>
- ✅ Add income and expense transactions
- ✅ View all transactions
- ✅ Set monthly budget limits
- ✅ Automatic expense calculation
- ✅ Budget status check (Within Budget / Exceeded)

<br>

## <strong>🧱 PROJECT STRUCTURE</strong>
- `com.example.BudgetAnalyzer/`
  - `controller/` - TransactionController.java
  - `service/` - TransactionService.java
  - `repository/` - TransactionRepository.java, BudgetRepository.java
  - `model/` - Transaction.java, Budget.java, TransactionType.java


## 📸 Screenshots

Click on any link to view the screenshot:

### Application & Database
- [Application Running](screenshot/01-terminal-running.png)
- [Database Tables](screenshot/02-database-tables.png)

### Transaction CRUD Operations
- [Add Transaction](screenshot/03-add-transaction.png)
- [Get All Transactions](screenshot/04-get-all-transactions.png)
- [Update Transaction](screenshot/05-update-transaction.png)
- [Delete Transaction](screenshot/06-delete-transaction.png)

### Budget Management
- [Set Overall Budget](screenshot/07-set-budget.png)
- [Budget Status - Within Limit](screenshot/8-budget-status-normal.png)
- [Budget Exceeded Alert](screenshot/9-budget-exceeded.png)

### Analytics & Reports
- [Monthly Summary](screenshot/10-monthly-summary.png)
- [Top Spending Categories](screenshot/11-top-categories.png)

### Filtering Features
- [Transactions by Date Range](screenshot/12-date-range-filter.png)
- [Transactions by Category (Food)](screenshot/13-category-filter-food.png)



<br>

## <strong>⚙️ TECH STACK</strong>
| Technology | Purpose |
|------------|---------|
| **Java** | Programming Language |
| **Spring Boot** | Framework |
| **Spring Data JPA** | Database ORM |
| **Hibernate** | JPA Implementation |
| **MySQL** | Database |
| **Maven** | Build Tool |

<br>

## <strong>🧠 ARCHITECTURE</strong>
Client (Postman)
↓
Controller (API Layer)
↓
Service (Business Logic)
↓
Repository (JPA)
↓
MySQL Database

<br>

## <strong>🧩 ENTITIES</strong>

### <strong>➤ Transaction</strong>
Stores amount, type (INCOME/EXPENSE), category, and date

### <strong>➤ Budget</strong>
Stores monthly budget limit and month

<br>

## <strong>🔌 API ENDPOINTS</strong>

### <strong>➤ Add Transaction</strong>

### 1. Add Transaction

**Endpoint:** `POST /api/transaction`

**Request Body:**
```json
{
  "description": "Weekly grocery",
  "amount": 850.50,
  "type": "EXPENSE",
  "category": "Food",
  "date": "2026-05-08"
}
<strong>➤ Get All Transactions</strong>
GET /api/transactions
<strong>➤ Set Budget</strong>
POST /api/budget?amount=2000
<strong>➤ Check Budget Status</strong>
GET /api/budget-status
Response:
text
✅ Within Budget
⚠️ Budget Exceeded!

<strong>🗄️ DATABASE CONFIGURATION</strong>
properties
spring.datasource.url=jdbc:mysql://localhost:3306/expense_tracker
spring.datasource.username=root
spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

<strong>▶️ HOW TO RUN</strong>
Clone the repository

-Open in IntelliJ IDEA

-Create MySQL database: expense_tracker

-Update application.properties with your credentials

-Run ExpenseTrackerApplication.java


<strong>🧪 TESTING</strong>
Use Postman to test the APIs:

🔹 Send POST requests to add transactions

🔹 Use GET requests to fetch data and check budget


<strong>🎯 FUTURE IMPROVEMENTS</strong>
🔐 Add user authentication (login/signup)

📈 Add category-wise analytics

🎨 Add frontend UI

🔍 Add transaction filters (date/category)


<strong>👨‍💻 AUTHOR</strong>
<p><strong>Md Shadab Mobin</strong></p> ```




