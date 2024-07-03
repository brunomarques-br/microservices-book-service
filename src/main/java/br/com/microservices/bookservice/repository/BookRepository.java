package br.com.microservices.bookservice.repository;

import br.com.microservices.bookservice.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
