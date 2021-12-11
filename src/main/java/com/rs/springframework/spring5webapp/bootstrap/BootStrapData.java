package com.rs.springframework.spring5webapp.bootstrap;

import com.rs.springframework.spring5webapp.domain.Author;
import com.rs.springframework.spring5webapp.domain.Book;
import com.rs.springframework.spring5webapp.domain.Publisher;
import com.rs.springframework.spring5webapp.repositories.AuthorRepository;
import com.rs.springframework.spring5webapp.repositories.BookRepository;
import com.rs.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Started in Bootstrap");

        Publisher publisher = new Publisher();
        publisher.setName("SFG Publishing");
        publisher.setAddressLine1("121 McDowel Blvd,Suite 102");
        publisher.setCity("St. Petersburg");
        publisher.setState("FL");
        publisher.setZip("50045");

        publisherRepository.save(publisher);

        Author eric = new Author("Eric","Evans");
        Book ddd = new Book("Domain Driven Design","123123");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepository.save(ddd);

        //one more  example
        Author rod =new Author("Rod","Johnson");
        Book noEJB =new Book("J2EE Developers Without EJB","123321");

        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        authorRepository.save(rod);
        bookRepository.save(noEJB);


        System.out.println("Number of Books: "+bookRepository.count());
        System.out.println("Number of Authors: "+authorRepository.count());
        System.out.println("Number of Publisher: "+publisherRepository.count());
    }
}
