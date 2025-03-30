package bookstoreapp;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Pos;
import java.util.stream.Collectors;

/**
 *
 * @author Ayush Shah
 */
public class CustomerStartScreen extends Application {
    private static Customer currentUser;
    private TableView<Book> bookTable = new TableView<>();
    private ArrayList<Book> data = bookstoreapp.FileHandler.getBookList();
    ArrayList<Book> selectedBooks = new ArrayList<>(data.stream().filter(Book::isSelected).collect(Collectors.toList()));
    private Label errorLabel;
    
    public static void setUser(Customer user){
        currentUser = user;
    }
    
    @Override
    public void start(Stage customerStage) {
        
        Label message = new Label("Welcome " + currentUser.getUsername() + ". You have " + currentUser.getPoints() + " points. Your status is " + currentUser.getStatus() + ".");
        message.setFont(new Font("Arial", 24));
        
        Label label = new Label("Books in Store");
        label.setFont(new Font("Arial", 20));
        
        Region invisibleSpace = new Region();
        invisibleSpace.setPrefWidth(300);
        
        Region invisibleSpace2 = new Region();
        invisibleSpace2.setPrefWidth(300);
        
        errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: red;");
        
        bookTable.setEditable(true);
        
        TableColumn<Book, Boolean> selectColumn = new TableColumn<>("Select");
        selectColumn.setCellValueFactory(cellData -> cellData.getValue().selectedProperty());
        selectColumn.setCellFactory(CheckBoxTableCell.forTableColumn(selectColumn));
        selectColumn.setEditable(true);
        
        TableColumn<Book, String> nameColumn = new TableColumn<>("Book Name");
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBookName()));
        
        TableColumn<Book, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getBookPrice()).asObject());

        selectColumn.setPrefWidth(55);
        nameColumn.setPrefWidth(133);
        priceColumn.setPrefWidth(81);
        
        bookTable.getColumns().addAll(nameColumn, priceColumn, selectColumn);
        bookTable.getItems().addAll(data);
        
        HBox topBox = new HBox(0, invisibleSpace, bookTable, invisibleSpace2);
        topBox.setAlignment(Pos.CENTER);
        
        
        Button buyBookButton = new Button("Buy Books");
        
        Button buyBookWithPointsButton = new Button("Redeem Points and Buy");
        
        try{
            buyBookButton.setOnAction(e -> buyBooks(customerStage, getSelectedBooks(), false));
            buyBookWithPointsButton.setOnAction(e -> buyBooks(customerStage, getSelectedBooks(), true));
        }
        catch(NoClassDefFoundError e){
            System.out.println("Please click on item before clicking buy.");
        }
        
        Button logoutButton = new Button("Logout");
        logoutButton.setOnAction(e -> logoutCustomer(customerStage));
        
        HBox bottomBox = new HBox(20, buyBookButton, buyBookWithPointsButton, logoutButton);
        bottomBox.setAlignment(Pos.CENTER);
        
        VBox bottomBoxWithLabel = new VBox(10, errorLabel, bottomBox);
        bottomBoxWithLabel.setAlignment(Pos.CENTER);
        
        VBox layout = new VBox(20, message, label, topBox, bottomBoxWithLabel);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;"); 
        
        
        Scene scene = new Scene(layout, 900, 600);
        customerStage.setTitle("Book Store");
        customerStage.setScene(scene);
        customerStage.show();
    }
    
    private ArrayList<Book> getSelectedBooks(){
        ArrayList<Book> selectedBooks = new ArrayList<>();    
        for (Book book : bookTable.getItems()) {
            if (book.isSelected()) {
                selectedBooks.add(book);
            }
        }
        return selectedBooks;
    }
    
    private void buyBooks(Stage currentStage, ArrayList<Book> selectedBooks, boolean buyWithPoints){
        if(selectedBooks.isEmpty()){
            errorLabel.setText("Select a Book to Purchase");
        }
        else{
            CustomerCostScreen customerCostScreen = new CustomerCostScreen();
            customerCostScreen.purchaseInfo((Customer) currentUser, selectedBooks, buyWithPoints);
            customerCostScreen.start(currentStage);
        }
    }
    
    
    private void logoutCustomer(Stage currentStage){  
        try{
            currentUser.setLogout();
        }
        catch(NullPointerException e){
            System.out.println("User Not Logged In");
        }

        LoginScreen loginScreen = new LoginScreen();
        loginScreen.start(currentStage);
    }


    public static void main(String[] args) {
        launch(args);
    }
    
}
