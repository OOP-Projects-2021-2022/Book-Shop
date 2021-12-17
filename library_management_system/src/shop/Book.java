package shop;

public class Book {
    private String name;
    private int numberOfPages;
    private Guest ownership;

    public Book(String name, int numberOfPages) {
        this.name = name;
        this.numberOfPages = numberOfPages;
    }

    public String getName() {
        return name;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setOwnership(Guest guest) {
        ownership = guest;
    }

    public Guest getOwnership() {
        return ownership;
    }
}
