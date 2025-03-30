package bookstoreapp;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.BooleanProperty;

/**
 *
 * @author Ayush Shah
 */
public class Book {
    private double bookPrice;
    private String bookName;
    private BooleanProperty selected;
    
    public Book(String name, double price){
        bookPrice = price;
        bookName = name;
        this.selected = new SimpleBooleanProperty(false);
    }
    
    public String getBookName(){
        return bookName;        
    }
    
    public double getBookPrice(){
        return bookPrice;
    }
   
    public BooleanProperty selectedProperty() {
        return selected;
    }
    
    public boolean isSelected() {
        return selected.get();
    }

    public void setSelected(boolean value) {
        selected.set(value);
    }
}
