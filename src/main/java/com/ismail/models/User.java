package com.ismail.models;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;

import com.ismail.*;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private Long  id;

	
    @NotNull(message = "*Please provide an First name")
    @Size(min=2, max=15)
	private String firstName;
    
    @NotNull(message = "*Please provide an Last name")
    @Size(min=2, max=15)
	private String lastName;
	
	
	@NotNull(message = "*Please provide phone number")
	@Column(nullable=false)
	private Integer phoneNo;
	
	@Email(message = "*Please provide a valid Email")
	@NotEmpty(message = "*Please provide an email")
	@Column(nullable=false)
	private String email;
	
	@Column(nullable=true)
	private String password;
	
	@Column(nullable=false)
	private boolean active;
	
	@Column(nullable=true)
	private String emailConfirmation;
	
	
	@Column(nullable = false, updatable = false)
	 @Temporal(TemporalType.TIMESTAMP)
	 @CreatedDate
	private Date createdAt;
	 
	 @Column(nullable = false, updatable = false)
	 @Temporal(TemporalType.TIMESTAMP)
	 @LastModifiedDate
	private Date updatedAt;
	 
	
		@ManyToMany(cascade = {CascadeType.ALL, CascadeType.REMOVE})
		@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
		private Set<Role> roles;


		public Long getId() {
			return id;
		}


		public void setId(Long id) {
			this.id = id;
		}



		public String getFirstName() {
			return firstName;
		}


		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}


		public String getLastName() {
			return lastName;
		}


		public void setLastName(String lastName) {
			this.lastName = lastName;
		}


		public Integer getPhoneNo() {
			return phoneNo;
		}


		public void setPhoneNo(Integer phoneNo) {
			this.phoneNo = phoneNo;
		}


		public String getEmail() {
			return email;
		}


		public void setEmail(String email) {
			this.email = email;
		}


		public String getPassword() {
			return password;
		}


		public void setPassword(String password) {
			this.password = password;
		}


		public boolean isActive() {
			return active;
		}


		public void setActive(boolean active) {
			this.active = active;
		}


		public String getEmailConfirmation() {
			return emailConfirmation;
		}


		public void setEmailConfirmation(String emailConfirmation) {
			this.emailConfirmation = emailConfirmation;
		}



		public Date getCreatedAt() {
			return createdAt;
		}


		public void setCreatedAt(Date createdAt) {
			this.createdAt = createdAt;
		}


		public Date getUpdatedAt() {
			return updatedAt;
		}


		public void setUpdatedAt(Date updatedAt) {
			this.updatedAt = updatedAt;
		}


		public Set<Role> getRoles() {
			return roles;
		}


		public void setRoles(Set<Role> roles) {
			this.roles = roles;
		}


}
