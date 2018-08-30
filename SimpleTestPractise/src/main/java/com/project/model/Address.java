package com.project.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Address {
	private String add1;
	private String add2;
	private String area;
	private String city;
	private String landmark;
	private String state;
	private int pincode;
}
