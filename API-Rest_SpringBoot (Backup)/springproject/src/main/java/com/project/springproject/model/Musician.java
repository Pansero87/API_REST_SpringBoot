package com.project.springproject.model;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents a musician in the orchestra.
 * 
 * This class contains information about a musician, such as their first name,
 * last name, birthdate,
 * instrument, email, and the orchestra they belong to.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "musicians")
public class Musician implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String firstname;
    private String lastname;
    private Date birthdate;
    private String instrument;
    private String email;

    @ManyToOne
    @JoinColumn(name = "orchestra_id", referencedColumnName = "id")
    private Orchestra orchestra;
}