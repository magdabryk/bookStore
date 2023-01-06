package pl.camp.it.book.store.database.memory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.camp.it.book.store.database.IBookDAO;
import pl.camp.it.book.store.database.sequence.IBookIdSequence;
import pl.camp.it.book.store.database.sequence.IIdSequence;
import pl.camp.it.book.store.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class BookDB implements IBookDAO {


    IIdSequence bookIdSequence;
    private final List<Book> books = new ArrayList<>();


    public BookDB(@Autowired IBookIdSequence bookIdSequence) {
        this.bookIdSequence = bookIdSequence;
        this.books.add(new Book(this.bookIdSequence.getId(),
                "Algorytmy i struktury danych. Kurs video. Java, JavaScript, Python",
                "Artur Kulesza",
                40.05,
                10,
                "978-83-283-8242-8"));

        this.books.add(new Book(this.bookIdSequence.getId(),
                "Java Full Stack Developer. Kurs video. Tworzenie aplikacji internetowych od podstaw",
                "Marcin Berendt",
                104.65,
                10,
                "978-83-283-6841-5"));

        this.books.add(new Book(this.bookIdSequence.getId(),
                "Java od zera. Kurs video. Programuj obiektowo!",
                "Piotr Chudzik",
                84.50,
                10,
                "978-83-283-9011-9"));

        this.books.add(new Book(this.bookIdSequence.getId(),
                "Java. Efektywne programowanie. Wydanie III",
                "Joshua Bloch",
                64.35,
                10,
                "978-83-283-9896-2"));

        this.books.add(new Book(this.bookIdSequence.getId(),
                "Java. Przewodnik dla początkujących. Wydanie VIII",
                "Herbert Schildt",
                64.35,
                10,
                "978-83-283-9118-5"));
    }

    @Override
    public List<Book> getBooks() {
        return books;
    }

    @Override
    public List<Book> getBooksByPattern(String pattern) {
        List<Book> filteredBooks = new ArrayList<>();

        for (Book book : this.books) {
            if (book.getTitle().toLowerCase().contains(pattern.toLowerCase()) ||
                    book.getAuthor().toLowerCase().contains(pattern.toLowerCase())) {
                filteredBooks.add(book);
            }
        }
        return filteredBooks;
    }

    @Override
    public void persistBook(Book book) {
        book.setId(bookIdSequence.getId());
        this.books.add(book);
    }

    @Override
    public Optional<Book> getBookById(int id) {
        for(Book book: this.books){
            if(book.getId() == id){
                return Optional.of(book);
            }
        }
        return Optional.empty();
    }

    @Override
    public void updateBook(Book book) {
    Optional<Book> bookBox = getBookById(book.getId());
    if(bookBox.isPresent()){
        Book bookFromDB = bookBox.get();
        bookFromDB.setTitle(book.getTitle());
        bookFromDB.setAuthor(book.getAuthor());
        bookFromDB.setPrice(book.getPrice());
        bookFromDB.setQuantity(book.getQuantity());
        bookFromDB.setIsbn(book.getIsbn());
    }
    }
}
