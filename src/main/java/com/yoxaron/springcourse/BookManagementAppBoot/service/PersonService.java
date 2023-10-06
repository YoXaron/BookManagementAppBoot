package com.yoxaron.springcourse.BookManagementAppBoot.service;

import com.yoxaron.springcourse.BookManagementAppBoot.model.Book;
import com.yoxaron.springcourse.BookManagementAppBoot.model.Person;
import com.yoxaron.springcourse.BookManagementAppBoot.repository.PersonRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PersonService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getAll() {
        return personRepository.findAll();
    }

    public Person getById(int id) {
        Optional<Person> foundPerson = personRepository.findById(id);
        return foundPerson.orElse(null);
    }
    
    @Transactional
    public void save(Person person) {
        personRepository.save(person);
    }

    @Transactional
    public void update(int id, Person updatedPerson) {
        updatedPerson.setId(id);
        personRepository.save(updatedPerson);
    }

    @Transactional
    public void delete(int id) {
        personRepository.deleteById(id);
    }

    public Optional<Person> getPersonByFullName(String fullName) {
        return personRepository.getPersonByFullName(fullName);
    }

    public List<Book> getBooksByPersonId(int id) {
        Optional<Person> optionalPerson = personRepository.findById(id);

        if (optionalPerson.isPresent()) {
            Person person = optionalPerson.get();

            Hibernate.initialize(person.getBooks());

            for (Book book : person.getBooks()) {
                long takenAtMillis = Math.abs(book.getTakenAt().getTime() - new Date().getTime());

                //Expire check (10 days)
                if (takenAtMillis > 864000000) {
                    book.setExpired(true);
                }
            }

            return person.getBooks();

        } else {
            return Collections.emptyList();
        }
    }
}
