package uk.ac.bangor.jml20vql.csee.NHSPrototype2.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import uk.ac.bangor.jml20vql.csee.NHSPrototype2.DistanceMatrix.DistanceMatrixAPI;
import uk.ac.bangor.jml20vql.csee.NHSPrototype2.distance.Address;
import uk.ac.bangor.jml20vql.csee.NHSPrototype2.distance.Choices;
import uk.ac.bangor.jml20vql.csee.NHSPrototype2.distance.Distance;
import uk.ac.bangor.jml20vql.csee.NHSPrototype2.repository.AddressRepository;
import uk.ac.bangor.jml20vql.csee.NHSPrototype2.repository.DistanceRepository;

@Controller
@RequestMapping("/MeetingCalculator")
public class MeetingController {
	
	@Autowired
	AddressRepository addressRepo;
	
	@Autowired
	DistanceRepository distanceRepo;
	
	@RequestMapping(method = RequestMethod.GET)
	public String showForm(Model model) throws Exception {
		
		model.addAttribute("addresses", addressToList(addressRepo.findAll()));
		model.addAttribute("choices", new Choices());	
		
		//model.addAttribute("distance", distance);
		
		return "MeetingCalculator";
	}
	
	public String showResults(Model model, float distance) throws Exception{
		
		model.addAttribute("addresses", addressToList(addressRepo.findAll()));
		model.addAttribute("choices", new Choices());	
		
		model.addAttribute("result", distance);
		
		return "results";
	}
	
	
	
	@RequestMapping(method = RequestMethod.POST)
	public String returnAnswer(@ModelAttribute("choices") Choices choices, Model model) throws Exception {
		
		// if both the origin point and the destination are the same, return 0
		if (choices.getOrigin().equals(choices.getDestination()))
			return showResults(model, 0f);
		
		
		
		String[] postcodes = orderedPostcodes(choices.getOrigin(), choices.getDestination());
		float distance = findDistance(distanceRepo.findAll(), postcodes[0], postcodes[1]);
		
		// if no distance for the two postcodes has been found in the table
		if(distance == -1f) {
			
			
			DistanceMatrixAPI api = new DistanceMatrixAPI(); 
			api.generateDistance(postcodes[0], postcodes[1]);
			
			
			// if the google maps api has generated a value
			if(api.miles() != -1f) {
				storeDistance(postcodes[0], postcodes[1], api.miles());
				return showResults(model, api.miles());
			}
			
		}
		return showResults(model, distance);
	}
	
	
	
	// return a string array of postcodes provided in alphabetical order
	private String[] orderedPostcodes(String postcode1, String postcode2) {
		String[] postcodes = new String[2];
		
		// postcode1 should be first alphabetically, not postcode2. If this is not the case, swap them
		if (!isInAlphabeticalOrder(postcode1, postcode2)) {
			String temp = postcode1;
			postcode1 = postcode2;
			postcode2 = temp;
		}
		
		postcodes[0] = postcode1;
		postcodes[1] = postcode2;
		
		return postcodes;
	}
	
	
	
	// store distance in the distance table
	private void storeDistance(String postcode1, String postcode2, float miles) {
		Distance distance = new Distance();
		distance.setMiles(miles);
		distance.setPostcode1(postcode1);
		distance.setPostcode2(postcode2);
		distance.setId(generateDistanceId());
		
		distanceRepo.save(distance);
	}
	
	
	
	// returns a string which is the next up from the largest id value
	private String generateDistanceId() {
		
		int i = 0;
		
		for (Distance distance : distanceRepo.findAll())
			i++;
		
		return Integer.toString(i);
		
	}
	
	
	
	// searches the distance table for an entry with both postcode1 and postcode2 and returns the distance between them
	private float findDistance(Iterable<Distance> list, String postcode1, String postcode2) {
		
		for (Distance distance : list)
			if (postcode1.equals(distance.getPostcode1()))
				if  (postcode2.equals(distance.getPostcode2()))
					return distance.getMiles();
		
		return -1f;
		
	}
	
	
	
	// converts address iterable to list
	private List<Address> addressToList(Iterable<Address> iterable) {
		
		List<Address> list = new ArrayList();
		
		for (Address address : iterable)
			list.add(address);
		
		return list;
	}
	
	
	
	// checks if the two strings are in alphabetical order
	private boolean isInAlphabeticalOrder(String string1, String string2) {
		
		char[] chars1 = string1.toLowerCase().toCharArray();
		char[] chars2 = string2.toLowerCase().toCharArray();
	
		
		for (int i = 0; i < string1.length(); i++)
			if (chars1[i] != chars2[i])
				if(characterValue(chars1[i]) < characterValue(chars2[i])) {
					return true;
				} else {
					return false;
				}
					
		return true;
			
	}
	
	
	
	// returns a number corresponding to a characters place in the alphabet
	private int characterValue(char charac) {
		switch(charac) {
		case '0':
			return 0;
		case '1':
			return 1;
		case '2':
			return 2;
		case '3':
			return 3;
		case '4':
			return 4;
		case '5':
			return 5;
		case '6':
			return 6;
		case '7':
			return 7;
		case '8':
			return 8;
		case '9':
			return 9;
		case 'a':
			return 10;
		case 'b':
			return 11;
		case 'c':
			return 12;
		case 'd':
			return 13;
		case 'e':
			return 14;
		case 'f':
			return 15;
		case 'g':
			return 16;
		case 'h':
			return 17;
		case 'i':
			return 18;
		case 'j':
			return 19;
		case 'k':
			return 20;
		case 'l':
			return 21;
		case 'm':
			return 22;
		case 'n':
			return 23;
		case 'o':
			return 24;
		case 'p':
			return 25;
		case 'q':
			return 26;
		case 'r':
			return 27;
		case 's':
			return 28;
		case 't':
			return 29;
		case 'u':
			return 30;
		case 'v':
			return 31;
		case 'w':
			return 32;
		case 'x':
			return 33;
		case 'y':
			return 34;
		case 'z':
			return 35;
		}
		
		return -1;
	}

	
}
