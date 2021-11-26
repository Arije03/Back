package api.model.repository;

import java.util.Optional;
import api.model.entities.EUnits;
import api.model.entities.Units;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Makhlouf Helali
 */
@Repository
public interface UnitsRepository extends JpaRepository<Units, Integer> {

    /**
     * Returns the unit with the name sent in @param,
     * which may or may not contain a non-null Unit object.
     * If the unit wanted is present,  isPresent() will return true and
     * get() will return the unit.
     * 
     * @param name
     * @return
     */
    Optional<Units> findUnitByName(EUnits name);
}
