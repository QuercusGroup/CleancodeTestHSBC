package uk.co.hsbc.library.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Library {

	@Id @GeneratedValue	private Long id;
	private String name;
	private String address;
	private String email;
	private String phoneNumber;
	private boolean isSelf;
}
