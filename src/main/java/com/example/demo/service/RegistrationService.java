package com.example.demo.service;

import java.util.Map;

import com.example.demo.bindings.User;

public interface RegistrationService {
public boolean uniqeEmail(String email);
public Map<Integer, String> getCountries();
public Map<Integer, String> getStates(Integer countryId);
public Map<Integer, String> getCities(Integer stateId);
public boolean registerUser(User user);
}
