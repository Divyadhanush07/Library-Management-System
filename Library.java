import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books;
    private List<Borrower> borrowers;
    private static final String FILE_NAME = "library_data.txt";

    public Library() {
        books = new ArrayList<>();
        borrowers = new ArrayList<>();
    }

    public void addBook(Book book) { books.add(book); }
    public void removeBook(Book book) { books.remove(book); }
    public void borrowBook(String name, Book book) {
        if (book.isAvailable()) {
            book.setAvailable(false);
            Borrower borrower = new Borrower(name, book, java.time.LocalDate.now().plusWeeks(2));
            borrowers.add(borrower);
            System.out.println(name + " successfully borrowed " + book.getTitle());
        } else {
            System.out.println("Book is not available.");
        }
    }

    public void returnBook(Book book) {
        book.setAvailable(true);
        borrowers.removeIf(borrower -> borrower.getBorrowedBook().equals(book));
        System.out.println(book.getTitle() + " has been returned.");
    }

    public List<Book> getAvailableBooks() {
        List<Book> availableBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.isAvailable()) {
                availableBooks.add(book);
            }
        }
        return availableBooks;
    }

    public List<Borrower> getBorrowers() { return borrowers; }

    public void saveData() {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            for (Book book : books) {
                writer.write(book.getTitle() + "," + book.getAuthor() + "," + book.getGenre() + "," + book.isAvailable() + "\n");
            }
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                Book book = new Book(parts[0], parts[1], parts[2]);
                book.setAvailable(Boolean.parseBoolean(parts[3]));
                books.add(book);
            }
            System.out.println("Data loaded successfully.");
        } catch (IOException e) {
            System.out.println("No existing data found.");
        }
    }
}
