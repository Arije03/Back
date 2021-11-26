/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.model.iservice;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import api.model.entities.EUnits;
import api.model.entities.Units;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author Makhlouf Helali
 */
@Service
public interface IUnitsService {
    public ResponseEntity<Units> createUnit(Units unit);
    public List<Units> readAllUnits();
    public ResponseEntity<Units> updateUnit(Units unit);
    public ResponseEntity<Map<String, Boolean>> deleteUnit(Integer id);
    public Optional<Units> findUnitByName(EUnits name);
}
