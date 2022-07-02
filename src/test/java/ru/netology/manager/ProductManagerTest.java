package ru.netology.manager;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

class ProductMangerTest {
    static ProductRepository repo = new ProductRepository();
    ProductManger manger = new ProductManger(repo);
    static Product smartphone = new Smartphone(2, "Nokia-8800", 100, "Nokia");
    static Product smartphone1 = new Smartphone(5, "S8", 100500, "Samsung");
    static Product book = new Book(25, "Bible", 1000, "God");
    static Product book1 = new Book(1, "Теория психоанализа", 50, " Зигмунд Фрейд");
    static Product book2 = new Book(3, "Фрейд", 123, "Теория психоанализа");

    @BeforeAll
    static void setUp() {
        repo.save(smartphone);
        repo.save(smartphone1);
        repo.save(book);
        repo.save(book1);
        repo.save(book2);
    }

    @Test
    public void shouldFindBookName() {
        Product[] expected = {book};
        Product[] actual = manger.searchBy("Bible");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindBookAuthor() {
        Product[] expected = {book1};
        Product[] actual = manger.searchBy("Зигмунд Фрейд");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindSmartphoneProducer() {
        Product[] expected = {smartphone};
        Product[] actual = manger.searchBy("Nokia");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindSmartphoneName() {
        Product[] expected = {smartphone1};
        Product[] actual = manger.searchBy("S8");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindTheSameText() {
        Product[] expected = {book1, book2};
        Product[] actual = manger.searchBy("Теория психоанализа");

        assertArrayEquals(expected, actual);
    }

}

