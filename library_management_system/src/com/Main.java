package com;

import java.sql.Connection;
import java.sql.DriverManager;

import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/book_shop", "postgres", "anamaria");
            System.out.println("Opened database successfully");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }


 /*       Scanner in = new Scanner(System.in);
        String inputStr, inputOp, name, username,pages;
        int pageNr;
        while(true)
        {
            System.out.println("Choose your user type (guest, librarian, q) ");
            inputStr = in.nextLine();
            switch (inputStr.toLowerCase(Locale.ROOT)) {
                case "librarian":
                    System.out.println("What's your name? ");
                    username = in.nextLine();
                    Librarian librarian = new Librarian(username);

                    System.out.println("Choose operation (add, delete, q) ");
                    inputOp = in.nextLine();

                    if(inputOp.equals("add") || inputOp.equals("delete")) {
                        System.out.println("Give the name of the book: ");
                        name = in.nextLine();
                        System.out.println("Give the nr of pages");
                        pages = in.nextLine();
                        pageNr = Integer.parseInt(pages);
                        //in.nextLine(); //pt enter dupa int

                        Book book = new Book(name, pageNr);

                        if (inputOp.equals("add")) {
                            librarian.addBook(book);
                        } else if (inputOp.equals("delete")) {
                            librarian.deleteBook(book);
                        }
                    }
                    break;
                case "guest":
                    System.out.println("What's your name? ");
                    username = in.nextLine();
                    Guest guest = new Guest(username);

                    System.out.println("Choose operation (view, take, q) ");
                    inputOp = in.nextLine();

                    if(inputOp.equals("view")) {
                        guest.viewBooks();
                    }
                    else if(inputOp.equals("take")) {
                        System.out.println("Give the name of the book: ");
                        name = in.nextLine();
                        guest.takeOwnership(name);
                    }
                    break;
                case "q": System.exit(0);
            }
        }*/
    }
}
