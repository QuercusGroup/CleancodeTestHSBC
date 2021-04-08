package uk.co.hsbc.library.model;

import lombok.Getter;
import lombok.Setter;
import uk.co.hsbc.library.config.Gender;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Setter
@Entity
public class User {

	@Id @GeneratedValue private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private Gender gender;
	private Instant subscriptionDate;
	private boolean isStaff;
	private boolean isLibrarian;
}
