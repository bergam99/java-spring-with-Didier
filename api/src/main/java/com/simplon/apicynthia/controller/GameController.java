package com.simplon.apicynthia.controller;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.simplon.apicynthia.model.Game;
import com.simplon.apicynthia.model.dto.GameDTO;
import com.simplon.apicynthia.service.GameService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

// toutes les annotations au dessus de class s'appelle bean (haricot). j'utilise cette annotation pour donner un role particulier dans la class 
@RestController
public class GameController {

    // instancier automaiquement
    @Autowired
    private GameService gameService;

    // pour eviter ça on fait : @Autowired
    // public GameController(){
    // this.gameService = new GameService();
    // }

    /**
     * renvoie tous les jeux
     * GetMapping : permet de relier cette méthode 'allGames' à une URL qui sera
     * appelée en méthode HTTP GET.
     * 
     * @return
     */
    @GetMapping("/games")
    public Iterable<GameDTO> allGames() {
        Iterable<Game> games = gameService.getAllGames();
        List<GameDTO> gamesDTO = new ArrayList<GameDTO>();
        for (Game game : games) {
            gamesDTO.add(new GameDTO(game));
        }
        return gamesDTO;
    }

    /**
     * {} : c'est variable
     * PathVariable : recuperer
     */
    @GetMapping("/game/{id}")
    public GameDTO game(@PathVariable("id") int id) {
        Optional<Game> g = gameService.getGame(id); // objet optionnal
        if (g.isPresent()) {
            return new GameDTO(g.get()); // la methode get de l'objet Optional retourne
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
    @PostMapping("/game")
    public GameDTO insertGame(@RequestBody Game g) {
        return new GameDTO(gameService.saveGame(g));
    }

    /**
     * supprimer
     */

    @DeleteMapping("/game/{id}")
    public boolean deleteGame(@PathVariable("id") long id) {
        Optional<Game> g = gameService.getGame(id); // objet optionnal
        if (g.isPresent()) {
            gameService.deleteGame(id);
            return true;
        } else {
            return false;
        }
    }

    /**
     * modifier le jeu
     */
    @PutMapping("/game/{id}")
    public Game updatGame(@PathVariable("id") long id, @RequestBody Game game) {
        Optional<Game> g = gameService.getGame(id);
        if (g.isPresent()) {
            Game gameToUpdate = g.get();
            if (game.getTitle() != null) {
                gameToUpdate.setTitle(game.getTitle());
            }
            if (game.getMin() != null) {
                gameToUpdate.setMin(game.getMin());
            }
            if (game.getMax() != null) {
                gameToUpdate.setMin(game.getMax());
            }
            return gameService.saveGame(gameToUpdate);
        }
        return null;
    }
}