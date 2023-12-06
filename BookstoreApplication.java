import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class BookstoreApplication {

    private static final String URL = "jdbc:postgresql://localhost:5432/Bookstore";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Guni2009";

    private static Scanner scanner = new Scanner(System.in);

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/Bookstore", "postgres", "Guni2009");
    }

    public static void main(String[] args) {
        try (Connection connection = getConnection()) {
            int choice;

            while (true) {
                printMenu();
                choice = getUserChoice();

                switch (choice) {
                    case 1:
                        MetadataManager.showTableInfo(connection);
                        break;
                    case 2:
                        MetadataManager.showKeyInfo(connection);
                        break;
                    case 3:
                        createBook(connection);
                        break;
                    case 4:
                        List<Object[]> booksData = retrieveBooksData(connection);
                        displayBooksData(booksData);
                        break;
                    case 5:
                        updateBook(connection);
                        break;
                    case 6:
                        deleteBook(connection);
                        break;
                    case 7:
                        int bookId = getBookIdFromUser();
                        Book book = BookManager.getBook(connection, bookId);
                        displayBookDetails(book);
                        break;
                    case 8:
                        createAuthor(connection);
                        break;
                    case 9:
                        getAuthorDetails(connection);
                        break;
                    case 10:
                        updateAuthor(connection);
                        break;
                    case 11:
                        deleteAuthor(connection);
                        break;
                    case 12:
                        createBookWithAuthor(connection);
                        break;
                    case 13:
                        deleteBookWithAuthor(connection);
                        break;
                    case 14:
                        createCustomer(connection);
                        break;
                    case 15:
                        getCustomerDetails(connection);
                        break;
                    case 16:
                        updateCustomer(connection);
                        break;
                    case 17:
                        deleteCustomer(connection);
                        break;
                    case 18:
                        createOrder(connection);
                        break;
                    case 19:
                        getOrderDetails(connection);
                        break;
                    case 20:
                        deleteOrder(connection);
                        break;
                    case 21:
                        createBookInOrder(connection);
                        break;
                    case 22:
                        deleteBookFromOrder(connection);
                        break;
                    case 0:
                        System.out.println("Exiting the program. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    private static void printMenu() {
        System.out.println("Choose an option:");
        System.out.println("1. Get Information about Tables");
        System.out.println("2. Get Information about Primary and Foreign Keys of Tables");
        System.out.println("3. Create Book");
        System.out.println("4. Display Data of All Books");
        System.out.println("5. Update Book");
        System.out.println("6. Delete Book");
        System.out.println("7. Get Book Details");
        System.out.println("8. Create Author");
        System.out.println("9. Get Author Details");
        System.out.println("10. Update Author");
        System.out.println("11. Delete Author");
        System.out.println("12. Link Book with Author");
        System.out.println("13. Unlink Book with Author");
        System.out.println("14. Create Customer");
        System.out.println("15. Get Customer Details");
        System.out.println("16. Update Customer");
        System.out.println("17. Delete Customer");
        System.out.println("18. Create Order");
        System.out.println("19. Get Order Details");
        System.out.println("20. Delete Order");
        System.out.println("21. Link Book with Order");
        System.out.println("22. Unlink Book with Order");
        System.out.println("0. Exit");
    }

    private static int getUserChoice() {
        System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }

    private static void createBook(Connection connection) {
        System.out.println("Enter book details:");

        System.out.print("Book Id: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Title: ");
        String title = scanner.nextLine();
        System.out.print("Quantity in Stock: ");
        int quantityInStock = scanner.nextInt();
        System.out.print("Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Publish Date: ");
        String publishDate = scanner.nextLine();
        System.out.print("Genre: ");
        String genre = scanner.nextLine();
        System.out.print("Description: ");
        String description = scanner.nextLine();

        Book book = new Book(bookId, title, quantityInStock, price, publishDate, genre, description);

        BookManager.createBook(connection, book);
    }

    private static List<Object[]> retrieveBooksData(Connection connection) {
        return BookManager.retrieveBooksData(connection);
    }

    private static void displayBooksData(List<Object[]> booksData) {
        System.out.println("Books Data:");

        for (Object[] bookData : booksData) {
            System.out.println("Book Id: " + bookData[0]);
            System.out.println("Title: " + bookData[1]);
            System.out.println("Quantity in Stock: " + bookData[2]);
            System.out.println("Price: " + bookData[3]);
            System.out.println("Publish Date: " + bookData[4]);
            System.out.println("Genre: " + bookData[5]);
            System.out.println("Description: " + bookData[6]);
            System.out.println("Author Id: " + bookData[7]);
            System.out.println("Author Name: " + bookData[8]);
            System.out.println("Customer Id: " + bookData[9]);
            System.out.println("Customer Name: " + bookData[10]);
            System.out.println("Email: " + bookData[11]);
            System.out.println("Order Id: " + bookData[12]);
            System.out.println("Order Date: " + bookData[13]);
            System.out.println("Quantity Ordered: " + bookData[14]);
            System.out.println("Total Amount: " + bookData[15]);
            System.out.println();
        }
    }

    private static void updateBook(Connection connection) {
        System.out.println("Enter book details for updating:");

        System.out.print("Book Id: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Title: ");
        String title = scanner.nextLine();
        System.out.print("Quantity in Stock: ");
        int quantityInStock = scanner.nextInt();
        System.out.print("Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Publish Date: ");
        String publishDate = scanner.nextLine();
        System.out.print("Genre: ");
        String genre = scanner.nextLine();
        System.out.print("Description: ");
        String description = scanner.nextLine();

        Book book = new Book(bookId, title, quantityInStock, price, publishDate, genre, description);

        BookManager.updateBook(connection, book);
    }

    private static void deleteBook(Connection connection) {
        System.out.println("Enter book Id for deletion:");
        int bookId = scanner.nextInt();

        BookManager.deleteBook(connection, bookId);
    }

    private static int getBookIdFromUser() {
        System.out.print("Enter Book Id: ");
        return scanner.nextInt();
    }

    private static void displayBookDetails(Book book) {
        if (book != null) {
            System.out.println("Book Details:");
            System.out.println("Book Id: " + book.getBookId());
            System.out.println("Title: " + book.getTitle());
            System.out.println("Quantity in Stock: " + book.getQuantityInStock());
            System.out.println("Price: " + book.getPrice());
            System.out.println("Publish Date: " + book.getPublishDate());
            System.out.println("Genre: " + book.getGenre());
            System.out.println("Description: " + book.getDescription());
            System.out.println();
        }
    }

    private static void createAuthor(Connection connection) {
        System.out.println("Enter author details:");

        System.out.print("Author Id: ");
        String authorId = scanner.next();
        System.out.print("Author Name: ");
        String authorName = scanner.next();

        Author author = new Author(authorId, authorName);

        try {
            AuthorManager.createAuthor(connection, author);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void getAuthorDetails(Connection connection) {
        System.out.print("Enter Author Id: ");
        String authorId = scanner.next();

        try {
            Author author = AuthorManager.getAuthor(connection, authorId);
            displayAuthorDetails(author);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updateAuthor(Connection connection) {
        System.out.print("Enter Author Id for updating: ");
        String authorId = scanner.next();
        System.out.print("Enter new Author Name: ");
        String newAuthorName = scanner.next();

        try {
            AuthorManager.updateAuthor(connection, authorId, newAuthorName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void deleteAuthor(Connection connection) {
        System.out.print("Enter Author Id for deletion: ");
        String authorId = scanner.next();

        try {
            AuthorManager.deleteAuthor(connection, authorId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createBookWithAuthor(Connection connection) {
        System.out.println("Enter book and author details:");

        System.out.print("Book Id: ");
        Integer bookId = scanner.nextInt();
        System.out.print("Author Id: ");
        String authorId = scanner.next();

        try {
            BookAuthorManager.CreateBookWithAuthor(connection, bookId, authorId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void deleteBookWithAuthor(Connection connection) {
        System.out.println("Enter book and author details:");

        System.out.print("Book Id: ");
        Integer bookId = scanner.nextInt();
        System.out.print("Author Id: ");
        String authorId = scanner.next();

        try {
            BookAuthorManager.DeleteBookWithAuthor(connection, bookId, authorId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void displayAuthorDetails(Author author) {
        if (author != null) {
            System.out.println("Author Details:");
            System.out.println("Author Id: " + author.getAuthorId());
            System.out.println("Author Name: " + author.getAuthorName());
            System.out.println();
        }
    }
    private static void createCustomer(Connection connection) {
        System.out.println("Enter customer details:");

        System.out.print("Customer Id: ");
        String customerId = scanner.next();
        System.out.print("Customer Name: ");
        String customerName = scanner.next();
        System.out.print("Email: ");
        String email = scanner.next();

        try {
            CustomerManager.createCustomer(connection, customerId, customerName, email);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void getCustomerDetails(Connection connection) {
        System.out.print("Enter Customer Id: ");
        String customerId = scanner.next();

        try {
            Customer customer = CustomerManager.getCustomer(connection, customerId);
            displayCustomerDetails(customer);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updateCustomer(Connection connection) {
        System.out.print("Enter Customer Id for updating: ");
        String customerId = scanner.next();
        System.out.print("Enter new Customer Name: ");
        String newCustomerName = scanner.next();
        System.out.print("Enter new Email: ");
        String newEmail = scanner.next();

        try {
            CustomerManager.updateCustomer(connection, customerId, newCustomerName, newEmail);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void deleteCustomer(Connection connection) {
        System.out.print("Enter Customer Id for deletion: ");
        String customerId = scanner.next();

        try {
            CustomerManager.deleteCustomer(connection, customerId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void displayCustomerDetails(Customer customer) {
        if (customer != null) {
            System.out.println("Customer Details:");
            System.out.println("Customer Id: " + customer.getCustomerId());
            System.out.println("Customer Name: " + customer.getCustomerName());
            System.out.println("Email: " + customer.getEmail());
            System.out.println();
        }
    }
    private static void createOrder(Connection connection) {
        System.out.println("Enter order details:");

        System.out.print("Order Id: ");
        String orderId = scanner.next();
        System.out.print("Customer Id: ");
        String customerId = scanner.next();
        System.out.print("Order Date: ");
        String orderDate = scanner.next();
        System.out.print("Quantity Ordered: ");
        int quantityOrdered = scanner.nextInt();
        System.out.print("Total Amount: ");
        double totalAmount = scanner.nextDouble();

        Order order = new Order(orderId, customerId, orderDate, quantityOrdered, totalAmount);

        OrderManager.createOrder(connection, order);
    }

    private static void getOrderDetails(Connection connection) {
        System.out.print("Enter Order Id: ");
        String orderId = scanner.next();

        try {
            Order order = OrderManager.getOrder(connection, orderId);
            displayOrderDetails(order);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void deleteOrder(Connection connection) {
        System.out.print("Enter Order Id for deletion: ");
        String orderId = scanner.next();

        OrderManager.deleteOrder(connection, orderId);
    }

    private static void displayOrderDetails(Order order) {
        if (order != null) {
            System.out.println("Order Details:");
            System.out.println("Order Id: " + order.getOrderId());
            System.out.println("Customer Id: " + order.getCustomerId());
            System.out.println("Order Date: " + order.getOrderDate());
            System.out.println("Quantity Ordered: " + order.getQuantityOrdered());
            System.out.println("Total Amount: " + order.getTotalAmount());
            System.out.println();
        }
    }

    private static void createBookInOrder(Connection connection) {
        System.out.println("Enter book order details:");

        System.out.print("Book Id: ");
        Integer bookId = scanner.nextInt();
        System.out.print("Order Id: ");
        String orderId = scanner.next();


        try {
            BookOrderManager.createBookInOrder(connection, bookId, orderId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void deleteBookFromOrder(Connection connection) {
        System.out.println("Enter book order details:");

        System.out.print("Book Id: ");
        Integer bookId = scanner.nextInt();
        System.out.print("Order Id: ");
        String orderId = scanner.next();


        try {
            BookOrderManager.deleteBookFromOrder(connection, bookId, orderId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



