package br.com.microservices.bookservice.service;

import br.com.microservices.bookservice.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BookService {

    private final Environment environment;

    public BookService(Environment environment) {
        this.environment = environment;
    }

    public Book findBook(Long id, String currency) {
        Book book = new Book();
        book.setId(id);
        book.setAuthor("Rodrigo Turini");
        book.setTitle("Spring Boot");
        book.setLaunchDate(new Date());
        book.setPrice(29.90);
        book.setCurrency(currency);
        book.setEnvironment(environment.getProperty("local.server.port"));
        return book;
    }
}
