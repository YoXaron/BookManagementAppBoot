package com.yoxaron.springcourse.BookManagementAppBoot.repository;

import com.yoxaron.springcourse.BookManagementAppBoot.model.Book;
import com.yoxaron.springcourse.BookManagementAppBoot.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;


@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    @Query("SELECT b FROM Book b WHERE b.person.id = :personId")
    List<Book> findBooksByPersonId(@Param("personId") int personId);

    Optional<Person> getPersonByFullName(String fullName);
}
