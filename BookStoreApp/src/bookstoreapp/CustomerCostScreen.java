package bookstoreapp;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author Ayush Shah
 */
public class CustomerCostScreen extends Application {
    private static Customer currentUser;
    private ArrayList<Book> booksBought = new ArrayList<Book>();
    private boolean isBuyingWithPoints;
    
    public void purchaseInfo(Customer user, ArrayList<Book> selectedBooks, boolean buyWithPoints){
        currentUser = user;
        booksBought = selectedBooks;
        isBuyingWithPoints = buyWithPoints;
    }
    
    @Override
    public void start(Stage ownerStage) {
        double totalCost = 0;
        
        for(Book book : booksBought){
            totalCost += book.getBookPrice();
            bookstoreapp.FileHandler.deleteBook(book);
        }
        
        if(isBuyingWithPoints){
            totalCost = currentUser.getStatus().calculateFinalCost(currentUser, totalCost);
        }
        currentUser.getStatus().updatePoints(currentUser, totalCost);
        
        bookstoreapp.FileHandler.saveCustomerListToFile();
        bookstoreapp.FileHandler.saveBookListToFile();
        
        Label label = new Label("Total Cost: " + totalCost + "\nCurrent Points: " + currentUser.getPoints() + "\nYour Status is " + currentUser.getStatus());
        label.setFont(new Font("Arial", 20));
        

        Button logoutButton = new Button("Logout");
        logoutButton.setOnAction(e -> logoutOwner(ownerStage));
        
        Region invisibleSpace = new Region();
        invisibleSpace.setPrefHeight(120);
        
        Region invisibleSpace2 = new Region();
        invisibleSpace2.setPrefHeight(90);
        
        
        VBox layout = new VBox(20, label, invisibleSpace, invisibleSpace2, logoutButton);
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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
