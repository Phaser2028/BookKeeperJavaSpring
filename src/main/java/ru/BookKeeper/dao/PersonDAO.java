package ru.BookKeeper.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.BookKeeper.models.Book;
import ru.BookKeeper.models.Person;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;
    private final BookDAO bookDAO;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate, BookDAO bookDAO) {
        this.jdbcTemplate = jdbcTemplate;
        this.bookDAO = bookDAO;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM person;", new BeanPropertyRowMapper<>(Person.class));
    }


    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM person WHERE id=?;", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO person(name,year_birth) VALUES(?, ?);", person.getName(), person.getYear_birth());
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE person SET name=?, year_birth=? WHERE id=?", updatedPerson.getName(),
                updatedPerson.getYear_birth(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM person WHERE id=?;", id);
    }




    public List<Book> getBooksByPersonId(int id) {
        return jdbcTemplate.query("select * from book where person_id=?",new Object[]{id},new BeanPropertyRowMapper<>(Book.class));

    }



}