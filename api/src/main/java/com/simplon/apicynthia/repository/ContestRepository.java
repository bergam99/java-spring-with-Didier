// repository existe dans le projet java. 
package com.simplon.apicynthia.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.simplon.apicynthia.model.Contest;

// interface : interface tu peux implementer dans plusieurs class. une class peut heriter une seule class. mais interface peut utiliser un peu partout. ,class abstrait
// cette interface (crud) 
@Repository
public interface ContestRepository extends CrudRepository<Contest, Long>{

}

