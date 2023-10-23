package com.simplon.apicynthia.model;

//import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import java.util.List;
import lombok.Data;

@Data /* permet de ne pas ajouter les g(s)etters dans la classe */
@Entity /* cette classe a le role d'une entité */
public class Game {
    // attribut ou propriété correspond à la table créée dans la BDD, toujours en private

    @Id /* Clé primaire */
    @GeneratedValue(strategy = GenerationType.IDENTITY) /* auto-incrementation */
    private Long id;

    private String title;

    // @Column(name= "min_players") => si on veut associer a un champ dans la BDD qui a un nom différent, pour faire la correspondance
    private Integer min;

    private Integer max;

    @OneToMany(mappedBy = "game")
    private List<Contest> contests;
}