package com.simplon.apicynthia.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity

public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    
    @Column(length = 30)
    private String nickname;

    @OneToMany(mappedBy = "winner")
    private List<Contest> wins;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "player_contest",
    joinColumns = {@JoinColumn(name = "player_id")},
    inverseJoinColumns = {@JoinColumn(name = "contest_id")}
    )
    // recupere tous les contests
    private List<Contest> contests;
}