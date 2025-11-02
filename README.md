# 💰 Splitwise Clone

A simplified **Splitwise-like Expense Sharing Application** that helps users **record expenses, split costs among group members, and settle up balances** using an optimized algorithm.  
This project demonstrates **object-oriented design**, **data modeling**, and **algorithmic problem-solving** using Java.

---

## 🧠 Core Idea

Whenever people share expenses (like a trip or a dinner), each person may pay a different amount.  
This project helps calculate **who owes whom and how much** so that everyone is settled efficiently with the **minimum number of transactions**.

---

## ⚙️ Settle Up Algorithm

### Concept
Each user has a **net balance**:
- Positive (`+`) → the user **should receive money**
- Negative (`-`) → the user **owes money**

Example:
| User | Balance |
|------|----------|
| A | +1750 |
| B | -50 |
| C | -1250 |
| D | -450 |

### Step-by-Step Flow
1. Separate users into two groups:
   - **Group 1 (Debtors)** → people who owe money (`-`)
   - **Group 2 (Creditors)** → people who should receive money (`+`)

2. Match:
   - **Smallest debtor (min amount)** with **largest creditor (max amount)**.
   - Perform a transaction for `min(abs(debtor), abs(creditor))`.

3. Update balances and repeat until all are settled.

### Example
| Iteration | Debtor → Creditor | Amount | Result |
|------------|------------------|---------|--------|
| 1 | C → A | 1250 | C settled, A +500 left |
| 2 | D → A | 450 | D settled, A +50 left |
| 3 | B → A | 50 | All settled ✅ |

### Time Complexity
Using two **Heaps**:
- `getMin()`, `getMax()` → O(1)
- `insert()`, `deleteMin()` → O(log N)
- **Overall:** `O(N log N)`

---

## 🏗️ System Design Overview

### 🧩 Class Diagram (Conceptual)

#### Entities
- **User**
- **Expense**
- **Group**
- **UserExpense**
- *(No separate Transaction entity — handled as dummy expenses)*

#### Relationships
User 1 <---- M Expense  
Expense 1 <---- M UserExpense  
User 1 <---- M UserExpense  
Group 1 <---- M Expense  
Group M <---- M User

---

## 📚 Class Structure

### User
| Field | Type |
|-------|------|
| id | Long |
| name | String |
| phone | String |
| email | String |
| password | String |

### Expense
| Field | Type |
|-------|------|
| id | Long |
| description | String |
| amount | Double |
| createdBy | User |
| expenseType | Enum (NORMAL / DUMMY) |
| paidBy | Map<User, Double> |
| hadToPay | Map<User, Double> |

### UserExpense
| Field | Type |
|-------|------|
| user | User |
| expense | Expense |
| amount | Double |
| type | Enum (PAID_BY / HAD_TO_PAY) |

### Group
| Field | Type |
|-------|------|
| id | Long |
| name | String |
| admin | User |
| users | List<User> |
| expenses | List<Expense> |

---

## 🧾 Database Schema Design

### Tables
#### `users`
| id | name | phone | email | password |

#### `expenses`
| id | description | amount | created_by_id | expense_type | group_id |

#### `user_expenses`
| user_id | expense_id | amount | user_expense_type |

#### `groups`
| id | name | admin_id |

#### `users_groups`
| id | user_id | group_id |

---

## 🧮 Example

### Expense 1: Dinner
PaidBy:  
A: 1200, B: 800

HadToPay:  
A: 500, B: 200, C: 700, D: 600

Net Balances:
| User | Balance |
|------|----------|
| A | +700 |
| B | +600 |
| C | -700 |
| D | -600 |

**Transactions:**
- C → A : 700  
- D → B : 600  

### Expense 2: Sweet Shop
PaidBy:  
A: 500

HadToPay:  
A: 100, B: 100, C: 50, D: 250

Transactions are recalculated at runtime, so **no need to store them permanently**.  
When a transaction happens, a **dummy expense** is created to reflect it:

PaidBy: A +1000  
HadToPay: B -1000

---

## 📈 Tech Stack

- **Language:** Java  
- **Design Pattern:** Strategy (Command pattern)  
- **Data Structure:** Heap (Priority Queue)  
- **Database:** (Optional) MySQL / H2  
- **Architecture:** Modular & Extensible  

---

## 🏁 Summary

This project simulates how Splitwise efficiently calculates and settles expenses using **O(N log N)** algorithmic efficiency, clean **object-oriented design**, and flexible **command-based input handling**.
