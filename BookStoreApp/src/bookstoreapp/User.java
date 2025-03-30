package bookstoreapp;

/**
 *
 * @author Ayush Shah
 */
abstract class User {
    String username;
    String password;
    boolean loggedIn;
    
    public abstract String getUsername();
    public abstract void login(String username, String password);
    public abstract void setLogout();
    public abstract boolean isLoggedIn();
}
