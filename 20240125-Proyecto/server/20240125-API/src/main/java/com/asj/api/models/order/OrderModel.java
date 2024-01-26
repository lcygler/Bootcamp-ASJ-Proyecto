package com.asj.api.models.order;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

import com.asj.api.models.supplier.SupplierModel;
import com.asj.api.models.user.UserModel;

@Entity
@Table(name = "orders")
public class OrderModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(unique = true)
	private String number;

	@Column(name = "issue_date")
	private LocalDateTime issueDate;

	@Column(name = "delivery_date")
	private LocalDateTime deliveryDate;

	private String comments;
	
	private Double total;

	@ManyToOne
	@JoinColumn(name = "status_id")
	private StatusModel status;

	@ManyToOne
	@JoinColumn(name = "supplier_id")
	private SupplierModel supplier;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserModel user;

	@Column(name = "is_deleted")
	private Boolean isDeleted;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	public OrderModel() {
	}

	public OrderModel(Integer id, String number, LocalDateTime issueDate, LocalDateTime deliveryDate, String comments,
			Double total, StatusModel status, SupplierModel supplier, UserModel user, Boolean isDeleted,
			LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.id = id;
		this.number = number;
		this.issueDate = issueDate;
		this.deliveryDate = deliveryDate;
		this.comments = comments;
		this.total = total;
		this.status = status;
		this.supplier = supplier;
		this.user = user;
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

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public LocalDateTime getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(LocalDateTime issueDate) {
		this.issueDate = issueDate;
	}

	public LocalDateTime getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(LocalDateTime deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public StatusModel getStatus() {
		return status;
	}

	public void setStatus(StatusModel status) {
		this.status = status;
	}

	public SupplierModel getSupplier() {
		return supplier;
	}

	public void setSupplier(SupplierModel supplier) {
		this.supplier = supplier;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
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
		return "OrderModel [id=" + id + ", number=" + number + ", issueDate=" + issueDate + ", deliveryDate="
				+ deliveryDate + ", comments=" + comments + ", total=" + total + ", status=" + status + ", supplier="
				+ supplier + ", user=" + user + ", isDeleted=" + isDeleted + ", createdAt=" + createdAt + ", updatedAt="
				+ updatedAt + "]";
	}

}
