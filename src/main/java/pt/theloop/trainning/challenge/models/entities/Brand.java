package pt.theloop.trainning.challenge.models.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Brand {

    @Id
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

}
