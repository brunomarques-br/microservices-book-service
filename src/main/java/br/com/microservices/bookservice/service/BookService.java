package br.com.microservices.bookservice.service;

import br.com.microservices.bookservice.model.Book;
import br.com.microservices.bookservice.repository.BookRepository;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final Environment environment;
    private final BookRepository bookRepository;

    public BookService(Environment environment, BookRepository bookRepository) {
        this.environment = environment;
        this.bookRepository = bookRepository;
    }

    public Book findBook(Long id, String currency) {

        var book = bookRepository.findById(id).orElse(null);
        if (book != null) {
            book.setCurrency(currency);
            book.setEnvironment(environment.getProperty("local.server.port"));
        } else {
            throw new RuntimeException("Book not found");
        }

        return book;
    }
}
