import java.util.Scanner;

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();
        
        System.out.println("Welcome to Library Management System");

        while (true) {
            System.out.println("\nMain Menu");
            System.out.println("1. Login");
            System.out.println("2. Sign Up");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    User loggedInUser = login(library, scanner);
                    if (loggedInUser != null) {
                        if (loggedInUser.isAdmin()) {
                            AdminDashboard adminDashboard = new AdminDashboard(library);
                            adminDashboard.showMenu();
                        } else {
                            UserDashboard userDashboard = new UserDashboard(loggedInUser, library);
                            userDashboard.showMenu();
                        }
                    }
                    break;
                case 2:
                    signUp(library, scanner);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static User login(Library library, Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User loggedInUser = library.login(username, password);
        if (loggedInUser == null) {
            System.out.println("Invalid username or password.");
        }
        return loggedInUser;
    }

    public static void signUp(Library library, Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (library.isUsernameTaken(username)) {
            System.out.println("Username already taken. Please choose another.");
            return;
        }

        User newUser = new User(username, password, false); // Regular user, not admin
        library.addUser(newUser);
        System.out.println("User created successfully. Please login.");
    }
}
