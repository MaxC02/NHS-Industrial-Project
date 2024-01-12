package uk.ac.bangor.jml20vql.csee.NHSPrototype2.distance;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Distance {
	
	@Id
	private String id;
	private String postcode1;
	private String postcode2;
	private float miles;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPostcode1() {
		return postcode1;
	}
	public void setPostcode1(String postcode1) {
		this.postcode1 = postcode1;
	}
	public String getPostcode2() {
		return postcode2;
	}
	public void setPostcode2(String postcode2) {
		this.postcode2 = postcode2;
	}
	public float getMiles() {
		return miles;
	}
	public void setMiles(float distance) {
		this.miles = distance;
	}
	
	

}
