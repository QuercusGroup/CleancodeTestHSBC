package com.librarymanagementsystem.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class User {


	@Id
    private String userid;

    @NotBlank
    private String name;

	private String address;

	public User() {
    }

    public User(String id,String name, String address) {
    	this.userid = id;
        this.name = name;
        this.address = address;
    }

    public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
    public String toString() {
        return "User{" + "name='" + name + '\'' + ", address='" + address + '\'' + '}';
    }
}
