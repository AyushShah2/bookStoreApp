package bookstoreapp;

import java.util.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.scene.control.Alert;

/**
 *
 * @author user
 */
public class FileHandler {
    private static ArrayList<Book> bookList = new ArrayList<Book>();
    private static ArrayList<Customer> customerList = new ArrayList<Customer>();
    
    public static void addBook(Book book){
        bookList.add(book);
    }
    
    public static void deleteBook(Book bookToDelete) {
    for (int i = 0; i < bookList.size(); i++) {
        if (bookList.get(i).getBookName().equals(bookToDelete.getBookName())) {
            bookList.remove(i);
            break;
            }
        }
    }
    
    public static ArrayList<Book> getBookList(){
        return bookList;
    }
    
    public static void getBookListFromFile(){
        if(!bookList.isEmpty()){
            bookList = new ArrayList<Book>();
        }
        try {
            
            Scanner scanner = new Scanner(new File("books.txt"));
            while(scanner.hasNextLine()){
                String bookName = scanner.nextLine();
                double price = Double.parseDouble(scanner.nextLine());

                Book book = new Book(bookName, price);

                bookList.add(book);
            }
            scanner.close();
        }
        catch(FileNotFoundException e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR");
            alert.setHeaderText("books.txt not found");
            alert.showAndWait();
        }
        catch(NoSuchElementException e){
            System.out.println("\nNo Books In Store");
        }
    }
    
    public static void saveBookListToFile(){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("books.txt"));
            for(Book book : bookList){
                writer.write(book.getBookName());
                writer.newLine();
                writer.write(String.valueOf(book.getBookPrice()));
                writer.newLine();
            }
            writer.close();
        }
        catch(IOException e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR");
            alert.setHeaderText("books.txt not found");
            alert.showAndWait();
        }
    }
    
    
    public static void addCustomer(Customer customer){
        customerList.add(customer);
    }
    
    public static void deleteCustomer(Customer customerToDelete) {
    for (int i = 0; i < customerList.size(); i++) {
        if (customerList.get(i).getUsername().equals(customerToDelete.getUsername())) {
            customerList.remove(i);
            break;
            }
        }
    }
    
    public static ArrayList<Customer> getCustomerList(){
        return customerList;
    }
    
    
    public static void getCustomerListFromFile(){
        if(!customerList.isEmpty()){
            customerList = new ArrayList<Customer>();
        }
        
        try {
            Scanner scanner = new Scanner(new File("customers.txt"));
            while(scanner.hasNextLine()){
                String username = scanner.nextLine();
                String password = scanner.nextLine();
                int points = Integer.parseInt(scanner.nextLine());

                Customer customer = new Customer(username, password);
                customer.setPoints(points);

                customerList.add(customer);
            }
            scanner.close();
        }
        catch(FileNotFoundException e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR");
            alert.setHeaderText("customers.txt not found");
            alert.showAndWait();
        }
        catch(NoSuchElementException e){
            System.out.println("\nNo Customers Currently Registered");
        }
    }
    
    
    public static void saveCustomerListToFile(){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("customers.txt"));
            for(Customer customer : customerList){
                writer.write(customer.getUsername());
                writer.newLine();
                writer.write(customer.getPassword());
                writer.newLine();
                writer.write(String.valueOf(customer.getPoints()));
                writer.newLine();
            }
            writer.close();
        }
        catch(IOException e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR");
            alert.setHeaderText("customers.txt not found");
            alert.showAndWait();
        }
    }

}
