package com.sujatanaik.LMSLocalLibrary.database.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table ( name = "users")
public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Integer id;

    @Column (name = "email", unique = true, nullable = false, length = 100)
    private String email;

    @Column (name = "first_name", nullable = false, length = 45)
    private String firstName;

    @Column (name = "last_name", nullable = false, length = 45)
    private String lastName;

    @Column (name = "address_line1", nullable = false, length = 100)
    private String addressLine1;

    @Column (name = "address_line2", nullable = true, length = 100)
    private String addressLine2;

    @Column (name = "city", nullable = false, length = 20)
    private String city;

    @Column (name = "state", nullable = false, length = 20)
    private String state;

    @Column (name = "zip", nullable = false, length = 5)
    private String zip;

    @Column (name = "phone", nullable = false, length = 12)
    private String phone;

    @Column (name = "password", nullable = false, length = 20)
    private String password;

    public enum UserStatus {
        ACTIVE,
        INACTIVE;
     }
    @Column (name = "ustatus", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    public enum UserGender {
        MALE,
        FEMALE;
    }
    @Column (name = "gender", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private UserGender gender;

    public enum UserNews {
        YES,
        NO;
    }
    @Column (name = "news", nullable = false, length = 5)
    @Enumerated(EnumType.STRING)
    private UserNews news;

    @Column (name = "create_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @ToString.Exclude
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<BorrowedBook> borrowedBookSet;
}
