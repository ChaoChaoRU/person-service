package telran.java47.person.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.java47.person.model.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {
	
	List<Person> findByBirthDateBetween(LocalDate minAge, LocalDate maxAge);

}
