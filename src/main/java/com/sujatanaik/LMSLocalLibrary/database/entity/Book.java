package com.sujatanaik.LMSLocalLibrary.database.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "price", nullable = false)
    private Double price; // double in db

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "category", nullable = false)
    private String category;

    public enum BookCondition {
        DAMAGED,
        GOOD;
    }
    @Column(name = "bcondition", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private BookCondition condition;

    public enum BookStatus {
        AVAILABLE,
        CHECKEDOUT,
        LOST;
    }
    @Column(name = "bstatus", nullable = false, length = 15)
    @Enumerated(EnumType.STRING)
    private BookStatus status;

    @Column(name = "img", length = 512)
    private String img;

    @Column (name = "create_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @ToString.Exclude
    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<BorrowedBook> borrowedBookSet;
}
