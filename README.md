Overview
This is a simple Bookstore application that connects to a database and allows you to perform basic operations related to books, authors, customers and orders.

Make sure you have the following tools installed on your computer:
 1. Java Development Kit (JDK)
 2. Git
 3. PostgreSQL Server

 Steps to run the application:
 1. Git clone https://github.com/GunelD/As2_db.git
 2. Open the project in any Java IDE you prefer.
 3. Set up the Database.
 4. Create a PostgreSQL database and execute the SQL script provided below:
    
    CREATE TABLE Authors (
    authorId VARCHAR(30),
    authorName VARCHAR(100) NOT NULL,
	PRIMARY KEY (authorId)
);

CREATE TABLE Books (
    bookId INT,
    title VARCHAR(255) NOT NULL,
    quantityInStock INTEGER NOT NULL,
    price NUMERIC(10, 2) NOT NULL,
	publishDate VARCHAR(50),
    genre VARCHAR(100),
	description TEXT,
	PRIMARY KEY (bookId),
    CONSTRAINT positiveQuantity CHECK (quantityInStock >= 0)
);

CREATE TABLE Customers (
    customerId VARCHAR(30),
    customerName VARCHAR(100) NOT NULL,
	email VARCHAR(255) UNIQUE,
	PRIMARY KEY (customerId)
);

CREATE TABLE Orders (
    orderId VARCHAR(30),
    customerId VARCHAR(30),
    orderDate VARCHAR(50),
    quantityOrdered INTEGER NOT NULL,
	totalAmount DECIMAL(10, 2) NOT NULL,
	PRIMARY KEY (orderId),
	FOREIGN KEY (customerId) REFERENCES Customers
);


CREATE TABLE Book_author (
bookId INT,
authorId VARCHAR(30),
PRIMARY KEY (bookId, authorId),
FOREIGN KEY (authorId) REFERENCES Authors,
FOREIGN KEY (bookId) REFERENCES Books
);

CREATE TABLE Book_order (
bookId INT,
orderId VARCHAR(30),
PRIMARY KEY (bookId, orderId),
FOREIGN KEY (orderId) REFERENCES Orders,
FOREIGN KEY (bookId) REFERENCES Books
);

5. Check the database connection.
6. Open the BookstoreApplication class.
7. Check the getConnection method and update the database connection details (URL, username, password) according to your setup.
8. Run the BookstoreApplication class
9. Choose any option you want to perform and press the corresponding number from the list in the user interface.
