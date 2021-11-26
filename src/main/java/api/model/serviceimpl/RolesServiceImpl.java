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
import api.model.entities.ERoles;
import api.model.entities.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import api.model.repository.RolesRepository;
import api.model.iservice.IRolesService;

/**
 *
 * @author Makhlouf Helali
 */
@Service
public class RolesServiceImpl implements IRolesService {

    @Autowired
    private RolesRepository roleRepository;
    
    @Override
    public ResponseEntity<Roles> createRoles(Roles role) {
        return ResponseEntity.ok(roleRepository.save(role));
    }

    @Override
    public List<Roles> readAllRoless() {
        return roleRepository.findAll();
    }

    @Override
    public ResponseEntity<Roles> updateRoles(Roles role) {
        Roles roleToUpdate = roleRepository.getById(role.getId());
        roleToUpdate = role;
        return ResponseEntity.ok(roleRepository.save(roleToUpdate));
    }

    @Override
    public ResponseEntity<Map<String, Boolean>> deleteRoles(Integer id) {
        roleRepository.delete(roleRepository.findById(id).get());
        Map<String, Boolean> response = new HashMap();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    @Override
    public Optional<Roles> findRolesByName(ERoles name) {
        return roleRepository.findRoleByName(name);
    }
    
}
