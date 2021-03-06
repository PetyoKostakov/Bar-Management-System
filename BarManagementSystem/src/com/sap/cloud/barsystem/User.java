
package com.sap.cloud.barsystem;



/**
 * Class holding information on a person.
 */
public class User {
    private String userName;
    private String firstName;
    private String lastName;
    private String password;
    private int access;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String newUserName) {
        this.userName = newUserName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String newFirstName) {
        this.firstName = newFirstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String newLastName) {
        this.lastName = newLastName;
    }
    
    public void setAccess (int newAccess) {
    	this.access = newAccess;
    }
    
    public int getAccess (){
    	return this.access;
    	
    }
    
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }
    
    
    
}
