/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.controllers;

import api.model.http.responseModel.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import api.model.iservice.IUsersService;
import org.springframework.http.HttpStatus;

/**
 *
 * @author Makhlouf Helali
 */
@CrossOrigin(origins = "${api.app.crossOriginsHosts}", maxAge = 3600)
@RestController
@RequestMapping("/api/users")
public class UsersController {

    @Autowired
    private IUsersService userManager;
    
    @GetMapping("/")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity allUsers() {
        return ResponseEntity.ok(userManager.readAllUsers());
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('GUIDE') or hasRole('ADMIN')")
    public ResponseEntity userAccess() {
        return ResponseEntity.ok(new MessageResponse(HttpStatus.OK, "User board"));
    }

    @GetMapping("/guide")
    @PreAuthorize("hasRole('GUIDE')")
    public ResponseEntity guideAccess() {
        return ResponseEntity.ok(new MessageResponse(HttpStatus.OK, "Guide board"));
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity adminAccess() {
        return ResponseEntity.ok(new MessageResponse(HttpStatus.OK, "Admin board"));
    }
}
