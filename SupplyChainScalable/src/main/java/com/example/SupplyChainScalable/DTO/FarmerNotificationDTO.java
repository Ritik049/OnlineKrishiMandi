package com.example.SupplyChainScalable.DTO;

import java.util.List;

import com.example.SupplyChainScalable.Models.Notification;

public class FarmerNotificationDTO {

	  private String name;
	  private List<Notification> lst;
	  
	  public FarmerNotificationDTO(String name, List<Notification> lst)
	  {
		  this.name = name;
		  this.lst = lst;
	  }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Notification> getLst() {
		return lst;
	}

	public void setLst(List<Notification> lst) {
		this.lst = lst;
	}

	public FarmerNotificationDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	
	  
}
