package com.simplon.apicynthia.model.dto;

import java.util.ArrayList;
import java.util.List;

import com.simplon.apicynthia.model.Contest;
import com.simplon.apicynthia.model.Player;

import lombok.Data;

@Data
public class PlayerDTO {
    private Long id;
    private String email;
    private String nickname;
    private List<String> wins;
    private Integer contests;

    public PlayerDTO(Player player) {
        this.id = player.getId();
        this.email = player.getEmail();
        this.nickname = player.getNickname();
        this.wins = new ArrayList<String>();

        if (player.getWins() != null) {
            for (Contest contest : player.getWins()) {
                this.wins.add(
                        "Partie n" + contest.getId() + "de" + contest.getGame().getTitle() + "du"
                                + contest.getStartDate());
            }
        }else {
            this.wins = null;
        }
        // siza : recuperer la taille
        if (player.getContests() != null) {
        this.contests = player.getContests().size();
        } else {
            this.contests=0;
        }
    }
}