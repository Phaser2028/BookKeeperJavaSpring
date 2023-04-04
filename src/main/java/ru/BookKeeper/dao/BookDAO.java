package ru.BookKeeper.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.BookKeeper.models.Book;
import ru.BookKeeper.models.Person;


import java.util.List;
import java.util.Optional;


@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    public PersonDAO personDAO;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM book WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO book(title,publication_year,author) VALUES(?,?,?)", book.getTitle(), book.getPublication_year(),book.getAuthor());
    }

    public void update(int id, Book updatedBook) {
        jdbcTemplate.update("UPDATE book SET title=?, publication_year=?, author=? WHERE id=?", updatedBook.getTitle(), updatedBook.getPublication_year(),updatedBook.getAuthor(),id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM book WHERE id=?", id);
    }

    //получить владельца по id книги
    public Optional<Person> getBookOwner(int id){//id книги
        return jdbcTemplate.query("SELECT person.* FROM book join person on book.person_id=person.id "+
                        "WHERE book.id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny();
    }



    //выдать книгу
    public void assignBook(int id,Person person){
        jdbcTemplate.update("UPDATE book SET person_id=? WHERE id=?", person.getId(),id);
    }

    //забрать книгу
    public void getBackBook(int id){
        jdbcTemplate.update("UPDATE book SET person_id=null WHERE id=?", id);
    }
}
