package api.model.iservice;

import api.model.entities.Clients;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

/**
 *
 * @author makhlouf
 */
@Service
public interface IClientsService {

	public Clients createClient(Clients client);

	public List<Clients> readAllClients();

	public Clients updateClient(Clients client);

	public Map<String, Boolean> deleteClient(Long id);

	public Clients findClientById(Long id);

	public List<Clients> findClientsByName(String name);

}
