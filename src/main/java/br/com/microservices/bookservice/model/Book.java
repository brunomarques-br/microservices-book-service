package br.com.microservices.bookservice.model;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
public class Book implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String title;
    private String author;
    private Date launchDate;
    private Double price;
    private String currency;
    private String environment;
}
