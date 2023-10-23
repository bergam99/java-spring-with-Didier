package com.simplon.apicynthia.repository;

import org.springframework.stereotype.Repository;

import com.simplon.apicynthia.model.Player;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Long>{
    
}