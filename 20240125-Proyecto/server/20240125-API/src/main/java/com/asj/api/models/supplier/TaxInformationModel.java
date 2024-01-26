package com.asj.api.models.supplier;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "tax_information")
public class TaxInformationModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(unique = true)
	private String cuit;

	@ManyToOne
	@JoinColumn(name = "vat_condition_id")
	private VatConditionModel vatCondition;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	public TaxInformationModel() {
	}

	public TaxInformationModel(Integer id, String cuit, VatConditionModel vatCondition, LocalDateTime createdAt,
			LocalDateTime updatedAt) {
		this.id = id;
		this.cuit = cuit;
		this.vatCondition = vatCondition;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public VatConditionModel getVatCondition() {
		return vatCondition;
	}

	public void setVatCondition(VatConditionModel vatCondition) {
		this.vatCondition = vatCondition;
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
		return "TaxInformationModel [id=" + id + ", cuit=" + cuit + ", vatCondition=" + vatCondition + ", createdAt="
				+ createdAt + ", updatedAt=" + updatedAt + "]";
	}

}
