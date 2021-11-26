/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.model.repository;

import api.model.entities.Clients;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author makhlouf
 */
@Repository
public interface ClientsRepository extends JpaRepository<Clients, Long> {
    
    public List<Clients> findClientsByName(String name);
}
