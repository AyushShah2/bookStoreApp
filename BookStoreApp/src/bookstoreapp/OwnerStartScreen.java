package bookstoreapp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author Ayush Shah
 */
public class OwnerStartScreen extends Application {
    private static Owner currentUser;
    
    public static void setUser(Owner user){
        currentUser = user;
    }
    
    @Override
    public void start(Stage ownerStage) {
        
        Label label = new Label("Owner's Dashboard");
        label.setFont(new Font("Arial", 24));
        
        Button booksButton = new Button("Books");
        booksButton.setOnAction(e -> goToBooks(ownerStage));
        
        Button customersButton = new Button("Customers");
        customersButton.setOnAction(e -> goToCustomers(ownerStage));
        
        Button logoutButton = new Button("Logout");
        logoutButton.setOnAction(e -> logoutOwner(ownerStage));
        
        Region invisibleSpace = new Region();
        invisibleSpace.setPrefHeight(120);
        
        Region invisibleSpace2 = new Region();
        invisibleSpace2.setPrefHeight(90);
        
        
        VBox layout = new VBox(20, label, invisibleSpace, booksButton, customersButton, invisibleSpace2, logoutButton);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");
        
        Scene scene = new Scene(layout, 900, 600);
        ownerStage.setTitle("Book Store");
        ownerStage.setScene(scene);
        ownerStage.show();
    }
    
    private void goToBooks(Stage currentStage){
        OwnerBooksScreen ownerBooksScreen = new OwnerBooksScreen();
        bookstoreapp.FileHandler.getBookListFromFile();
        ownerBooksScreen.start(currentStage);
    }
    
    private void goToCustomers(Stage currentStage){
        OwnerCustomersScreen ownerCustomersScreen = new OwnerCustomersScreen();
        bookstoreapp.FileHandler.getCustomerListFromFile();
        ownerCustomersScreen.start(currentStage);
    }
    
    private void logoutOwner(Stage currentStage){  
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
