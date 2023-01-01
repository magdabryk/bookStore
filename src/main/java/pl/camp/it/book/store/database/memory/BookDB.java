package pl.camp.it.book.store.database.memory;


import org.springframework.stereotype.Component;
import pl.camp.it.book.store.database.IBookDAO;
import pl.camp.it.book.store.model.Book;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookDB implements IBookDAO {
    private final List<Book> books = new ArrayList<>();

    public BookDB() {
        this.books.add(new Book(1,
                "Algorytmy i struktury danych. Kurs video. Java, JavaScript, Python",
                "Artur Kulesza",
                40.05,
                10,
                "978-83-283-8242-8"));

        this.books.add(new Book(2,
                "Java Full Stack Developer. Kurs video. Tworzenie aplikacji internetowych od podstaw",
                "Marcin Berendt",
                104.65,
                10,
                "978-83-283-6841-5"));

        this.books.add(new Book(3,
                "Java od zera. Kurs video. Programuj obiektowo!",
                "Piotr Chudzik",
                84.50,
                10,
                "978-83-283-9011-9"));

        this.books.add(new Book(4,
                "Java. Efektywne programowanie. Wydanie III",
                "Joshua Bloch",
                64.35,
                10,
                "978-83-283-9896-2"));

        this.books.add(new Book(5,
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
}
