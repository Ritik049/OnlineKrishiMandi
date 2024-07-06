package com.example.SupplyChainScalable.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SupplyChainScalable.Models.User;
import com.example.SupplyChainScalable.Service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	 @Autowired
	 UserService service;
	 
	 //All Users
	 @GetMapping("/")
	 public List<User> getAll()
	 {
		return service.getAll();
	 }
	 
	 //Single User
	 @GetMapping("/{id}")
	 public ResponseEntity<User> getById(@PathVariable Long id)
	 {
		 return service.getUserById(id);
	 }
	 
	 
	 //Create User
	 @PostMapping("/create")
	 public ResponseEntity<User> createUser(@RequestBody User user)
	 {
		 return service.create(user, user.getRole());
	 }
	 
	 
	 //UpdateUser
	 @PutMapping("/{id}/update")
	public ResponseEntity<User> updateUser (@RequestBody User user, @PathVariable Long id)
	{
		 return service.update(id, user);
	}
	 
	 //Delete User
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable Long id)
	{
		return service.delete(id);
	}
	 
	 
}
