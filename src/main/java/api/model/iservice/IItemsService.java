package api.model.iservice;

import api.model.entities.Items;
import api.model.entities.Suppliers;
import api.model.http.responseModel.ApiResponse;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

/**
 *
 * @author makhlouf
 */
@Service
public interface IItemsService {

	public Items createItem(Items item);

	public List<Items> readAllItems();

	public Items updateItem(Items item);

	public Map<String, Boolean> deleteItem(Long id);

	public Items findItemById(Long id);

	public List<Items> findItemsByCode(Long code);

	public List<Items> findItemsByWording(String wording);

	public Boolean existsByCode(Long code);

	public Boolean existsByWording(String wording);

	public List<Items> getSupplierItems(Suppliers supplier);

}
