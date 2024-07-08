import java.io.Serializable;

public class LoginSystem implements Serializable {
    private static final long serialVersionUID = 1L;
    private Library library;
    private static final String ADMIN_USERNAME = "uwulibrary";
    private static final String ADMIN_PASSWORD = "1234";

    public LoginSystem(Library library) {
        this.library = library;
    }

    public User login(String username, String password) {
        if (username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD)) {
            return new User(ADMIN_USERNAME, ADMIN_PASSWORD, true);
        }

        User user = library.getUser(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }

        return null;
    }

    public boolean signUp(String username, String password, boolean isAdmin) {
        if (isAdmin) {
            System.out.println("Admin creation is not allowed.");
            return false;
        }
        User user = new User(username, password, false);
        library.addUser(user);
        return true;
    }
}
