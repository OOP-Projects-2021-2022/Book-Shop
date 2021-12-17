package shop;

import java.util.ArrayList;

public class Library {
    //static bc static variables are created when program starts and destroyed when program stops
    private static ArrayList<Book> books = new ArrayList<>();

    public void addBook(Book addBook) {
        books.add(addBook);
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

}
