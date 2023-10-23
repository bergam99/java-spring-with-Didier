package com.simplon.apicynthia.model;

import java.util.List;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
// import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


import lombok.Data;

@Entity
@Data

public class Contest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_date")
    private Date startDate;


    // objet Game
    // many contest(où je suis) ->(peut etre relié à) one game
    @ManyToOne
    //JoinColumn : renommé, c'est optionnel
    // @JoinColumn(name = "game_id")
    private Game game;

    @ManyToOne
    private Player winner;

    @ManyToMany(mappedBy = "contests")
    // recupere tous les players
    private List<Player> players;
}