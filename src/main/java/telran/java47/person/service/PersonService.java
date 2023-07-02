package telran.java47.person.service;

import java.util.List;

import telran.java47.person.dto.AddressDto;
import telran.java47.person.dto.CityPopulationDto;
import telran.java47.person.dto.PersonDto;

public interface PersonService {
	
	Boolean addPerson(PersonDto person);
	
	PersonDto findPersonById(Integer id);
	
	List<PersonDto> findPersonByCity(String city);
	
	List<PersonDto> findPersonByAges(Integer minAge, Integer maxAge);
	
	PersonDto updateName(Integer id, String name);
	
	List<PersonDto> findPersonByName(String name);
	
	List<CityPopulationDto> getCityPopulation();
	
	PersonDto updateAddress(AddressDto newAddress, Integer id);
	
	PersonDto deletePerson(Integer id);

}
