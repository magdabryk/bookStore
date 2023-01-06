package pl.camp.it.book.store.model;

public class OrderPosition {
    private Book book;
    private int quantity;

    public OrderPosition(Book book, int amount) {
        this.book = book;
        this.quantity = amount;
    }

    public OrderPosition() {
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void increamentQuantity(){
        this.quantity++;
    }
}
