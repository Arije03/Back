package api.model.serviceimpl;

import api.exceptions.apiexceptions.ApiBadRequestException;
import api.exceptions.apiexceptions.ApiInternalServerException;
import api.exceptions.apiexceptions.ApiRessourceNotFoundException;
import api.model.entities.Suppliers;
import api.model.repository.SuppliersRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import api.model.iservice.ISuppliersService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author makhlouf
 */
@Service
public class SuppliersServiceImpl implements ISuppliersService {

	@Autowired
	private SuppliersRepository suppliersRepository;

	@Override
	public Suppliers createSupplier(Suppliers supplier) {
		if (supplier == null) {
			throw new ApiBadRequestException("Supplier object sent is null cannot save!");
		} else {
			try {
				return suppliersRepository.save(supplier);
			} catch (ApiBadRequestException e) {
				System.gc();
				throw e;
			} catch (Exception e) {
				System.gc();
				throw new ApiInternalServerException(e.getMessage());
			}
		}
	}

	@Override
	public List<Suppliers> readAllSuppliers() {
		try {
			List listSuppliers = suppliersRepository.findAll();
			if (listSuppliers.isEmpty()) {
				throw new ApiRessourceNotFoundException("no suppliers found");
			}
			return listSuppliers;
		} catch (ApiInternalServerException e) {
			throw new ApiInternalServerException("Internal server error!");
		}
	}

	@Override
	public Suppliers updateSupplier(Suppliers supplier) {
		if (supplier == null) {
			throw new ApiBadRequestException("Supplier object sent is null cannot save!");
		} else {
			try {
				return suppliersRepository.save(supplier);
			} catch (Exception e) {
				throw new ApiInternalServerException(e.getMessage());
			}
		}
	}

	@Override
	public Map<String, Boolean> deleteSupplier(Long id) {
		if (id == null) {
			throw new ApiBadRequestException("the id sent is null, cannot find supplier to delete!");
		} else {
			Map response = new HashMap<String, Boolean>();
			try {
				Suppliers supplierToDelete = suppliersRepository.findById(id).get();
				if (supplierToDelete == null) {
					System.gc();
					response.put("deleted", Boolean.FALSE);
					throw new ApiRessourceNotFoundException("cannot find anu supplier with this id!");
				} else {
					suppliersRepository.delete(supplierToDelete);
					response.put("deleted", Boolean.TRUE);
					supplierToDelete = null;
					System.gc();
					return response;
				}

			} catch (ApiBadRequestException | ApiRessourceNotFoundException e) {
				throw e;
			} catch (Exception e) {
				throw new ApiInternalServerException("Internal server error");
			}
		}
	}

	@Override
	public Suppliers findSupplierById(Long id) {
		if (id == null) {
			throw new ApiBadRequestException("the id shuld not be null!");
		} else {
			try {
				return suppliersRepository.findById(id).get();
			} catch (Exception e) {
				System.gc();
				throw new ApiRessourceNotFoundException("supplier not foud");
			}
		}
	}

	@Override
	public List<Suppliers> findSuppliersByName(String name) {
		if (name == null) {
			throw new ApiBadRequestException("the supplier name shuld not be null!");
		} else {
			try {
				List<Suppliers> suppliers = suppliersRepository.findSuppliersByName(name);
				if (suppliers == null || suppliers.isEmpty()) {
					System.gc();
					throw new ApiRessourceNotFoundException("No supplier found with this name");
				}
				return suppliers;
			} catch (ApiBadRequestException | ApiRessourceNotFoundException e) {
				throw e;
			} catch (Exception e) {
				System.gc();
				throw new ApiInternalServerException(e.getMessage());
			}
		}
	}

}
