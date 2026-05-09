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
com.example.BudgetAnalyzer
├── controller
├── service
├── repository
└── model

## Screenshots

### Application Running
![Terminal](screenshots/01-terminal-running.png)

### Database Tables
![Database](screenshots/02-database-tables.png)

### Add Transaction
![Add Transaction](screenshots/03-add-transaction.png)
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

```http```
POST /api/transaction
Request Body:
json
{
  "amount": 500,
  "type": "EXPENSE",
  "category": "Food",
  "date": "2026-04-26"
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




