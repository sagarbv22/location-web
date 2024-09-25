package com.pscube.location.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Location {

	@Id
	private Integer id;
	private String code;
	private String name;
	private String type;

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
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Location [id=" + id + ", code=" + code + ", name=" + name + ", type=" + type + "]";
	}

	public Location(Integer id, String code, String name, String type) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.type = type;
	}

}
