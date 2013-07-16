package com.sap.cloud.barsystem;

public class Order {

	
	




	/**
	 * Class holding information on a dish.
	 */
	
	    private String items;
	    private double endPrice;
	    private int id;
	    private int amount;
	    private String orderedBy;

	    public String getItems() {
	        return items;
	    }

	    public void setItems(String newItems) {
	        this.items = newItems;
	    }

	 
	    
	    public void setId (int newId) {
	    	this.id = newId;
	    }
	    
	    public int getId (){
	    	return this.id;
	    	
	    }
	    
	    
	    public void setEndPrice(double newEndPrice){
	    	this.endPrice = newEndPrice;
	    	
	    }
	    
	    public double getEndPrice(){
	    	return endPrice;
	    }
	    
	    public int getAmount(){
	    	return amount;
	    }
	    
	    public void setAmount(int newAmount){
	    	this.amount= newAmount;
	    }
	    
	    	
		public String getOrderedBy() {
		        return orderedBy;
		}
		
		
		public void setOrderedBy(String newOrderedBy){
			this.orderedBy = newOrderedBy;
		}
	    	
	    	
	       
	    
	}

	
	
	
	
	
	
	
	
	
	
	
	
	

