package com.rw.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="buyers")
public class Buyer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String phone;

    @ManyToMany
    @JoinTable(name = "buyer_book",
                joinColumns = @JoinColumn(name = "id_buyer"),
                inverseJoinColumns = @JoinColumn(name = "id_book"))
    Set<Book> books = new HashSet<>();




    public Buyer(){}

    public Buyer(String name, String phone, Set<Book> books) {
        this.name = name;
        this.phone = phone;
        this.books = books;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

//    @Override
//    public String toString() {
//        return "Buyer{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", phone='" + phone + '\'' +
//                //", books=" + books +
//                '}'+"\n";
//    }
}
