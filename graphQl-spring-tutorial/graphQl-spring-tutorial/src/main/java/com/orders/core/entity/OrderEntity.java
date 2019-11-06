package com.orders.core.entity;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
@Entity
public class OrderEntity extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 7052056387746328468L;


	@Column(name = "code",nullable=false,updatable=false,unique=true)
	private String code;	//UK
	
	@Column(name = "creation_date",nullable=false,updatable=false)
	private Instant creationDate;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="customer_id",nullable=false,updatable=false)
	private CustomerEntity customer;
	
	@Override
	public int hashCode() {
		return (this.getId()).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderEntity other = (OrderEntity) obj;
		return this.getId().equals(other.getId());
	}

}
