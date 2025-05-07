package com.app.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name="User_Regi_Login")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Size(min=3, max = 50, message = "Username must contain at least 3 characters")
	@Column(unique = true)
	private String username;
	
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,15}$", 
	         message = "Password must be 8-15 characters long and include uppercase, lowercase, number, and special character.")
	@Size(min=8, max = 15, message = "password must contain minimum 8 characters")
	
	private String password;
	
	private String emaiId;

	@Transient      //dont store this value on db
	private String cfmPassword;
	
	private String role;
	
	@Column(nullable = false)
    private boolean status ;
	
	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime createdDate;
	
	@LastModifiedDate
	private LocalDateTime updatedDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmaiId() {
		return emaiId;
	}

	public void setEmaiId(String emaiId) {
		this.emaiId = emaiId;
	}

	public String getCfmPassword() {
		return cfmPassword;
	}

	public void setCfmPassword(String cfmPassword) {
		this.cfmPassword = cfmPassword;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
}
