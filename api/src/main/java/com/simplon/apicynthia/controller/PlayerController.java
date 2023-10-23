package com.simplon.apicynthia.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.simplon.apicynthia.model.Contest;
import com.simplon.apicynthia.model.Player;
import com.simplon.apicynthia.model.dto.ContestDTO;
import com.simplon.apicynthia.model.dto.PlayerDTO;
import com.simplon.apicynthia.service.PlayerService;

import lombok.Data;

@Data
@RestController
public class PlayerController {
    @Id // clé primaire
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    @Column(length = 30)
    private String nickname;

    // instancier automaiquement
    @Autowired
    private PlayerService playerService;

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
    @GetMapping("/players")
    public Iterable<PlayerDTO> allPlayerss() {
        List<PlayerDTO> playersDTO = new ArrayList<PlayerDTO>();
        Iterable<Player> players = playerService.getAllPlayers();
        for (Player player : players) {
            playersDTO.add(new PlayerDTO(player));

        }
        return playersDTO;
    }

    /**
     * {} : c'est variable
     * PathVariable : recuperer
     */
    @GetMapping("/player/{id}")
    public PlayerDTO player(@PathVariable("id") int id) {
        Optional<Player> p = playerService.getPlayer(id); // objet optionnal
        if (p.isPresent()) {
            return new PlayerDTO(p.get()); // la methode get de l'objet Optional retourne
        } else {
            return null;
        }
    }

    /**
     * 
     */
    @PostMapping("/player")
    public PlayerDTO insertPlayer(@RequestBody Player p) {
        return new PlayerDTO(playerService.savePlayer(p));
    }

    /**
     * supprimer
     */

    @DeleteMapping("/player/{id}")
    public boolean deletePlayer(@PathVariable("id") long id) {
        Optional<Player> p = playerService.getPlayer(id); // objet optionnal
        if (p.isPresent()) {
            playerService.deletePlayer(id);
            return true;
        } else {
            return false;
        }
    }

    /**
     * modifier le jeu
     */
    @PutMapping("/player/{id}")
    public PlayerDTO updatGame(@PathVariable("id") long id, @RequestBody Player player) {
        Optional<Player> p = playerService.getPlayer(id);
        if (p.isPresent()) {
            Player playerToUpdate = p.get();
            if (player.getNickname() != null) {
                playerToUpdate.setNickname(player.getNickname());
            }
            if (player.getEmail() != null) {
                playerToUpdate.setNickname(player.getNickname());
            }
            return new PlayerDTO(playerService.savePlayer((player)));
        }
        return null;
    }

    @GetMapping("player/{id}/contests")
    public Iterable<ContestDTO> contests(@PathVariable("id") long id){
        Optional<Player> p = playerService.getPlayer(id);
        if(p.isPresent()){
            List<Contest> contests= p.get().getContests();
            List<ContestDTO> contestsDTO= new ArrayList<ContestDTO>();
            for (Contest contest : contests) {
                contestsDTO.add(new ContestDTO(contest));
            }
            return contestsDTO;
        }
        return null;
    }
}
