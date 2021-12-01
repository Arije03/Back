/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.controllers;

import api.model.entities.Items;
import api.model.entities.Suppliers;
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
import api.model.iservice.IItemsService;

/**
 *
 * @author Makhlouf Helali
 */
@CrossOrigin(origins = "${api.app.crossOriginsHosts}", maxAge = 3600)
@RestController
@RequestMapping("/api/items")
public class ItemsController {

	@Autowired
	private IItemsService itemsService;

	@GetMapping("/")
	public ResponseEntity<ApiResponse> listItems(
		   @RequestParam(required = false) String wording,
		   @RequestParam(required = false) Long code
	) {

		List<Items> listItems = new ArrayList();
		if (wording != null) {
			itemsService.findItemsByWording(wording).forEach(listItems::add);
			
		}else if (code != null) {
			itemsService.findItemsByCode(code).forEach(listItems::add);
		}else{
			itemsService.readAllItems().forEach(listItems::add);
		}
		return ResponseEntity.ok(new ApiResponse(HttpStatus.OK, "items succesfully fetched", listItems));
	}

	@PostMapping("/new")
	public ResponseEntity<ApiResponse> createItem(@NotNull @RequestBody(required = false) Items item) {
		return ResponseEntity.ok(new ApiResponse(HttpStatus.OK, "Item successfully created", itemsService.createItem(item)));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse> getItemById(@NotNull @PathVariable(required = false) Long id) {
		return ResponseEntity.ok(new ApiResponse(HttpStatus.OK, "Item successfully fetched", itemsService.findItemById(id)));
	}

	@GetMapping("/itemwording/{wording}")
	public ResponseEntity<ApiResponse> getItemByWording(@NotNull @PathVariable(required = false) String wording) {
		return ResponseEntity.ok(new ApiResponse(HttpStatus.OK, "Item successfully fetched", itemsService.findItemsByWording(wording)));
	}

	@GetMapping("/itemcode/{code}")
	public ResponseEntity<ApiResponse> getItemByCode(@NotNull @PathVariable(required = false) Long code) {
		return ResponseEntity.ok(new ApiResponse(HttpStatus.OK, "Item successfully fetched", itemsService.findItemsByCode(code)));
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<ApiResponse> updateItem(@NotNull @PathVariable Long id, @RequestBody(required = false) Items item) {
		return ResponseEntity.ok(new ApiResponse(HttpStatus.OK, "Item successfully updated", itemsService.updateItem(item)));
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ApiResponse> deleteItem(@NotNull @PathVariable(required = false) Long id) {
		return ResponseEntity.ok(new ApiResponse(HttpStatus.OK, "Item successfully deleted", itemsService.deleteItem(id)));
	}

	@GetMapping("/supplieritems")
	public ResponseEntity<ApiResponse> listSupplierItems(@NotNull @RequestBody(required = false) Suppliers supplier) {
		return ResponseEntity.ok(new ApiResponse(HttpStatus.OK, "Supplier items successfully fetched", itemsService.getSupplierItems(supplier)));
	}

}
