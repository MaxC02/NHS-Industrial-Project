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
@RequestMapping("/EditAddress")
public class EditController {
	
	@Autowired
	AddressRepository repo;

	@RequestMapping(method = RequestMethod.GET)
	public String showForm(Model model) throws Exception {
		
		model.addAttribute("address", new Address());
		
		return "edit";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String returnAnswers(@ModelAttribute("address") Address address, Model model) throws Exception {
		
		if (contains(repo.findAll(), address.getPostcode())) {
			repo.deleteById(address.getPostcode());
			repo.save(address);
		}
		
		return showForm(model);
	}
	
	
	// check if a given address list contains a given postcode
	private boolean contains(Iterable<Address> list, String postcode) {
		
		for (Address currentAddress : list)
			if (postcode.equals(currentAddress.getPostcode()))
				return true;
		
		return false;
	}
	
}
