package com.asj.api.models.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

import com.asj.api.models.address.AddressModel;

@Entity
@Table(name = "users")
public class UserModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank
	@Size(max = 255)
	@Column(name = "first_name")
	private String firstName;

	@NotBlank
	@Size(max = 255)
	@Column(name = "last_name")
	private String lastName;

	@NotBlank
	@Size(max = 255)
	@Column(unique = true)
	private String dni;

	@NotBlank
	@Size(max = 255)
	private String email;

	@NotBlank
	@Size(max = 255)
	private String phone;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "genre_id")
	private GenreModel genre;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "address_id")
	private AddressModel address;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "role_id")
	private RoleModel role;

	@Column(name = "is_deleted")
	private Boolean isDeleted;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	public UserModel() {
	}

	public UserModel(Integer id, String firstName, String lastName, String dni, String email, String phone,
			GenreModel genre, AddressModel address, RoleModel role, Boolean isDeleted, LocalDateTime createdAt,
			LocalDateTime updatedAt) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dni = dni;
		this.email = email;
		this.phone = phone;
		this.genre = genre;
		this.address = address;
		this.role = role;
		this.isDeleted = isDeleted;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public GenreModel getGenre() {
		return genre;
	}

	public void setGenre(GenreModel genre) {
		this.genre = genre;
	}

	public AddressModel getAddress() {
		return address;
	}

	public void setAddress(AddressModel address) {
		this.address = address;
	}

	public RoleModel getRole() {
		return role;
	}

	public void setRole(RoleModel role) {
		this.role = role;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return "UserModel [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", dni=" + dni
				+ ", email=" + email + ", phone=" + phone + ", genre=" + genre + ", address=" + address + ", role="
				+ role + ", isDeleted=" + isDeleted + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}

}
