package bookstoreapp;

/**
 *
 * @author user
 */
public class Book {
    private double bookPrice;
    private String bookName;
    private boolean isBought;
    
    public Book(String name, double price){
        bookPrice = price;
        bookName = name;
    }
    
    public String getBookName(){
        return bookName;        
    }
    
    public double getBookPrice(){
        return bookPrice;
    }
    
    public boolean isBookBought(){
        return isBought;
    }
    
    public void buyBook(){
        isBought = true;
    }
}
