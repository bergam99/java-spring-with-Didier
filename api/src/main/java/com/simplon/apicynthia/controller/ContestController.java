package com.simplon.apicynthia.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.simplon.apicynthia.model.Contest;
import com.simplon.apicynthia.model.dto.ContestDTO;
import com.simplon.apicynthia.service.ContestService;
import com.simplon.apicynthia.service.GameService;
import com.simplon.apicynthia.service.PlayerService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

// toutes les annotations au dessus de class s'appelle bean (haricot). j'utilise cette annotation pour donner un role particulier dans la class 
@RestController
public class ContestController {

    // instancier automaiquement
    @Autowired
    private ContestService contestService;

    @Autowired
    private GameService gameService;

    @Autowired
    private PlayerService playerService;
    // pour eviter ça on fait : @Autowired
    // public ContestController(){
    // this.contestService = new ContestService();
    // }

    /**
     * renvoie tous les jeux
     * GetMapping : permet de relier cette méthode 'allContests' à une URL qui sera
     * appelée en méthode HTTP GET.
     * 
     * @return
     */
    @GetMapping("/contests")
    public Iterable<ContestDTO> allContests() {
        Iterable<Contest> contests = contestService.getAllContests();
        List<ContestDTO> contestsDTO = new ArrayList<ContestDTO>();
        for (Contest contest : contests) {
            contestsDTO.add(new ContestDTO(contest));
        }
        return contestsDTO;
    }

    /**
     * {} : c'est variable
     * PathVariable : recuperer
     */
    @GetMapping("/contest/{id}")
    public ContestDTO contest(@PathVariable("id") int id) {
        Optional<Contest> g = contestService.getContest(id); // objet optionnal
        if (g.isPresent()) {
            return new ContestDTO(g.get()); // la methode get de l'objet Optional retourne
        } else {
            return null;
        }
    }

    /**
     * recuperer le jeu de methode post
     * RequestBody : réupèere les données passées dans le corps de la requête HTTP
     * En méthode HTTP POST, les données sont passées dans le corps de la requête
     * (alors qu'en GET, des données peuvent être passées dans l'URL).
     * 
     * @param g
     * @return
     */
    @PostMapping("/contest")
    public ContestDTO insertContest(@RequestParam String start_date, @RequestParam int game_id, @RequestParam Integer winner_id) {
        Contest contest = new Contest();
        contest.setStartDate(Date.valueOf(start_date));
        contest.setGame( gameService.getGame(game_id).get() );
        if( winner_id != null) {
            contest.setWinner( playerService.getPlayer(winner_id).get() );
        }

        return new ContestDTO(contestService.saveContest(contest));
    }


    /**
     * supprimer
     */

    @DeleteMapping("/contest/{id}")
    public boolean deleteContest(@PathVariable("id") long id) {
        Optional<Contest> g = contestService.getContest(id); // objet optionnal
        if (g.isPresent()) {
            contestService.deleteContest(id);
            return true;
        } else {
            return false;
        }
    }

    /**
     * modifier le jeu
     */
    @PutMapping("/contest/{id}")
    public ContestDTO updateContest(@PathVariable("id") long id, @RequestBody Contest contest) {
        Optional<Contest> g = contestService.getContest(id);
        if(g.isPresent()) {
            contest.setId(id);
            
            return new ContestDTO(contestService.saveContest(contest));
        }
        return null;
    }

}