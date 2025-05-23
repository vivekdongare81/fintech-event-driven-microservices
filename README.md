# Fintech-Event-Driven-Microservices

This project is a **microservices-based fintech platform** built using an **event-driven architecture**. It simulates a banking/financial ecosystem where independent services communicate asynchronously through messaging. The architecture is designed for **scalability**, **resilience**, and **modularity**, making it ideal for modern financial applications.

---

## 💡 Features

- 🧾 **Account Service** – Manage user accounts and profiles  
- 💳 **Card Service** – Issue, block, and manage debit/credit cards  
- 🏦 **Loan Service** – Apply, approve, and manage loans  
- 💼 **Customer Service** – Central customer data management  
- 🔔 **Notification Service** – Send alerts via email/SMS  
- 📊 **Config Server** – Centralized configuration for all services  
- 📘 **Eureka Discovery** – Service registration and discovery  
- 📢 **API Gateway** – Unified entry point for all microservices  
- 🔄 **Kafka Integration** – Event-based inter-service communication  
- 📜 **Spring Cloud Config** – Externalized configuration management  
- 🔒 **Security** – JWT-based authentication and authorization

---

## 🧱 Tech Stack

| Category         | Technology                        |
|------------------|------------------------------------|
| Language         | Java 17                            |
| Framework        | Spring Boot, Spring Cloud          |
| Messaging        | Apache Kafka                       |
| Discovery        | Netflix Eureka                     |
| API Gateway      | Spring Cloud Gateway               |
| Config           | Spring Cloud Config Server         |
| Security         | Spring Security + JWT              |
| Build Tool       | Maven                              |
| Containerization | Docker                             |


---

## 📦 Microservices Overview

| Service               | Port | Description                    |
|-----------------------|------|--------------------------------|
| API Gateway           | ---- | Entry point for all services   |
| Eureka Server         | ---- | Service registry               |
| Config Server         | ---- | Shared configuration           |
| Account Service       | ---- | Handles user accounts          |
| Card Service          | ---- | Handles cards                  |
| Loan Service          | ---- | Handles loans                  |
| Notification Service  | ---- | Event-driven notifications     |
| Customer Service      | ---- | Central customer management    |

---

## 🚀 Getting Started

### ✅ Prerequisites

- Java 17+
- Maven
- Docker (for Kafka and Zookeeper)
- Postman or Curl (for API testing)

### 🔧 Setup Instructions

1. **Clone the repository**

```bash
git clone https://github.com/your-username/fintech-even-driven-microservices.git
cd fintech-even-driven-microservices


Future Plans:

Digital wallets
Personal finance tools
Payment gateway integrations
AI-driven financial insights

Reactive programming
Event-driven design
Cloud-native infrastructure
