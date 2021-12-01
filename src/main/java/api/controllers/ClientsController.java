package api.controllers;

import api.model.entities.Clients;
import api.model.http.responseModel.ApiResponse;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import api.model.iservice.IClientsService;

/**
 *
 * @author Makhlouf Helali
 */
@CrossOrigin(origins = "${api.app.crossOriginsHosts}", maxAge = 3600)
@RestController
@RequestMapping("/api/clients")
public class ClientsController {

	@Autowired
	private IClientsService clientsService;

	@GetMapping("/")
	public ResponseEntity<ApiResponse> listClients(
		   @RequestParam(required = false) String name
	) {

		List<Clients> listClients = new ArrayList();
		if (name != null) {
			clientsService.findClientsByName(name).forEach(listClients::add);
			
		}else{
			clientsService.readAllClients().forEach(listClients::add);
		}
		return ResponseEntity.ok(new ApiResponse(HttpStatus.OK, "clients succesfully fetched", listClients));
	}

	@PostMapping("/new")
	public ResponseEntity<ApiResponse> createClient(@NotNull @RequestBody(required = false) Clients client) {
		return ResponseEntity.ok(new ApiResponse(HttpStatus.OK, "Client successfully created", clientsService.createClient(client)));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse> getClientById(@NotNull @PathVariable(required = false) Long id) {
		return ResponseEntity.ok(new ApiResponse(HttpStatus.OK, "Client successfully fetched", clientsService.findClientById(id)));
	}

	@GetMapping("/clientname/{name}")
	public ResponseEntity<ApiResponse> getClientByName(@NotNull @PathVariable(required = false) String name) {
		return ResponseEntity.ok(new ApiResponse(HttpStatus.OK, "Client successfully fetched", clientsService.findClientsByName(name)));
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<ApiResponse> updateClient(@NotNull @PathVariable Long id, @RequestBody(required = false) Clients client) {
		return ResponseEntity.ok(new ApiResponse(HttpStatus.OK, "Client successfully updated", clientsService.updateClient(client)));
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ApiResponse> deleteClient(@NotNull @PathVariable(required = false) Long id) {
		return ResponseEntity.ok(new ApiResponse(HttpStatus.OK, "Client successfully deleted", clientsService.deleteClient(id)));
	}


}
