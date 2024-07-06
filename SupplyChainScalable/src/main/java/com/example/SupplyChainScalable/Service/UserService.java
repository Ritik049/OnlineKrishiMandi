package com.example.SupplyChainScalable.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.SupplyChainScalable.Models.Buyer;
import com.example.SupplyChainScalable.Models.Farmer;
import com.example.SupplyChainScalable.Models.User;
import com.example.SupplyChainScalable.Repository.BuyerRepository;
import com.example.SupplyChainScalable.Repository.FarmerRepository;
import com.example.SupplyChainScalable.Repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository repo;
	
	@Autowired
	FarmerRepository farmerRepo;
	
	@Autowired
	BuyerRepository buyerRepo;
	
	
	//CRUD
	
	//GET ALL
	public List<User> getAll()
	{
		try {
			return repo.findAll();
		}
		catch(Exception e)
		{
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Unable to retrieve users",e);
		}
	}
	
	
	//GET BY ID
	public ResponseEntity<User> getUserById(Long id)
	{
	    try {
	    	Optional<User> user = repo.findById(id);
	    	
	    	if(user.isPresent())
	    	{
	    		return ResponseEntity.ok(user.get());
	    	}
	    	else
	    	{
	    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	    	}
	    }
	    catch(Exception e)
	    { 
	    	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Unable to retrieve users",e);
	    }
	}
	
	
	
	
	//Create User
	public ResponseEntity<User>create(User user,String role)
	{
		try {
			
			User createdUser = null;
			
			if(role.equalsIgnoreCase("FARMER"))
			{
				Farmer farmer =  new Farmer(user.getName(),user.getEmail(),user.getPassword(),user.getLocation(),user.getImage(),user.getRole());
				
				createdUser  = farmerRepo.save(farmer);
			}
			else if(role.equalsIgnoreCase("BUYER"))
			{
				Buyer buyer = new Buyer(user.getName(),user.getEmail(),user.getPassword(),user.getLocation(),user.getImage(),user.getRole());
				createdUser = buyerRepo.save(buyer);
			}
			else
			{
				createdUser = repo.save(user);
			}
			return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
		}
		catch(Exception e)
		{
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Unable to create user",e);
		}
	}
	
	
	
	//Update user with userId
	public ResponseEntity<User> update(Long id, User user)
	{
		try {
			   Optional<User> existingUserOpt = repo.findById(id);
			   
			   if(existingUserOpt.isPresent())
			   {
				   User existingUser = existingUserOpt.get();
				   
				   if(!existingUser.getRole().equals(user.getRole()))
				   {
					   return ResponseEntity.status(HttpStatus.FORBIDDEN).body(user);  //We are forbidding user to role change;
				   }
				   existingUser.setName(user.getName());
				   existingUser.setEmail(user.getEmail());
				   existingUser.setPassword(user.getPassword());
				   existingUser.setLocation(user.getLocation());
				   existingUser.setImage(user.getImage());
				   
				   User updatedUser = existingUser;
				   
				   return ResponseEntity.ok(updatedUser);
			   }
			   else
			   {
				   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			   }
		}
		catch(Exception e)
		{
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Unable to retreive existing user",e);
		}
	}
	
	
	
	
	
	//Not need to do as they are already mapped.
//	public User update(Long id, String name)
//	{
//		Farmer farmer = farmerRepo.findById(id).get();
//		
//		farmer.setName(name);
//		
//		return farmerRepo.save(farmer);
//		
//	}
	
	
	
	//Delete User ID;
	 public ResponseEntity<String> delete(Long id) {
	        try {
	            repo.deleteById(id);
	            return ResponseEntity.ok("Successfully deleted");
	        } catch (EmptyResultDataAccessException e) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
	        } catch (Exception e) {
	            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to delete user", e);
	        }
	    }
	
	
	
}
