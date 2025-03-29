
package bookstoreapp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
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
        primaryStage.setTitle("Login Page");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();
               
        
        if (username.equals("admin") && password.equals("admin")) {
            User currentUser = new Owner();
            currentUser.login(username, password);
            
            if(currentUser.isLoggedIn()){
                messageLabel.setText("Login successful as Owner"); //ADD IMPLEMENTATION TO GO TO OWNER PAGE
                System.out.print("Logged in as owner");
                //BREAK THIS CODE AND GO TO OWNER PAGE
            }
        }
        else if (authenticateUser(username, password) != null) {
            User currentUser = authenticateUser(username, password);
            currentUser.login(username, password);

            if(currentUser.isLoggedIn()){
                messageLabel.setText("Login successful as " + username); //ADD IMPLEMENTATION TO GO TO CUSTOMER PAGE
                System.out.print("Logged in as " + username);
                //BREAK THIS CODE AND GO TO CUSTOMER PAGE
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
        File file = new File("customers.txt");

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String currentUsername = scanner.nextLine();
                if (!scanner.hasNextLine()) break;
                String currentPassword = scanner.nextLine();
                if (!scanner.hasNextLine()) break; 
                int currentPoints = Integer.parseInt(scanner.nextLine());

                
                if (currentUsername.equals(username) && currentPassword.equals(password)) {
                    Customer existingCustomer = new Customer(currentUsername, currentPassword);
                    existingCustomer.setPoints(currentPoints);
                    return existingCustomer;
                }
            }
        } catch (FileNotFoundException e) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("ERROR");
            alert.setHeaderText("customers.txt not found");
            alert.showAndWait();
        } catch (NumberFormatException e) {
            messageLabel.setText("Error: Invalid points format in file");
        }

        return null;
    }


    public static void main(String[] args) {
        launch(args);
    }
    
}
