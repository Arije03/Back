/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.model.repository;

import api.model.entities.Suppliers;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author makhlouf
 */
@Repository
public interface SuppliersRepository extends JpaRepository<Suppliers, Long> {
    @Query("SELECT s from Suppliers s WHERE s.name LIKE :x")
    public List<Suppliers> findSuppliersByName(@Param("x")String name);
}
