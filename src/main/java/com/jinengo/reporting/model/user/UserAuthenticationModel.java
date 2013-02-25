package com.jinengo.reporting.model.user;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * user authentication model
 * @author lars schuettemeyer
 *
 */
@Entity
@Table(name = "UserAuthentication")
public class UserAuthenticationModel {
	
	@Id
    @NotEmpty(message = "Bitte gebe eine gültige E-Mail-Adresse ein!")
    private String userEmail;
    @NotEmpty(message = "Bitte gebe ein Passwort ein")
    private String userPassword;
    private String userRole;
    
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

}
