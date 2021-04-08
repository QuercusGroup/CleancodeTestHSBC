package uk.co.hsbc.library.service.model;

import lombok.Getter;
import lombok.Setter;
import uk.co.hsbc.library.config.Gender;

import java.time.Instant;

@Getter
@Setter
public class UserInfo {

	private String firstName;
	private String lastName;
	private String email;
	private Gender gender;
	private Instant subscriptionDate;
	private boolean isStaff;
	private boolean isLibrarian;
}
