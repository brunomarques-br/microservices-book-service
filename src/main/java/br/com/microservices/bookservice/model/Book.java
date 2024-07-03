package br.com.microservices.bookservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity(name = "book")
public class Book implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "launch_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date launchDate;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "title", nullable = false, length = 250)
    private String title;

    @Transient
    private String currency;
    @Transient
    private String environment;
}
