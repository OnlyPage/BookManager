package com.example.bookManager.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@Table(name = "bookDetail")
public class BookDetail
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "nameBook")
    private String nameBook;

    @Column(name = "author")
    private String author;

    @Column(name = "publicDate")
    private Date publicDate;

    @OneToMany(mappedBy = "bookDetail")
    private Set<BookStore> bookStore;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getPublicDate() {
        return publicDate;
    }

    public void setPublicDate(Date publicDate) {
        this.publicDate = publicDate;
    }

    public Set<BookStore> getBookStore() {
        return bookStore;
    }

    public void setBookStore(Set<BookStore> bookStore) {
        this.bookStore = bookStore;
    }
}
