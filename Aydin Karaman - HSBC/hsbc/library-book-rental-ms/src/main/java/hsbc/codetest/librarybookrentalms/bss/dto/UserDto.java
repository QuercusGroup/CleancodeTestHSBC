package hsbc.codetest.librarybookrentalms.bss.dto;

import java.sql.Date;

import javax.persistence.AttributeConverter;

import hsbc.codetest.librarybookrentalms.utils.JsonUtil;

public class UserDto implements AttributeConverter<UserDto, String>{
	private Long id;	
	private String name;
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

	@Override
	public String convertToDatabaseColumn(UserDto attribute) {
		return JsonUtil.mapToString(attribute);
	}

	@Override
	public UserDto convertToEntityAttribute(String dbData) {
		return dbData == null ? new UserDto() : JsonUtil.mapToObject(dbData, UserDto.class);
	}
}
