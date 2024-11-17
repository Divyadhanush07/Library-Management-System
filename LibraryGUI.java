import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LibraryGUI extends JFrame {
    private Library library;

    public LibraryGUI() {
        library = new Library();
        library.loadData();
        setupUI();
    }

    private void setupUI() {
        setTitle("Library Management System");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextField titleField = new JTextField(10);
        JTextField authorField = new JTextField(10);
        JTextField genreField = new JTextField(10);

        JButton addButton = new JButton("Add Book");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                String author = authorField.getText();
                String genre = genreField.getText();
                library.addBook(new Book(title, author, genre));
            }
        });

        JPanel addBookPanel = new JPanel();
        addBookPanel.add(new JLabel("Title:"));
        addBookPanel.add(titleField);
        addBookPanel.add(new JLabel("Author:"));
        addBookPanel.add(authorField);
        addBookPanel.add(new JLabel("Genre:"));
        addBookPanel.add(genreField);
        addBookPanel.add(addButton);

        JTextArea availableBooksArea = new JTextArea(10, 30);
        availableBooksArea.setEditable(false);

        JButton viewAvailableButton = new JButton("View Available Books");
        viewAvailableButton.addActionListener(e -> {
            StringBuilder sb = new StringBuilder();
            for (Book book : library.getAvailableBooks()) {
                sb.append(book).append("\n");
            }
            availableBooksArea.setText(sb.toString());
        });

        JPanel availableBooksPanel = new JPanel();
        availableBooksPanel.add(viewAvailableButton);
        availableBooksPanel.add(new JScrollPane(availableBooksArea));

        setLayout(new BorderLayout());
        add(addBookPanel, BorderLayout.NORTH);
        add(availableBooksPanel, BorderLayout.CENTER);

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                library.saveData();
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LibraryGUI gui = new LibraryGUI();
            gui.setVisible(true);
        });
    }
}
