
package bookstoreapp;

/**
 *
 * @author user
 */
public class Owner extends User{
    
    Owner(){
        this.username = "admin";
        this.password = "admin";
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
