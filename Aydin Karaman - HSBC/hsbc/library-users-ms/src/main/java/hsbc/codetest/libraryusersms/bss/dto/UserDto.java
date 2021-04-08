package hsbc.codetest.libraryusersms.bss.dto;

import java.sql.Date;

import javax.validation.constraints.NotBlank;

public class UserDto {
	private Long id;

	@NotBlank(message = "Please provide a name")
	private String name;

	@NotBlank(message = "Please provide a surname")
	private String surname;

	private Date birthdate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

}
