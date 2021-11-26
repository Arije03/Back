/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.model.iservice;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import api.model.entities.ERoles;
import api.model.entities.Roles;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author Makhlouf Helali
 */
@Service
public interface IRolesService {
    public ResponseEntity<Roles> createRoles(Roles role);
    public List<Roles> readAllRoless();
    public ResponseEntity<Roles> updateRoles(Roles role);
    public ResponseEntity<Map<String, Boolean>> deleteRoles(Integer id);
    public Optional<Roles> findRolesByName(ERoles name);
}
