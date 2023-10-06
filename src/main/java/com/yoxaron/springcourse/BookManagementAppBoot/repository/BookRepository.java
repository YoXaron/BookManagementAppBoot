package com.yoxaron.springcourse.BookManagementAppBoot.repository;

import com.yoxaron.springcourse.BookManagementAppBoot.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByNameStartingWith(String name);
}