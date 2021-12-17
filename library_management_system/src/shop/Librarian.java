package shop;

public class Librarian {
    private String name;
    private Library library = new Library();

    public Librarian(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addBook(Book bookAdd) {
        boolean added = false;
        for (Book book : library.getBooks()) {
            if (book.getName().equals(bookAdd.getName())) {
                added = true;
                System.out.println("This book already exists in the library!");
                break;
            }
        }
        if(added == false) {
            library.addBook(bookAdd);
            System.out.println("Book was added successfully");
        }
    }

    public void deleteBook(Book bookDelete) {
        if(library.getBooks().contains(bookDelete)){
            library.getBooks().removeIf(book -> book.getName().equals(bookDelete.getName()));
            System.out.println("Book deleted successfully");
        }else{
            System.out.println("Book didn't exist in the first place");
        }

    }

}
