# Expense Tracker App

# Functional Requirements

1. User able to Sign in and Sign up
2. User able to add/remove expense manually
3. User able to see his expenses, catagorized them
4. User able to see weekly, monthly, yearly report and stats about spending

# Non - Functional Requirements

1. Sys needs to be fault tolerant, scalable and latency < 100ms
2. Config driven system if possible to accomodate less code changes in future

# Future Scope or Requirements

1. User is able to track financial behaviour and can ask for tips to improve
2. App able to add expence on its own by reading and parse User's SMS given that user allow it
3. Whatsapp and SMS notifications indicating the risk of overspending etc.

# HLD

```
+--------+           +-------------+
| Client | --------> | API Gateway |
+--------+           +------+------+
                             |
                             v
                 +-----------+-----------+
                 |       Microservices    |
                 |   (Spring Boot apps)   |
                 |                        |
                 |  +------------------+  |
                 |  |   Auth Service   |<------+
                 |  +--------+---------+       |
                 |           |                 |
                 |           v                 |
                 |   +---------------+         |
                 |   |   Datastore   |         |
                 |   +---------------+         |
                 |           |                 |
                 |           v                 |
                 |   +--------------------+    |
                 |   | Notification Svc   |<---+
                 |   +--------------------+    |
                 |           ^                 |
                 |           |                 |
                 |  +--------+----------+      |
                 |  | Templatization Svc |<----+
                 |  +--------+----------+      |
                 |           |                 |
     +-----------+-----------+-------------+---+------------+
     |           |           |             |                |
     v           v           v             v                v
+---------+  +--------+  +---------+   +-----------+   +-------------+
|  User   |  | Ledger |  | Billing |   | Reporting |   | Other Svcs  |
| Service |  | Svc    |  | Service |   |  Service  |   | (Optional)  |
+---------+  +--------+  +---------+   +-----------+   +-------------+
```

| Tool          | Purpose                            |
| ------------- | ---------------------------------- |
| 🔁 RabbitMQ   | Message brokering / event handling |
| 🧠 Redis      | Caching / Session management       |
| 🐳 Docker     | Containerization                   |
| 🧩 Kubernetes | Orchestration                      |
| ⚙️ Kafka      | Distributed messaging/logging      |
| 🧱 PostgreSQL | Relational DB (Datastore)          |
| 🛡 Kong        | API Gateway / Management           |

## 📘 Notes

- [Design Notes (Notion)](https://ginger-uranium-8af.notion.site/EP01-Auth-Service-9ff640aee5cb435ebd22be61b5fb1aae)
