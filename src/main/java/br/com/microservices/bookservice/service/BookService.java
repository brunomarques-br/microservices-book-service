package br.com.microservices.bookservice.service;

import br.com.microservices.bookservice.model.Book;
import br.com.microservices.bookservice.proxy.CambioProxy;
import br.com.microservices.bookservice.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final CambioProxy cambioProxy;

    public BookService(
            BookRepository bookRepository,
            CambioProxy cambioProxy) {
        this.bookRepository = bookRepository;
        this.cambioProxy = cambioProxy;
    }

    public Book findBook(Long id, String currency) {

        var book = bookRepository.findById(id).orElse(null);
        if (book == null) throw new RuntimeException("Book not found");

        //FEIGN
        var cambio = cambioProxy.getCambio(book.getPrice(), "USD", currency);

        book.setEnvironment(cambio.getEnvironment());
        book.setPrice(cambio.getConvertedValue());

        return book;
    }
}
