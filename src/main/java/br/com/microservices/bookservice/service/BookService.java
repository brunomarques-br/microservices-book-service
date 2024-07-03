package br.com.microservices.bookservice.service;

import br.com.microservices.bookservice.model.Book;
import br.com.microservices.bookservice.repository.BookRepository;
import br.com.microservices.cambioservice.dto.CambioDTO;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

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
        if (book == null) throw new RuntimeException("Book not found");

        HashMap<String, String> params = new HashMap<>();
        params.put("amount", book.getPrice().toString());
        params.put("from", "USD");
        params.put("to", currency);

        var response = new RestTemplate().getForEntity("http://localhost:8000/cambio-service/cambio/{amount}/{from}/{to}",
                CambioDTO.class, params);

        var cambio = response.getBody();
        if (cambio == null) throw new RuntimeException("Cambio not found");

        book.setPrice(cambio.getConvertedValue());
        book.setEnvironment(environment.getProperty("local.server.port"));
        book.setCurrency(currency);

        return book;
    }
}
