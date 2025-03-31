# BookStoreApp - README

## Overview
**BookStoreApp** is a graphical user interface (GUI) based bookstore application developed as part of the COE528 course project for Winter 2025. The application allows the **Owner** to manage books and customers, while **Customers** can buy books, redeem points, and track their status (Silver/Gold). The application follows the State Design Pattern for managing different user states and interactions. The program is developed using **Java 8**, and it requires **JavaFX** for the GUI.

## Features
- **Owner Functionalities:**
  - Login as `admin` (username: `admin`, password: `admin`).
  - Manage books (Add/Remove).
  - Manage customers (Add/Remove).
  - Save and load data to/from `books.txt` and `customers.txt`.

- **Customer Functionalities:**
  - Login with a valid username and password.
  - View and buy books with points and status.
  - Redeem points when purchasing books.

## Prerequisites
To run this application, you need:
- **Java Development Kit (JDK) 8** (Ensure you have Java 8 installed).
- **JavaFX SDK 8** for GUI components.
- **NetBeans IDE** (or another Java IDE) configured to use Java 8 and JavaFX.

### Configure Project in NetBeans
1. Open **NetBeans** IDE.
2. Create a new **Java Project** or open the existing project folder `BookStoreApp`.
3. Ensure that **Java 8** and **JavaFX 8** are configured for your project:
   - Right-click the project in NetBeans → **Properties**.
   - In the **Libraries** section, ensure that the **JavaFX 8 SDK** is added as a library.
   - In the **Run** section, add the path to the `lib` folder in your JavaFX SDK.

### Step 4: Build and Run the Application
1. In NetBeans, build the project by selecting **Build Project**.
2. To run the application, select **Run Project**. This will launch the login screen.
3. Enter the `admin` credentials to access the Owner's functionalities, or use any registered customer credentials to access Customer features.

### Step 5: Compile into a JAR
1. Right-click on the project in **NetBeans** and select **Clean and Build**.
2. This will generate a `.jar` file inside the `dist` folder.

## Running the JAR File

To run the `.jar` file on a system with Java 8 installed:
1. Open a terminal or command prompt.
2. Navigate to the folder containing the `.jar` file.
3. Execute the following command:

   ```bash
   java -jar BookStoreApp.jar
