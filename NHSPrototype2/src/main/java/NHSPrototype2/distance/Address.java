package uk.ac.bangor.jml20vql.csee.NHSPrototype2.distance;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.context.annotation.Primary;

@Entity
public class Address {
	
	@Id
	private String postcode;
	private String address_Line_1;
	private String address_Line_2;
	private String name;
	private String city;
	
	
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getAddress_Line_1() {
		return address_Line_1;
	}
	public void setAddress_Line_1(String address_Line_1) {
		this.address_Line_1 = address_Line_1;
	}
	public String getAddress_Line_2() {
		return address_Line_2;
	}
	public void setAddress_Line_2(String address_Line_2) {
		this.address_Line_2 = address_Line_2;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	public String toString() {
		return name + ", " + city;
	}

}
