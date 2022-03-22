package com.example.demo.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Util.EmailUtil;
import com.example.demo.bindings.User;
import com.example.demo.constatnts.AppConstants;
import com.example.demo.entity.CityEntity;
import com.example.demo.entity.CountryEntity;
import com.example.demo.entity.StateEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.props.AppProoerties;
import com.example.demo.repository.CityRepository;
import com.example.demo.repository.CountryRepository;
import com.example.demo.repository.StateRepository;
import com.example.demo.repository.UserRepository;

@Service
public class RegistrationServiceImpl implements RegistrationService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private CityRepository cityRepo;

	@Autowired
	private StateRepository stateRepo;

	@Autowired
	private CountryRepository countryRepo;

	@Autowired
	private EmailUtil emailUtil;
	
	@Autowired
	private AppProoerties appProoerties;

	@Override
	public boolean uniqeEmail(String email) {
		UserEntity userEntity = userRepo.findByUserEmaill(email);
		if (userEntity != null) {
			return false;
		}
		return true;
	}

	@Override
	public Map<Integer, String> getCountries() {
		List<CountryEntity> findCountryEntities = countryRepo.findAll();
		Map<Integer, String> countryMap = new HashMap<>();
		for (CountryEntity entity : findCountryEntities) {
			countryMap.put(entity.getCountryId(), entity.getCountryName());
		}
		return countryMap;
	}

	@Override
	public Map<Integer, String> getStates(Integer countryId) {
		List<StateEntity> lisEntities = stateRepo.findByCountryId(countryId);
		Map<Integer, String> stateMap = new HashMap<>();
		for (StateEntity stateEntity : lisEntities) {
			stateMap.put(stateEntity.getStateId(), stateEntity.getStatneName());
		}
		return stateMap;
	}

	@Override
	public Map<Integer, String> getCities(Integer stateId) {
		List<CityEntity> cityEntities = cityRepo.findByStateID(stateId);
		Map<Integer, String> cityMap = new HashMap<>();
		for (CityEntity cityEntity : cityEntities) {
			cityMap.put(cityEntity.getCityId(), cityEntity.getCityName());
		}
		return cityMap;
	}

	@Override
	public boolean registerUser(User user) {
		user.setUserPwd(generateTempPwd());
		user.setSetUserAccStatus(AppConstants.LOCKED);

		UserEntity entity = new UserEntity();
		BeanUtils.copyProperties(user, entity);

		UserEntity save = userRepo.save(entity);
		if (null != save.getUserId()) {
			return sendEmail(user);
		}
		return false;
	}

	private String generateTempPwd() {
		String tempPwd = null;
		int leftLimit = 48;
		int rightLimit = 122;
		int targetStringLength = 6;
		Random random = new Random();
		tempPwd = random.ints(leftLimit, rightLimit + 1).filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
				.limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
		return tempPwd;
	}

	private boolean sendEmail(User user) {
		boolean emailSent = false;
		// String subject = "User Registration Succesfull";
		Map<String, String> message = appProoerties.getMessage();
		String subject = message.get(AppConstants.REG_MAIL_SUBJECT);
		String bodyFileName = message.get(AppConstants.REG_MAIL_BODY_TEMPLATE_FILE);
		String body = readEmilBody(bodyFileName, user);
		emailUtil.sendEmail(subject, body, user.getUserEmaill());
		return emailSent;
	}

	public String readEmilBody(String fileNAme, User user) {
		String mailBody = null;
		StringBuffer buffer = new StringBuffer();
		Path path = Paths.get(fileNAme);
		try (Stream<String> stream = Files.lines(path)) {
			stream.forEach(line -> {
				buffer.append(line);
			});
			mailBody = buffer.toString();
			mailBody.replace(AppConstants.FNAME, user.getUserFname());
			mailBody.replace(AppConstants.EMAIL, user.getUserEmaill());
			mailBody.replace( AppConstants.TEMP_PWD, user.getUserPwd());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mailBody;
	}

}
