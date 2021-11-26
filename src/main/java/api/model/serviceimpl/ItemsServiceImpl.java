package api.model.serviceimpl;

import api.exceptions.apiexceptions.ApiBadRequestException;
import api.exceptions.apiexceptions.ApiInternalServerException;
import api.exceptions.apiexceptions.ApiRessourceNotFoundException;
import api.model.entities.Items;
import api.model.entities.Suppliers;
import api.model.repository.ItemsRepository;
import api.model.repository.SuppliersRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import api.model.iservice.IItemsService;

/**
 *
 * @author makhlouf
 */
@Service
public class ItemsServiceImpl implements IItemsService {

	@Autowired
	private ItemsRepository itemsRepository;

	@Autowired
	private SuppliersRepository suppliersRepository;

	@Override
	public Items createItem(Items item) {
		if (item == null) {
			throw new ApiBadRequestException("Item object sent is null cannot save!");
		} else {
			try {
				return itemsRepository.save(item);
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
	public List<Items> readAllItems() {
		try {
			List listItems = itemsRepository.findAll();
			if (listItems.isEmpty()) {
				System.gc();
				throw new ApiRessourceNotFoundException("no items found");
			}
			return listItems;
		} catch (ApiInternalServerException e) {
			System.gc();
			throw new ApiInternalServerException(e.getMessage());
		}
	}

	@Override
	public Items updateItem(Items item) {
		if (item == null) {
			throw new ApiBadRequestException("Item object sent is null cannot save!");
		} else {
			try {
				return itemsRepository.save(item);
			} catch (Exception e) {
				throw new ApiInternalServerException(e.getMessage());
			}
		}
	}

	@Override
	public Map<String, Boolean> deleteItem(Long id) {
		if (id == null) {
			throw new ApiBadRequestException("cannot find item to delete!");
		} else {
			Map response = new HashMap<String, Boolean>();
			try {
				Items itemToDelete = itemsRepository.findById(id).get();
				if (itemToDelete == null) {
					throw new ApiRessourceNotFoundException("cannot find the item!");
				} else {
					itemsRepository.delete(itemToDelete);
					response.put("deleted", Boolean.TRUE);
					return response;
				}
			}catch (Exception e) {
				throw new ApiInternalServerException("Internal server error");
			}
		}
	}

	@Override
	public Items findItemById(Long id) {
		if (id == null) {
			throw new ApiBadRequestException("the id shuld not be null!");
		} else {
			try {
				return itemsRepository.findById(id).get();
			} catch (Exception e) {
				System.gc();
				throw new ApiRessourceNotFoundException("item not foud");
			}
		}
	}

	@Override
	public List<Items> findItemsByWording(String wording) {
		if (wording == null) {
			throw new ApiBadRequestException("the item wording shuld not be null!");
		} else {
			try {
				List<Items> items = itemsRepository.findItemsByWording(wording);
				if (items == null || items.isEmpty()) {
					System.gc();
					throw new ApiRessourceNotFoundException("No item found with this name");
				}
				return items;
			} catch (ApiBadRequestException | ApiRessourceNotFoundException e) {
				throw e;
			} catch (Exception e) {
				System.gc();
				throw new ApiInternalServerException(e.getMessage());
			}
		}
	}

	@Override
	public List<Items> findItemsByCode(Long code) {
		if (code == null) {
			throw new ApiBadRequestException("the code shuld not be null!");
		} else {
			try {
				List<Items> listItems = itemsRepository.findItemsByCode(code);
				if (listItems == null || listItems.isEmpty()) {
					System.gc();
					throw new ApiRessourceNotFoundException("no items with this code!");
				}
				return listItems;

			} catch (ApiRessourceNotFoundException e) {
				System.gc();
				throw new ApiRessourceNotFoundException("No items with this code!");
			} catch (ApiBadRequestException e) {
				System.gc();
				throw new ApiBadRequestException("No items with this code");
			} catch (Exception e) {
				System.gc();
				throw new ApiInternalServerException("Internal server error");
			}
		}

	}

	@Override
	public Boolean existsByCode(Long code) {
		return itemsRepository.existsByCode(code);
	}

	@Override
	public Boolean existsByWording(String wording) {
		return itemsRepository.existsByWording(wording);
	}

	@Override
	public List<Items> getSupplierItems(Suppliers supplier) {
		List<Items> supplierItems = new ArrayList();
		Suppliers supplierWanted;
		if (supplier == null) {
			throw new ApiBadRequestException("You shuld send a supplier!");
		} else {
			try {
				supplierWanted = suppliersRepository.findById(supplier.getId()).get();
				if (supplierWanted == null) {
					throw new ApiRessourceNotFoundException("Supplier not found");
				}
				List<Items> items = itemsRepository.findAll();
				if (items.isEmpty()) {
					throw new ApiRessourceNotFoundException("No items found!");
				} else {

					for (Items item : items) {

						if (item.getSupplier() != null && item.getSupplier() == supplierWanted) {
							supplierItems.add(item);
						}
					}
				}
				if (supplierItems.isEmpty()) {
					supplierItems = null;
					throw new ApiRessourceNotFoundException("No items asssigned to this supplier");
				}

				items = null;
				supplierWanted = null;

				System.gc();
				return supplierItems;
			} catch (ApiRessourceNotFoundException e) {
				throw e;
			} catch (ApiBadRequestException e) {
				throw e;
			} catch (Exception e) {
				throw new ApiInternalServerException("Internal server error");
			}
		}
	}

}
