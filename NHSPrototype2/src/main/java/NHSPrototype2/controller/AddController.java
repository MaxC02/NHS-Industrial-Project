package uk.ac.bangor.jml20vql.csee.NHSPrototype2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import uk.ac.bangor.jml20vql.csee.NHSPrototype2.distance.Address;
import uk.ac.bangor.jml20vql.csee.NHSPrototype2.repository.AddressRepository;


@Controller
@RequestMapping("/AddAddress")
public class AddController {
	
	@Autowired
	AddressRepository repo;

	@RequestMapping(method = RequestMethod.GET)
	public String showForm(Model model) throws Exception {
		
		model.addAttribute("address", new Address());
		
		return "add";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String returnAnswers(@ModelAttribute("address") Address address, Model model) throws Exception {
		
		saveAddress(address);
		
		return showForm(model);
	}
	
	private void saveAddress(Address address) {
		
		if(!contains(repo.findAll(), address))
			repo.save(address);
			
	}
	
	// check if a given address list contains a given postcode
	private boolean contains(Iterable<Address> list, Address address) {
		
		for (Address currentAddress : list)
			if (address.getPostcode().equals(currentAddress.getPostcode()))
				return true;
		
		return false;
	}
	
}
