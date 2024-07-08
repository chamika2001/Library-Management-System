import java.io.*;
import java.util.*;

public class Library {
    private List<User> users;
    private List<Book> books;
    private List<Book> borrowedBooks;

    private static final String USERS_FILE = "users.dat";
    private static final String BOOKS_FILE = "books.dat";
    private static final String BORROWED_BOOKS_FILE = "borrowed_books.dat";

    public Library() {
        this.users = new ArrayList<>();
        this.books = new ArrayList<>();
        this.borrowedBooks = new ArrayList<>();
        loadUsersFromFile();
        loadBooksFromFile();
        loadBorrowedBooksFromFile();
    }

    public void addUser(User user) {
        users.add(user);
        saveUsersToFile();
    }

    public User getUser(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public User login(String username, String password) {
        User user = getUser(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public boolean isUsernameTaken(String username) {
        return getUser(username) != null;
    }

    public void addBook(Book book) {
        books.add(book);
        saveBooksToFile();
    }

    public void updateBookDetails(String isbn, String newTitle, String newAuthor) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                book.setTitle(newTitle);
                book.setAuthor(newAuthor);
                saveBooksToFile();
                return;
            }
        }
        System.out.println("Book with ISBN " + isbn + " not found.");
    }

    public void removeBook(String isbn) {
        Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.getIsbn().equals(isbn)) {
                iterator.remove();
                saveBooksToFile();
                return;
            }
        }
        System.out.println("Book with ISBN " + isbn + " not found.");
    }

    public List<Book> getAvailableBooks() {
        List<Book> availableBooks = new ArrayList<>();
        for (Book book : books) {
            if (!borrowedBooks.contains(book)) {
                availableBooks.add(book);
            }
        }
        return availableBooks;
    }

    public void borrowBook(User user, Book book) {
        borrowedBooks.add(book);
        saveBorrowedBooksToFile();
    }

    public void returnBook(User user, Book book) {
        borrowedBooks.remove(book);
        saveBorrowedBooksToFile();
    }

    private void loadUsersFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(USERS_FILE))) {
            Object obj = ois.readObject();
            if (obj instanceof List<?>) {
                users = (List<User>) obj; // Type casting with generic type safety
            } else {
                System.out.println("Invalid data format in users file.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Users file not found. Starting with an empty user list.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void saveUsersToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USERS_FILE))) {
            oos.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadBooksFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(BOOKS_FILE))) {
            Object obj = ois.readObject();
            if (obj instanceof List<?>) {
                books = (List<Book>) obj; // Type casting with generic type safety
            } else {
                System.out.println("Invalid data format in books file.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Books file not found. Starting with an empty book list.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void saveBooksToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(BOOKS_FILE))) {
            oos.writeObject(books);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadBorrowedBooksFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(BORROWED_BOOKS_FILE))) {
            Object obj = ois.readObject();
            if (obj instanceof List<?>) {
                borrowedBooks = (List<Book>) obj; // Type casting with generic type safety
            } else {
                System.out.println("Invalid data format in borrowed books file.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Borrowed books file not found. Starting with an empty borrowed books list.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void saveBorrowedBooksToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(BORROWED_BOOKS_FILE))) {
            oos.writeObject(borrowedBooks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }
}
