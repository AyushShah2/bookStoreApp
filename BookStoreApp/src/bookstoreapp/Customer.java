
package bookstoreapp;

/**
 *
 * @author user
 */
public class Customer extends User{
    private int points;
    
    Customer(String username, String password){
        points = 0;
        this.username = username;
        this.password = password;
    }
    
    @Override
    public String getUsername(){
        return username;
    }
    
    public String getPassword(){
        return password;
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
    
    public void setPoints(int points){
        this.points = points;
    }
    
    public int getPoints(){
        return points;
    }
    
}
