package api.model.repository;

import java.util.Optional;
import api.model.entities.ERoles;
import api.model.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Makhlouf Helali
 */
@Repository
public interface RolesRepository extends JpaRepository<Roles, Integer> {

    /**
     * Returns the role with the name sent in @param,
     * which may or may not contain a non-null Role object.
     * If the role wanted is present,  isPresent() will return true and
     * get() will return the role.
     * 
     * @param name
     * @return
     */
    Optional<Roles> findRoleByName(ERoles name);
}
