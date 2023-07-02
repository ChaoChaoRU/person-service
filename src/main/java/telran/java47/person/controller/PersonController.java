package telran.java47.person.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import telran.java47.person.dto.AddressDto;
import telran.java47.person.dto.CityPopulationDto;
import telran.java47.person.dto.PersonDto;
import telran.java47.person.service.PersonService;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController{
	
	final PersonService personService;

	@PostMapping
	public Boolean addPerson(@RequestBody PersonDto person) {
		return personService.addPerson(person);
	}

	@GetMapping("/{id}")
	public PersonDto findPersonById(@PathVariable Integer id) {
		return personService.findPersonById(id);
	}

	@GetMapping("/city/{city}")
	public List<PersonDto> findPersonByCity(@PathVariable String city) {
		return personService.findPersonByCity(city);
	}

	@GetMapping("/ages/{minAge}/{maxAge}")
	public List<PersonDto> findPersonByAges(@PathVariable Integer minAge, @PathVariable Integer maxAge) {
		return personService.findPersonByAges(minAge, maxAge);
	}

	@PutMapping("/{id}/name/{name}")
	public PersonDto updateName(@PathVariable Integer id, @PathVariable String name) {
		return personService.updateName(id, name);
	}

	@GetMapping("/name/{name}")
	public List<PersonDto> findPersonByName(@PathVariable String name) {
		return personService.findPersonByName(name);
	}

	@GetMapping("/population/city")
	public List<CityPopulationDto> getCityPopulation() {
		return personService.getCityPopulation();
	}

	@PutMapping("/{id}/address")
	public PersonDto updateAddress(@RequestBody AddressDto newAddress, @PathVariable Integer id) {
		return personService.updateAddress(newAddress, id);
	}

	@DeleteMapping("/{id}")
	public PersonDto deletePerson(@PathVariable Integer id) {
		return personService.deletePerson(id);
	}
	
	
}
