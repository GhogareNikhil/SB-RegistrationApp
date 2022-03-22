package com.example.demo.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bindings.User;
import com.example.demo.constatnts.AppConstants;
import com.example.demo.service.RegistrationService;

@RestController
public class RegistrationRestController {

	@Autowired
	private RegistrationService registrationService;

	@GetMapping("/emailCheck/{email}")
	public String checkEmail(@PathVariable String email) {
		boolean uniquemail = registrationService.uniqeEmail(email);
		if (uniquemail) {
			return AppConstants.UNIQUE;
		} else {
			return AppConstants.DUPLICATE;
		}
	}

	@GetMapping("/countries")
	public Map<Integer, String> getCointries() {
		return registrationService.getCountries();
	}

	@GetMapping("/states/{countryId}")
	public Map<Integer, String> getStates(@PathVariable Integer countryId) {
		return registrationService.getStates(countryId);
	}

	@GetMapping("/cities/{stateId}")
	public Map<Integer, String> getCities(@PathVariable Integer stateId) {
		return registrationService.getCities(stateId);
	}

	@PostMapping("/saveUser")
	public String saveUSer(@RequestBody User user) {
		boolean register = registrationService.registerUser(user);
		if (register) {
			return AppConstants.SUCCESS;
		} else {
			return AppConstants.FAIL;
		}
	}

}
