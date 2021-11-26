/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.model.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import api.model.entities.EUnits;
import api.model.entities.Units;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import api.model.repository.UnitsRepository;
import api.model.iservice.IUnitsService;

/**
 *
 * @author Makhlouf Helali
 */
@Service
public class UnitsServiceImpl implements IUnitsService {

    @Autowired
    private UnitsRepository unitRepository;
    
    @Override
    public ResponseEntity<Units> createUnit(Units unit) {
        return ResponseEntity.ok(unitRepository.save(unit));
    }

    @Override
    public List<Units> readAllUnits() {
        return unitRepository.findAll();
    }

    @Override
    public ResponseEntity<Units> updateUnit(Units unit) {
        Units unitToUpdate = unitRepository.getById(unit.getId());
        unitToUpdate = unit;
        return ResponseEntity.ok(unitRepository.save(unitToUpdate));
    }

    @Override
    public ResponseEntity<Map<String, Boolean>> deleteUnit(Integer id) {
        unitRepository.delete(unitRepository.findById(id).get());
        Map<String, Boolean> response = new HashMap();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    @Override
    public Optional<Units> findUnitByName(EUnits name) {
        return unitRepository.findUnitByName(name);
    }
    
}
