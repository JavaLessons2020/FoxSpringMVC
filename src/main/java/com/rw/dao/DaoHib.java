package com.rw.dao;

import com.rw.models.Book;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DaoHib {

    private final SessionFactory sessionFactory;

    public DaoHib(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session mySession() {
        return sessionFactory.openSession();
    }

    //запись в БД(вносить можно с помощью set)
        /*Person person = new Person("Name_4",17);
        session.save(person);
        transaction.commit();  */

    //получение по id
    public Book getBookId(int id) {
        Session session = mySession();
        Transaction transaction = session.beginTransaction();
        Book book = session.get(Book.class, id);
        System.out.println(book);
        //mySession().close();
        transaction.commit();
        return book;
    }

    //получение всех данных с таблицы
    public List<Book> getAllBooks() {
//        Transaction transaction = mySession().beginTransaction();
//        List<Book> allBooks = mySession().createQuery("FROM Book").list();
//        System.out.println(allBooks);
//        mySession().close();
//        transaction.commit();
//        return allBooks;
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException ex) {
            session = mySession();
        }
        List<Book> books = new ArrayList<>();
        try {
            books = session.createQuery("from Book ").list();
        } catch (HibernateException he) {
            System.out.println("Error getting books: " + he);
            he.printStackTrace();
        } finally {
            if (session.isOpen()) {
                session.close();
            }
        }
        return books;
    }

    //запись новой книги
    public void addNewBook(Book book) {
        Transaction transaction = mySession().beginTransaction();
        mySession().save(book);
        mySession().close();
        transaction.commit();
    }

    //изменение данных в объекте
    public void updateBook(Book newBook) {
        Transaction transaction = null;
        Book book = mySession().get(Book.class, newBook.getId());
        book = newBook;

        Session session = mySession();
//        book.setName(newBook.getName());
//        book.setAuthor(newBook.getAuthor());
//        book.setYour(newBook.getYour());
//        book.setStyle(newBook.getStyle());
//        book.setAmountPages(newBook.getAmountPages());
//        book.setDescription(newBook.getDescription());
        try {
            transaction = session.beginTransaction();
            session.update(book);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session.isOpen()) {
                session.close();
            }
        }

    }
    //изменение данных в объекте(данные обновляются автоматически)
        /*Person person = session.get(Person.class, 1);
        System.out.println(person);
        person.setAge(16);
        System.out.println(person);
        transaction.commit();   */

    //удаление объекта
    public void remove(int id) {
//        Transaction transaction = mySession().beginTransaction();
        Book book = mySession().get(Book.class, id);
//        //mySession().delete(book);
        //Book book = (Book) mySession().load(Book.class, id);
//
//        if (book != null) {
//            mySession().delete(book);
//        }
//        //mySession().close();
//        transaction.commit();

        Session session;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException ex) {
            session = mySession();
        }
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.delete(book);
            transaction.commit();
        } catch (HibernateException ex) {
            System.out.println("Error deleting car: " + ex);
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session.isOpen()) {
                session.close();
            }
        }
    }
}

//удаление объекта
        /*Person person = session.get(Person.class, 2);
        session.delete(person);
        transaction.commit();
        System.out.println(person);  */

        /*City lviv = new City("Lviv",50_000);
        City kiev = new City("Kiev", 60_000);
        City odessa = new City("Odessa",10_000);
        session.save(lviv);
        session.save(kiev);
        session.save(odessa);
        transaction.commit();  */


//sessionFactory.close();
//}

