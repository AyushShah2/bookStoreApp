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
 * @author Ayush Shah
 */
public class OwnerBooksScreen extends Application {

    private TableView<Book> bookTable = new TableView<>();
    private ArrayList<Book> data = bookstoreapp.FileHandler.getBookList();
    
    private TextField nameField = new TextField();
    private TextField priceField = new TextField();
    private Label errorLabel;

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
        
        errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: red;");
        
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
        
        VBox middleBoxWithLabel = new VBox(10, errorLabel, middleBox);
        middleBoxWithLabel.setAlignment(Pos.CENTER);

        addButton.setOnAction(e -> addBook());
        
        HBox bottomBox = new HBox(10, deleteButton, backButton);
        
        deleteButton.setOnAction(e -> deleteBook());
        backButton.setOnAction(e -> goBackToOwnerStartScreen(primaryStage));
        
        bottomBox.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(40, label, topBox, middleBoxWithLabel, bottomBox);
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
            if(name.trim().equals("")){
                throw new Exception();
            }
            price = Double.parseDouble(priceText);
            
            Book newBook = new Book(name, price);
            bookstoreapp.FileHandler.addBook(newBook);
            bookTable.getItems().add(newBook);
            bookstoreapp.FileHandler.saveBookListToFile();
        } catch (Exception e) {
            if (e instanceof NumberFormatException){
                errorLabel.setText("Enter a Valid Number");
            }
            else{
                errorLabel.setText("Enter a Proper Name");
            }
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
            errorLabel.setText("Select a Book to Delete");
        }
        
        
    }

    private void goBackToOwnerStartScreen(Stage currentStage){
        OwnerStartScreen ownerStartScreen = new OwnerStartScreen();
        ownerStartScreen.start(currentStage);
    }
    

    public static void main(String[] args) {
        launch(args);
    }
}
