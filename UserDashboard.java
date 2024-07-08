import java.util.List;
import java.util.Scanner;

public class UserDashboard {
    private User user;
    private Library library;
    private Scanner scanner;

    public UserDashboard(User user, Library library) {
        this.user = user;
        this.library = library;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("\nUser Dashboard");
            System.out.println("1. View All Books");
            System.out.println("2. View Available Books");
            System.out.println("3. View Borrowed Books");
            System.out.println("4. Borrow Book");
            System.out.println("5. Return Book");
            System.out.println("6. Logout");
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
                    borrowBook();
                    break;
                case 5:
                    returnBook();
                    break;
                case 6:
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
            System.out.println("You haven't borrowed any books yet.");
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

    public void borrowBook() {
        System.out.print("Enter ISBN of the book to borrow: ");
        String isbn = scanner.nextLine();

        Book bookToBorrow = findBookByISBN(isbn);
        if (bookToBorrow != null && !library.getBorrowedBooks().contains(bookToBorrow)) {
            library.borrowBook(user, bookToBorrow);
            System.out.println("Book borrowed successfully.");
        } else {
            System.out.println("Book not available for borrowing.");
        }
    }

    public void returnBook() {
        System.out.print("Enter ISBN of the book to return: ");
        String isbn = scanner.nextLine();

        Book bookToReturn = findBookByISBN(isbn);
        if (bookToReturn != null && library.getBorrowedBooks().contains(bookToReturn)) {
            library.returnBook(user, bookToReturn);
            System.out.println("Book returned successfully.");
        } else {
            System.out.println("You have not borrowed this book.");
        }
    }

    private Book findBookByISBN(String isbn) {
        List<Book> books = library.getBooks();
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null;
    }
}
