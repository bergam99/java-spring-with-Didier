package com.simplon.apicynthia.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simplon.apicynthia.model.Game;
import com.simplon.apicynthia.repository.GameRepository;

@Service
public class GameService {

    @Autowired /* signifie connexion automatique / permet de faire l'injection de dépendences sans instancié */
    private GameRepository gameRepository;
    
    /* le @Autowired permet de ne pas créer de constructeur */
    /*  public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    } */


    /* Méthodes en public pour être utilisable en dehors */
    
    
    /**
     * Methodes Récupérer tous les jeux 
     */
    
    /* Iterable est une classe générique : je vais renvoyer un objet d'une classe Game qui peut etre itérable (ou on peut boucler dessus) */
    public Iterable<Game> getAllGames() {
        return gameRepository.findAll();
    }


    /**
     * Récupérer un jeu avec son id
     */

    /*  Optional est une classe générique: je vais renvoyer ou pas un objet Game ou alors null, final pour constante signifie que l'id ne peut pas etre modifié */
    public Optional<Game> getGame(final long id){
        return gameRepository.findById(id);
    }

    /**
     * ajouter/modifier un jeu
     */
    public Game saveGame(Game g) {
        return this.gameRepository.save(g);
    }

    /**
     * Supprimer un jeu
     */
    public void deleteGame(final long id){
        this.gameRepository.deleteById(id);
    }
}