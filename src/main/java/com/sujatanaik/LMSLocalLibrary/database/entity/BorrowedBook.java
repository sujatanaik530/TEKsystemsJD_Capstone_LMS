package com.sujatanaik.LMSLocalLibrary.database.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "borrowedbooks")
public class BorrowedBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "book_id", nullable = false, updatable = true)
    private Book book;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false, updatable = true)
    private User user;

    @Column(name = "borrow_date", nullable = false)
    private LocalDate borrowDate;

    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    public enum BookStatus {
        CHECKEDOUT,
        RETURNED;
    }
    @Column(name = "bstatus", nullable = false)
    @Enumerated(EnumType.STRING)
    private BookStatus bstatus;

    @Column (name = "create_date")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date createDate;
}
