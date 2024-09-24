package com.pscube.location.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Location {

	@Id
	private Integer id;
	private String code;
	private String name;
	private String Type;

	
	public Location() {
		
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	@Override
	public String toString() {
		return "Location [id=" + id + ", name=" + name + ", Type=" + Type + "]";
	}

	public Location(Integer id, String code, String name, String type) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		Type = type;
	}

}
