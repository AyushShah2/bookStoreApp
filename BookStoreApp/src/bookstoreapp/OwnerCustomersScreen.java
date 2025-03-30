package bookstoreapp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.text.Font;
import javafx.geometry.Pos;
import java.util.*;
import javafx.scene.layout.Region;

/**
 *
 * @author user
 */
public class OwnerCustomersScreen extends Application {

    private TableView<Customer> customerTable = new TableView<>();
    private ArrayList<Customer> data = bookstoreapp.FileHandler.getCustomerList();
    
    private TextField usernameField = new TextField();
    private TextField passwordField = new TextField();

    @Override
    public void start(Stage primaryStage) {
        
        Button addButton = new Button("Add");
        Button deleteButton = new Button("Delete");
        Button backButton = new Button("Back");

        Label label = new Label("Customers");
        label.setFont(new Font("Arial", 24));
        
        Region invisibleSpace = new Region();
        invisibleSpace.setPrefWidth(230);
        
        Region invisibleSpace2 = new Region();
        invisibleSpace2.setPrefWidth(230);
        
        TableColumn<Customer, String> usernameColumn = new TableColumn<>("Username");
        usernameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUsername()));
        
        TableColumn<Customer, String> passwordColumn = new TableColumn<>("Password");
        passwordColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPassword()));

        TableColumn<Customer, Integer> pointsColumn = new TableColumn<>("Points");
        pointsColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getPoints()).asObject());
        
        usernameColumn.setPrefWidth(150);
        passwordColumn.setPrefWidth(150);
        pointsColumn.setPrefWidth(100);
        
        customerTable.getColumns().addAll(usernameColumn, passwordColumn, pointsColumn);
        customerTable.getItems().addAll(data);
        
        HBox topBox = new HBox(0, invisibleSpace, customerTable, invisibleSpace2);
        topBox.setAlignment(Pos.CENTER);
        
        usernameField.setPromptText("Username");
        passwordField.setPromptText("Password");
        
        HBox middleBox = new HBox(10, usernameField, passwordField, addButton);
        middleBox.setAlignment(Pos.CENTER);

        addButton.setOnAction(e -> addCustomer());
        
        HBox bottomBox = new HBox(10, deleteButton, backButton);
        
        deleteButton.setOnAction(e -> deleteCustomer());
        backButton.setOnAction(e -> goBackToOwnerStartScreen(primaryStage));
        
        bottomBox.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(40, label, topBox, middleBox, bottomBox);
        vbox.setAlignment(Pos.CENTER);

        customerTable.setPrefHeight(200);

        Scene scene = new Scene(vbox, 900, 600);

        primaryStage.setTitle("Book Store");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addCustomer() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        
        Customer newCustomer = new Customer(username, password);
        bookstoreapp.FileHandler.addCustomer(newCustomer);
        customerTable.getItems().add(newCustomer);
        bookstoreapp.FileHandler.saveCustomerListToFile();
        
        usernameField.clear();
        passwordField.clear();
    }
    
    private void deleteCustomer(){
        Customer selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
        
        if(selectedCustomer != null){
            bookstoreapp.FileHandler.deleteCustomer(selectedCustomer);
            customerTable.getItems().remove(selectedCustomer);
            bookstoreapp.FileHandler.saveCustomerListToFile();
        }
        else{
            showAlert("No Book Selected", "Please select a book to delete.");
        }
        
        
    }

    private void goBackToOwnerStartScreen(Stage currentStage){
        OwnerStartScreen ownerStartScreen = new OwnerStartScreen();
        ownerStartScreen.start(currentStage);
    }
    
    
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
