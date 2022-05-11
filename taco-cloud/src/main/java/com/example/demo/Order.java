package com.example.demo;

import java.util.Date;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.CreditCardNumber;

import lombok.Data;

@Data
@Entity
@Table(name="Taco_Order")
public class Order implements Serializable{
	private static final long SerialVersionUID = 1;
	@Id
	private Long id;
	
	private Date placeAt;
	
	@NotBlank(message="Name is require")
	private String name;
	@NotBlank(message="Street is required")
	private String street;
	@NotBlank(message="City is required")
	private String city;
	@Size(max=2, message = "State's maxsize is 2")
	@NotBlank(message="State is required")
	private String state;
	@NotBlank(message="Zip code is required")
	private String zip;
	@CreditCardNumber(message="Not a valid credit card number")
	private String ccNumber;
	@Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
			 message="Must be formatted MM/YY")
	private String ccExpiration;
	@Digits(integer = 3, fraction=0, message="invalid CVV")
	private String ccCVV;
	@ManyToMany(targetEntity = Taco.class)
	private List<Taco> tacos = new ArrayList<>(); 
	
	public void addDesign(Taco design) {
		this.tacos.add(design);
	}
	@PrePersist
	void placeAt() {
		this.placeAt = new Date();
	}
}
