package com.example.bookManager.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "categoryDetail")
public class CategoryDetail
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "categoryDetail")
    private Set<BookDetail> bookDetails;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<BookDetail> getBookDetails() {
        return bookDetails;
    }

    public void setBookDetails(Set<BookDetail> bookDetails) {
        this.bookDetails = bookDetails;
    }

    @Override
    public String toString()
    {
        return "id " + id + " : " + name;
    }
}
