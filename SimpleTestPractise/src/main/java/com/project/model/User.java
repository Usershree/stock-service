package com.project.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class User {
	private String firstName;
	private String lastName;
	private long phoneNumber;
	private Address address;
}
