package com.asj.api.models.supplier;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

import com.asj.api.models.address.AddressModel;
import com.asj.api.models.common.ImageModel;

@Entity
@Table(name = "suppliers")
public class SupplierModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(unique = true)
	private String code;

	@Column(name = "business_name")
	private String businessName;

	@ManyToOne
	@JoinColumn(name = "industry_id")
	private IndustryModel industry;

	private String website;

	@Column(unique = true)
	private String email;

	private String phone;

	@OneToOne
	@JoinColumn(name = "image_id")
	private ImageModel image;

	@ManyToOne
	@JoinColumn(name = "address_id")
	private AddressModel address;

	@OneToOne
	@JoinColumn(name = "tax_information_id")
	private TaxInformationModel taxInformation;

	@OneToOne
	@JoinColumn(name = "contact_detail_id")
	private ContactDetailModel contactDetails;

	@Column(name = "is_deleted")
	private Boolean isDeleted;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	public SupplierModel() {
	}

	public SupplierModel(Integer id, String code, String businessName, IndustryModel industry, String website,
			String email, String phone, ImageModel image, AddressModel address, TaxInformationModel taxInformation,
			ContactDetailModel contactDetails, Boolean isDeleted, LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.id = id;
		this.code = code;
		this.businessName = businessName;
		this.industry = industry;
		this.website = website;
		this.email = email;
		this.phone = phone;
		this.image = image;
		this.address = address;
		this.taxInformation = taxInformation;
		this.contactDetails = contactDetails;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public IndustryModel getIndustry() {
		return industry;
	}

	public void setIndustry(IndustryModel industry) {
		this.industry = industry;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
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

	public ImageModel getImage() {
		return image;
	}

	public void setImage(ImageModel image) {
		this.image = image;
	}

	public AddressModel getAddress() {
		return address;
	}

	public void setAddress(AddressModel address) {
		this.address = address;
	}

	public TaxInformationModel getTaxInformation() {
		return taxInformation;
	}

	public void setTaxInformation(TaxInformationModel taxInformation) {
		this.taxInformation = taxInformation;
	}

	public ContactDetailModel getContactDetails() {
		return contactDetails;
	}

	public void setContactDetails(ContactDetailModel contactDetails) {
		this.contactDetails = contactDetails;
	}

	public Boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(Boolean isDeleted) {
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

}