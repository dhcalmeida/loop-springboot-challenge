package pt.theloop.trainning.challenge.models.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Product {

    @Id
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Brand brand;

}
