import java.time.LocalDate;

public class Borrower {
    private String name;
    private Book borrowedBook;
    private LocalDate dueDate;

    public Borrower(String name, Book borrowedBook, LocalDate dueDate) {
        this.name = name;
        this.borrowedBook = borrowedBook;
        this.dueDate = dueDate;
    }

    public String getName() { return name; }
    public Book getBorrowedBook() { return borrowedBook; }
    public LocalDate getDueDate() { return dueDate; }

    @Override
    public String toString() {
        return name + " borrowed \"" + borrowedBook.getTitle() + "\" due on " + dueDate;
    }
}
