package com.sap.cloud.barsystem;



/**
 * Class holding information on a dish.
 */
public class Drink {
    private String drinkName;
    private double price;
  
   
    private int id;

    public String getDrinkName() {
        return drinkName;
    }

    public void setDrinkName(String newDrinkName) {
        this.drinkName = newDrinkName;
    }

 
    
    public void setId (int newId) {
    	this.id = newId;
    }
    
    public int getId (){
    	return this.id;
    	
    }
    
    
    public void setPrice(double newPrice){
    	this.price = newPrice;
    	
    }
    
    public double getPrice(){
    	return price;
    }
    
    
    
    
    
    
}
