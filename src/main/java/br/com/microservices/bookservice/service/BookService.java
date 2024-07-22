package br.com.microservices.bookservice.service;

import br.com.microservices.bookservice.model.Book;
import br.com.microservices.bookservice.proxy.CambioProxy;
import br.com.microservices.bookservice.repository.BookRepository;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final CambioProxy cambioProxy;
    private final Environment environment;

    public BookService(
            BookRepository bookRepository,
            CambioProxy cambioProxy,
            Environment environment) {
        this.bookRepository = bookRepository;
        this.cambioProxy = cambioProxy;
        this.environment = environment;
    }

    public Book findBook(Long id, String currency) {

        var book = bookRepository.findById(id).orElse(null);
        if (book == null) throw new RuntimeException("Book not found");

        //FEIGN
        var cambio = cambioProxy.getCambio(book.getPrice(), "USD", currency);

        var port = environment.getProperty("local.server.port");
        book.setEnvironment("Book port: " + port + " Cambio port: " + cambio.getEnvironment());
        book.setPrice(cambio.getConvertedValue());

        return book;
    }
}
