
package bookstoreapp;

/**
 *
 * @author user
 */
public class Customer extends User{
    int points;
    
    Customer(String username, String password){
        points = 0;
        this.username = username;
        this.password = password;
    }
    
    @Override
    public String getUsername(){
        return username;
    }
    
    @Override
    public void login(String username, String password){
        if (username.equals(this.username) && password.equals(this.password)){
            loggedIn = true;
        }
    }
    
    @Override
    public void setLogout(){
        loggedIn = false;
    }
    
    @Override
    public boolean isLoggedIn(){
        return loggedIn;
    }
}
