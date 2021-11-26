/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.model.repository;

import api.model.entities.Items;
import api.model.entities.Suppliers;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author makhlouf
 */
@Repository
public interface ItemsRepository extends JpaRepository<Items, Long> {

	List<Items> findItemsByCode(Long code);

	List<Items> findItemsByWording(String wording);

	Boolean existsByCode(Long code);

	Boolean existsByWording(String wording);

}
