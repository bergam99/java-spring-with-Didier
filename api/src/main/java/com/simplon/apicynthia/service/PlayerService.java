package com.simplon.apicynthia.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simplon.apicynthia.model.Player;
import com.simplon.apicynthia.repository.PlayerRepository;

@Service

public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    public Iterable<Player> getAllPlayers() {
    return playerRepository.findAll();
    }

        public Optional<Player> getPlayer(final long id){
        return playerRepository.findById(id);
    }

    /**
     * ajouter/modifier 
     */
    public Player savePlayer(Player p) {
        return this.playerRepository.save(p);
    }

    /**
     * Supprimer 
     */
    public void deletePlayer(final long id){
        this.playerRepository.deleteById(id);
    }
}