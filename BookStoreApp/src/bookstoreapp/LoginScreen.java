/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package bookstoreapp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author user
 */
public class LoginScreen extends Application {
    private TextField usernameField;
    private PasswordField passwordField;
    private Label messageLabel;
    
    @Override
    public void start(Stage primaryStage) {
        Label titleLabel = new Label("Book Store App Login");
        titleLabel.setStyle("-fx-font-size: 24px;");
        
        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        usernameField.setMaxWidth(200);
        
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setMaxWidth(200);
        
        Button loginButton = new Button("Login");
        messageLabel = new Label();        
        
        loginButton.setOnAction(e -> handleLogin());
        
        VBox layout = new VBox(10, titleLabel, usernameField, passwordField, loginButton);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");
        
        Scene scene = new Scene(layout, 700, 500);
        primaryStage.setTitle("Login Page");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleLogin() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();
        User currentUser = null;
        
        if (username.equals("admin") && password.equals("admin")) {
            messageLabel.setText("Login successful as Owner"); //ADD IMPLEMENTATION TO GO TO OWNER PAGE
            currentUser = new Owner();
            return;
        }
        else if (authenticateUser(username, password) != null) {
            currentUser = new Customer(username, password); //I DONT THINK THIS WILL KEEP TRACK OF POINTS, it might reset it
        } 
        else {
            messageLabel.setText("Invalid username or password");
            usernameField.clear();
            passwordField.clear();
        }
        
        currentUser.login(username, password);
    }
    
    private Customer authenticateUser(String username, String password) {
    try (Scanner scanner = new Scanner(new File("customers.txt"))) {
        String currentUsername = null;
        String currentPassword = null;
        int currentPoints = 0;

        while (scanner.hasNextLine()) {
            // First line: username
            currentUsername = scanner.nextLine().trim();
            // Second line: password
            if (scanner.hasNextLine()) {
                currentPassword = scanner.nextLine().trim();
            } else {
                break; // If there's no second line, break out
            }
            // Third line: points
            if (scanner.hasNextLine()) {
                currentPoints = Integer.parseInt(scanner.nextLine().trim()); // Parse points to integer
            }

            // If credentials match, create and return a Customer object with points
            if (currentUsername.equals(username) && currentPassword.equals(password)) {
                Customer customer = new Customer(currentUsername, currentPassword);
                customer.points = currentPoints;  // Set the points of the customer
                return customer;  // Return the matching customer with points
            }
        }
    } catch (FileNotFoundException e) {
        messageLabel.setText("Error reading customer data");
    } catch (NumberFormatException e) {
        messageLabel.setText("Error parsing points data");
    }
    return null; // Return null if no match is found
}

    
    
//    @Override
//    public void start(Stage primaryStage) {
//        Button btn = new Button();
//        btn.setText("Say 'Hello World'");
//        btn.setOnAction(new EventHandler<ActionEvent>() {
//            
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("Hello World!");
//            }
//        });
//        
//        StackPane root = new StackPane();
//        root.getChildren().add(btn);
//        
//        Scene scene = new Scene(root, 300, 250);
//        
//        primaryStage.setTitle("Hello World!");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
