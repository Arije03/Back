package api.controllers;

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
import api.model.iservice.ISuppliersService;

/**
 *
 * @author Makhlouf Helali
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/suppliers")
public class SuppliersController {

	@Autowired
	private ISuppliersService suppliersService;

	@GetMapping("/")
	public ResponseEntity<ApiResponse> listSuppliers(
		   @RequestParam(required = false) String name
	) {

		List<Suppliers> listSuppliers = new ArrayList();
		if (name != null) {
			suppliersService.findSuppliersByName(name).forEach(listSuppliers::add);
			
		}else{
			suppliersService.readAllSuppliers().forEach(listSuppliers::add);
		}
		return ResponseEntity.ok(new ApiResponse(HttpStatus.OK, "suppliers succesfully fetched", listSuppliers));
	}

	@PostMapping("/new")
	public ResponseEntity<ApiResponse> createSupplier(@NotNull @RequestBody(required = false) Suppliers supplier) {
		return ResponseEntity.ok(new ApiResponse(HttpStatus.OK, "Supplier successfully created", suppliersService.createSupplier(supplier)));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse> getSupplierById(@NotNull @PathVariable(required = false) Long id) {
		return ResponseEntity.ok(new ApiResponse(HttpStatus.OK, "Supplier successfully fetched", suppliersService.findSupplierById(id)));
	}

	@GetMapping("/suppliername/{name}")
	public ResponseEntity<ApiResponse> getSupplierByName(@NotNull @PathVariable(required = false) String name) {
		return ResponseEntity.ok(new ApiResponse(HttpStatus.OK, "Supplier successfully fetched", suppliersService.findSuppliersByName(name)));
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<ApiResponse> updateSupplier(@NotNull @PathVariable Long id, @RequestBody(required = false) Suppliers supplier) {
		return ResponseEntity.ok(new ApiResponse(HttpStatus.OK, "Supplier successfully updated", suppliersService.updateSupplier(supplier)));
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ApiResponse> deleteSupplier(@NotNull @PathVariable(required = false) Long id) {
		return ResponseEntity.ok(new ApiResponse(HttpStatus.OK, "Supplier successfully deleted", suppliersService.deleteSupplier(id)));
	}


}
