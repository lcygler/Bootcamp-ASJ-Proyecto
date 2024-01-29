package com.asj.api.models.address;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(name = "addresses")
public class AddressModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false, length = 255)
	@NotBlank
	@Size(max = 255)
	private String street;

	@Column(nullable = false, length = 255)
	@NotBlank
	@Size(max = 255)
	private String number;

	@Column(name = "postal_code", nullable = false, length = 255)
	@NotBlank
	@Size(max = 255)
	private String postalCode;

	@Column(nullable = false, length = 255)
	@NotBlank
	@Size(max = 255)
	private String city;

	@ManyToOne
	@JoinColumn(name = "state_id", nullable = false)
	@NotNull
	private StateModel state;

	@Column(name = "created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime updatedAt;

	public AddressModel() {
	}

	public AddressModel(Integer id, String street, String number, String postalCode, String city, StateModel state,
			LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.id = id;
		this.street = street;
		this.number = number;
		this.postalCode = postalCode;
		this.city = city;
		this.state = state;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public StateModel getState() {
		return state;
	}

	public void setState(StateModel state) {
		this.state = state;
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
		return "AddressModel [id=" + id + ", street=" + street + ", number=" + number + ", postalCode=" + postalCode
				+ ", city=" + city + ", state=" + state + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}

}
