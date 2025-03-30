
package bookstoreapp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
    public void start(Stage loginStage) {
        Label titleLabel = new Label("Book Store Login");
        titleLabel.setStyle("-fx-font-size: 24px;");
        
        usernameField = new TextField();
        usernameField.setPromptText("Username");
        usernameField.setMaxWidth(200);
        
        passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setMaxWidth(200);
        
        Button loginButton = new Button("Login");
        messageLabel = new Label();        
        
        loginButton.setOnAction(e -> handleLogin());
        
        VBox layout = new VBox(10, titleLabel, usernameField, passwordField, loginButton, messageLabel);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");
        
        Scene scene = new Scene(layout, 900, 600);
        loginStage.setTitle("Book Store");
        loginStage.setScene(scene);
        loginStage.show();
    }

    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();
               
        
        if (username.equals("admin") && password.equals("admin")) {
            User currentUser = new Owner();
            currentUser.login(username, password);
            
            if(currentUser.isLoggedIn()){
                messageLabel.setText("Login successful as Owner");
                System.out.println("Logged in as owner");
                Stage ownerStage = (Stage) usernameField.getScene().getWindow();
                
                OwnerStartScreen ownerStartScreen = new OwnerStartScreen();
                ownerStartScreen.setUser(currentUser);
                ownerStartScreen.start(ownerStage);
            }
        }
        else if (authenticateUser(username, password) != null) {
            User currentUser = authenticateUser(username, password);
            currentUser.login(username, password);

            if(currentUser.isLoggedIn()){
                messageLabel.setText("Login successful as " + username); //ADD IMPLEMENTATION TO GO TO CUSTOMER PAGE
                System.out.print("Logged in as " + username);
            }
        } 
        else {
            messageLabel.setText("Invalid username or password");
            System.out.print("Invalid username or password: " + username);
            usernameField.clear();
            passwordField.clear();
        }
        
    }
    
    private Customer authenticateUser(String username, String password) {
        bookstoreapp.FileHandler.getCustomerListFromFile();
        ArrayList<Customer> customerList = bookstoreapp.FileHandler.getCustomerList();
        
        for(Customer existingCustomer : customerList){
            if ((existingCustomer.getUsername().equals(username)) && existingCustomer.getPassword().equals(password)){
                return existingCustomer;
            }
        }

        return null;
    }


    public static void main(String[] args) {
        launch(args);
    }
    
}
