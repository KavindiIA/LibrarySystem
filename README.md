# Library Management System

## Overview
The Library Management System is a comprehensive Java-based desktop application developed using Eclipse IDE. This project leverages Java Swing for the graphical user interface and is designed to facilitate the management of library operations such as book borrowing, member registration, and inventory tracking. The application incorporates robust features such as secure authentication, email verification, and reporting capabilities.

## Features
- **User Authentication:**
  - Secure login system with SHA-256 hashed passwords for enhanced security.
  - Email verification for password reset functionality.

- **Book Management:**
  - Add, update, and delete book records.
  - Search books by bookID.

- **Account Management:**
  - Register new members.
  - Update and delete member information.

- **Borrowing and Returning Books:**
  - Track book borrow and return activities.
  - Maintain a history of transactions.

- **Reporting:**
  - Generate detailed reports using JasperReports.

## Technologies Used
- **Programming Language:** Java (JDK 22)
- **GUI Framework:** Java Swing
- **Database:** MySQL
- **Hashing Algorithm:** SHA-256 for password security
- **Email Service:** Integrated email functionality for password reset
- **Reporting Tool:** JasperReports

## Prerequisites
1. **Java Development Kit:** JDK 22 or later.
2. **Eclipse IDE:** Ensure Eclipse is installed and configured for Java development.
3. **MySQL Server:** Set up and configure MySQL for database connectivity.
4. **JasperReports Library:** Add the required JasperReports pom.xml files to the project.
5. **Email Service Configuration:** Configure SMTP settings in the application for email functionality.

## Usage
1. Launch the application from Eclipse or as a standalone JAR file.
2. Log in with your credentials or register as a new user.
3. Use the navigation menu to access features such as book management, account management, and transaction tracking.
4. Reset your password using the "Forgot Password" option, which sends a verification email to your registered email address.
5. Generate reports from the "Reports" section for detailed insights.

## Highlights
- The application ensures secure authentication using SHA-256 hashed passwords.
- Password reset emails are sent via an integrated SMTP service.
- Reports are dynamically generated using JasperReports.
- Robust database connectivity with MySQL for efficient data management.
