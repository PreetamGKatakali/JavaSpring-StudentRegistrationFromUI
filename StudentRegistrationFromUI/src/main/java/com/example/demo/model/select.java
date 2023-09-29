package com.example.demo.model;

public class select {
	String number;

	public select() {
		super();
	}

	public select(String number) {
		super();
		this.number = number;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "select [number=" + number + "]";
	}
}
