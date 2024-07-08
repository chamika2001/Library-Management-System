import java.util.List;
import java.util.Scanner;

public class AdminDashboard {
    private Library library;
    private Scanner scanner;

    public AdminDashboard(Library library) {
        this.library = library;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("\nAdmin Dashboard");
            System.out.println("1. View All Books");
            System.out.println("2. View Available Books");
            System.out.println("3. View Borrowed Books");
            System.out.println("4. Add Book");
            System.out.println("5. Update Book Details");
            System.out.println("6. Remove Book");
            System.out.println("7. Logout");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    viewAllBooks();
                    break;
                case 2:
                    viewAvailableBooks();
                    break;
                case 3:
                    viewBorrowedBooks();
                    break;
                case 4:
                    addBook();
                    break;
                case 5:
                    updateBookDetails();
                    break;
                case 6:
                    removeBook();
                    break;
                case 7:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void viewAllBooks() {
        List<Book> books = library.getBooks();

        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }

        System.out.println("\nAll Books:");
        displayBooks(books);
    }

    public void viewAvailableBooks() {
        List<Book> availableBooks = library.getAvailableBooks();

        if (availableBooks.isEmpty()) {
            System.out.println("No books available in the library.");
            return;
        }

        System.out.println("\nAvailable Books:");
        displayBooks(availableBooks);
    }

    public void viewBorrowedBooks() {
        List<Book> borrowedBooks = library.getBorrowedBooks();

        if (borrowedBooks.isEmpty()) {
            System.out.println("No books currently borrowed.");
            return;
        }

        System.out.println("\nBorrowed Books:");
        displayBooks(borrowedBooks);
    }

    private void displayBooks(List<Book> books) {
        System.out.println("+---------------------------------------------------------------+");
        System.out.printf("| %-20s | %-30s | %-15s |\n", "ISBN", "Title", "Author");
        System.out.println("+----------------------+----------------------------------------+-----------------+");

        for (Book book : books) {
            System.out.printf("| %-20s | %-30s | %-15s |\n", book.getIsbn(), book.getTitle(), book.getAuthor());
        }

        System.out.println("+---------------------------------------------------------------+");
    }

    public void addBook() {
        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author: ");
        String author = scanner.nextLine();

        Book newBook = new Book(isbn, title, author);
        library.addBook(newBook);
        System.out.println("Book added successfully.");
    }

    public void updateBookDetails() {
        System.out.print("Enter ISBN of the book to update: ");
        String isbn = scanner.nextLine();
        System.out.print("Enter new title: ");
        String newTitle = scanner.nextLine();
        System.out.print("Enter new author: ");
        String newAuthor = scanner.nextLine();

        library.updateBookDetails(isbn, newTitle, newAuthor);
    }

    public void removeBook() {
        System.out.print("Enter ISBN of the book to remove: ");
        String isbn = scanner.nextLine();

        library.removeBook(isbn);
    }
}
