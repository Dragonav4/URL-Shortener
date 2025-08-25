# URL Shortener with Password Protection

A web application that allows users to create and manage shortened links with optional password protection, visit counters, and multilingual user interface.  
The project includes both **REST API** and **web interface (Thymeleaf)**.

---

## Features
- Create short links with custom names and target URLs  
- Optional password protection (with strong password validation)  
- Secure password storage using Spring `PasswordEncoder`  
- Redirect with **visit counter**  
- REST API for integration (CRUD operations)  
- Web interface with forms for create/edit/delete  
- Multilingual support (**EN/PL/DE**) with language switcher  
- API documentation via **Swagger**  
- H2 in-memory database for development/testing  

---

## Tech Stack
- **Backend:** Java 21, Spring Boot 3, Spring MVC, Spring Data JPA  
- **Frontend:** Thymeleaf, Bootstrap (basic)  
- **Database:** H2 (in-memory)  
- **Tools:** Maven, Swagger (springdoc-openapi), IntelliJ IDEA  
---

## 📂 Project Structure

src/main/java/org/example/s31722tpo10
├── Constraints      # Password validator
├── Controller       # REST & Web controllers
├── DataLayer
│    └── Models      # JPA entities + DTOs
├── Interfaces       # Repository interfaces
├── Service          # Business logic
├── Utils            # Utility classes
└── S31722Tpo11Application.java   # Main entry point

src/main/resources
├── static           # (static assets if needed)
├── templates        # Thymeleaf views (create, details, edit, error)
├── application.yaml # App configuration
└── messages*.properties # i18n resource bundles (EN, PL, DE)

---
  Web Interface (Thymeleaf)
	•	/links/create → form to create link
	•	/links/{id} → details page (short URL, target, visits)
	•	/links/{id}/edit → edit form
	•	/links/{id}/delete → delete form

Languages available: EN / PL / DE (via ?lang=en|pl|de).
