package bookstoreapp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.text.Font;
import javafx.geometry.Pos;
import java.util.*;
import javafx.scene.layout.Region;

/**
 *
 * @author user
 */
public class OwnerBooksScreen extends Application {

    private TableView<Book> bookTable = new TableView<>();
    private ArrayList<Book> data = bookstoreapp.FileHandler.getBookList();
    
    private TextField nameField = new TextField();
    private TextField priceField = new TextField();

    @Override
    public void start(Stage primaryStage) {
        
        Button addButton = new Button("Add");
        Button deleteButton = new Button("Delete");
        Button backButton = new Button("Back");

        Label label = new Label("Books in Store");
        label.setFont(new Font("Arial", 24));
        
        Region invisibleSpace = new Region();
        invisibleSpace.setPrefWidth(300);
        
        Region invisibleSpace2 = new Region();
        invisibleSpace2.setPrefWidth(300);
        
        TableColumn<Book, String> nameColumn = new TableColumn<>("Book Name");
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBookName()));

        TableColumn<Book, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getBookPrice()).asObject());
        
        nameColumn.setPrefWidth(150);
        priceColumn.setPrefWidth(100);
        
        bookTable.getColumns().addAll(nameColumn, priceColumn);
        bookTable.getItems().addAll(data);
        
        HBox topBox = new HBox(0, invisibleSpace, bookTable, invisibleSpace2);
        topBox.setAlignment(Pos.CENTER);
        
        nameField.setPromptText("Name");
        priceField.setPromptText("Price");
        
        HBox middleBox = new HBox(10, nameField, priceField, addButton);
        middleBox.setAlignment(Pos.CENTER);

        addButton.setOnAction(e -> addBook());
        
        HBox bottomBox = new HBox(10, deleteButton, backButton);
        
        deleteButton.setOnAction(e -> deleteBook());
        backButton.setOnAction(e -> goBackToOwnerStartScreen(primaryStage));
        
        bottomBox.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(40, label, topBox, middleBox, bottomBox);
        vbox.setAlignment(Pos.CENTER);

        bookTable.setPrefHeight(200);

        Scene scene = new Scene(vbox, 900, 600);

        primaryStage.setTitle("Book Store");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addBook() {
        String name = nameField.getText();
        String priceText = priceField.getText();
        double price = 0;

        try {
            price = Double.parseDouble(priceText);
            
            Book newBook = new Book(name, price);
            bookstoreapp.FileHandler.addBook(newBook);
            bookTable.getItems().add(newBook);
            bookstoreapp.FileHandler.saveBookListToFile();
        } catch (NumberFormatException e) {
            showAlert("Invalid Price", "Please enter a valid number for price.");
            return;
        }
        
        nameField.clear();
        priceField.clear();
    }
    
    private void deleteBook(){
        Book selectedBook = bookTable.getSelectionModel().getSelectedItem();
        
        if(selectedBook != null){
            bookstoreapp.FileHandler.deleteBook(selectedBook);
            bookTable.getItems().remove(selectedBook);
            bookstoreapp.FileHandler.saveBookListToFile();
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
