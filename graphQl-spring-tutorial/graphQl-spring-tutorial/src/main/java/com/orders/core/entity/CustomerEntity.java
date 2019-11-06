package com.orders.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@EnableJpaAuditing
@Table(name="customers")
@Entity
@NoArgsConstructor // required for entity beans
@AllArgsConstructor
public class CustomerEntity extends BaseEntity implements Serializable{

	private static final long serialVersionUID = 8888213858445788286L;

	@Column(name = "code",nullable=false,updatable=false,unique=true)
	private String code;	//UK
	
	@NotEmpty //validation
	@Column(name = "first_name" ,nullable=false,updatable=true)
	private String firstName;
	
	@NotEmpty //validation
	@Column(name = "last_name" ,nullable=false,updatable=true)
	private String lastName;
	
	@Email //validation
	@Column(name = "email" ,nullable=false,updatable=false)
	private String email;

	
	@Override
	public int hashCode() {
		return this.getId().hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerEntity other = (CustomerEntity) obj;
		return this.getId().equals(other.getId());
	}
	
}
