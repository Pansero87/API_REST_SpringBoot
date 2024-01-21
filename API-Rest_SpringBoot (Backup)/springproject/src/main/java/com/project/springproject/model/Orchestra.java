package com.project.springproject.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents an orchestra in the music project.
 * 
 * This class contains information about the orchestra, such as its name, city,
 * country, style, conductor, and a list of musicians.
 * It is annotated with JPA annotations to define its mapping to the database
 * table "orchestras".
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "orchestras")
public class Orchestra implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;
    private String city;
    private String country;
    private String estile;
    private String conductor;

    @OneToMany(mappedBy = "orchestra", cascade = CascadeType.ALL)
    private List<Musician> musicians;

}
