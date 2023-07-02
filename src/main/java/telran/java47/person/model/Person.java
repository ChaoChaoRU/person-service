package telran.java47.person.model;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Entity
public class Person implements Serializable {

	private static final long serialVersionUID = -1250554987023825175L;
	
	@Id
	Integer id;
	@Setter
	String name;
	LocalDate birthDate;
	@Setter
//	@Embedded
	Address address;
	

}
