package api.model.serviceimpl;

import api.exceptions.apiexceptions.ApiBadRequestException;
import api.exceptions.apiexceptions.ApiInternalServerException;
import api.exceptions.apiexceptions.ApiRessourceNotFoundException;
import api.model.entities.Clients;
import api.model.repository.ClientsRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import api.model.iservice.IClientsService;

/**
 *
 * @author makhlouf
 */
@Service
public class ClientsServiceImpl implements IClientsService {

	@Autowired
	private ClientsRepository clientsRepository;

	@Override
	public Clients createClient(Clients client) {
		if (client == null) {
			throw new ApiBadRequestException("Client object sent is null cannot save!");
		} else {
			try {
				Clients clientToSave = clientsRepository.save(client);
				return clientToSave;
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
	public List<Clients> readAllClients() {
		try {
			List listClients = clientsRepository.findAll();
			if (listClients.isEmpty()) {
				listClients = null;
				System.gc();
				throw new ApiRessourceNotFoundException("no clients found");
			}
			return listClients;
		} catch (ApiInternalServerException e) {
			System.gc();
			throw new ApiInternalServerException(e.getMessage());
		}
	}

	@Override
	public Clients updateClient(Clients client) {
		if (client == null) {
			throw new ApiBadRequestException("Client object sent is null cannot save!");
		} else {
			try {
				return clientsRepository.save(client);
			} catch (Exception e) {
				throw new ApiInternalServerException(e.getMessage());
			}
		}
	}

	@Override
	public Map<String, Boolean> deleteClient(Long id) {
		if (id == null) {
			throw new ApiBadRequestException("the id sent is null, cannot find client to delete!");
		} else {
			Map response = new HashMap<String, Boolean>();
			try {
				Clients clientToDelete = clientsRepository.findById(id).get();
				if (clientToDelete == null) {
					System.gc();
					response.put("deleted", Boolean.FALSE);
					throw new ApiRessourceNotFoundException("cannot find anu client with this id!");
				} else {
					clientsRepository.delete(clientToDelete);
					response.put("deleted", Boolean.TRUE);
					clientToDelete = null;
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
	public Clients findClientById(Long id) {
		if (id == null) {
			throw new ApiBadRequestException("the id shuld not be null!");
		} else {
			try {
				return clientsRepository.findById(id).get();
			} catch (Exception e) {
				System.gc();
				throw new ApiRessourceNotFoundException("client not foud");
			}
		}
	}

	@Override
	public List<Clients> findClientsByName(String name) {
		if (name == null) {
			throw new ApiBadRequestException("the client name shuld not be null!");
		} else {
			try {
				List<Clients> clients = clientsRepository.findClientsByName(name);
				if (clients == null || clients.isEmpty()) {
					System.gc();
					throw new ApiRessourceNotFoundException("No client found with this name");
				}
				return clients;
			} catch (ApiBadRequestException | ApiRessourceNotFoundException e) {
				throw e;
			} catch (Exception e) {
				System.gc();
				throw new ApiInternalServerException(e.getMessage());
			}
		}
	}

	
	
}
