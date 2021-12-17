package shop;

public class Guest {
    private String name;
    private Library library = new Library();

    public Guest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void viewBooks() {
        for(Book book : library.getBooks())
            System.out.println(book.getName() + " " + book.getNumberOfPages());
        System.out.println();
    }

    public void takeOwnership(String bookName){
        boolean bookExists = false;
        for (Book book : library.getBooks()) {
            if(book.getName().equals(bookName)) {
                bookExists = true;
                if(book.getOwnership() == null) {
                    book.setOwnership(this);
                    System.out.println("You successfully took the book");
                }
                else {
                    System.out.println("This book has been taken by " + book.getOwnership().getName());
                }
                break;
            }
        }
        if(!bookExists)
            System.out.println("This book doesn't exist!");
    }
}
