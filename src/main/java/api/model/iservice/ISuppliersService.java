package api.model.iservice;

import api.model.entities.Suppliers;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author makhlouf
 */
@Service
public interface ISuppliersService {

	public Suppliers createSupplier(Suppliers supplier);

	public List<Suppliers> readAllSuppliers();
	
	public Suppliers updateSupplier(Suppliers supplier);

	public Map<String, Boolean> deleteSupplier(Long id);

	public Suppliers findSupplierById(Long id);

	public List<Suppliers> findSuppliersByName(String name);

}
