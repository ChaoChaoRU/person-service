package telran.java47.person.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.java47.person.dao.PersonRepository;
import telran.java47.person.dto.AddressDto;
import telran.java47.person.dto.CityPopulationDto;
import telran.java47.person.dto.PersonDto;
import telran.java47.person.dto.exeptions.PersonNotFoundException;
import telran.java47.person.model.Address;
import telran.java47.person.model.Person;

@RequiredArgsConstructor
@Service
public class PersonServiceImpl implements PersonService {
	
	final PersonRepository personRepository;
	final ModelMapper modelMapper;

	@Override
	public Boolean addPerson(PersonDto person) {
		if(personRepository.existsById(person.getId())) {
			return false;
		}
		personRepository.save(modelMapper.map(person, Person.class));
		return true;
	}

	@Override
	public PersonDto findPersonById(Integer id) {
		Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
		return modelMapper.map(person, PersonDto.class);
	}

	@Override
	public List<PersonDto> findPersonByCity(String city) {
		return personRepository.findAll()
								.stream()
								.filter(p -> p.getAddress().getCity().equalsIgnoreCase(city))
								.map(p -> modelMapper.map(p, PersonDto.class))
								.collect(Collectors.toList());
	}

	@Override
	public List<PersonDto> findPersonByAges(Integer minAge, Integer maxAge) {
		return personRepository
				.findByBirthDateBetween(LocalDate.now().minusYears(maxAge), LocalDate.now().minusYears(minAge))
				.stream()
				.map(p -> modelMapper.map(p, PersonDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public PersonDto updateName(Integer id, String name) {
		Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
		person.setName(name);
		personRepository.save(person);
		return modelMapper.map(person, PersonDto.class);
	}

	@Override
	public List<PersonDto> findPersonByName(String name) {
		return personRepository.findAll()
								.stream()
								.filter(p -> p.getName().equalsIgnoreCase(name))
								.map(p -> modelMapper.map(p, PersonDto.class))
								.collect(Collectors.toList());
	}

	@Override
	public List<CityPopulationDto> getCityPopulation() {
		Map<String, Long> cities = personRepository.findAll()
													.stream()
													.map(p -> p.getAddress().getCity())
													.collect(Collectors.groupingBy(p -> p, Collectors.counting()));
		return cities.entrySet()
					.stream()
					.map(entry -> new CityPopulationDto(entry.getKey(), entry.getValue().intValue()))
					.collect(Collectors.toList());
	}

	@Override
	public PersonDto updateAddress(AddressDto newAddress, Integer id) {
		Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
		person.setAddress(modelMapper.map(newAddress, Address.class));
		personRepository.save(person);
		return modelMapper.map(person, PersonDto.class);
	}

	@Override
	public PersonDto deletePerson(Integer id) {
		Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
		personRepository.delete(person);
		return modelMapper.map(person, PersonDto.class);
	}

}
