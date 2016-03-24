package it.mdnv.domain;

import java.io.Serializable;

public class Fornitore implements Serializable {

	private static final long serialVersionUID = 6882786992617394553L;

	public String model;
	public int year;
	public String brand;
	public String color;
	public int price;
	public boolean sold;

	public Fornitore(String model, int year, String brand, String color) {
		this.model = model;
		this.year = year;
		this.brand = brand;
		this.color = color;
	}

	public Fornitore(String model, int year, String brand, String color,
			int price, boolean sold) {
		this(model, year, brand, color);
		this.price = price;
		this.sold = sold;
	}

	public Fornitore(String model, String brand, int year, String color,
			int price, boolean sold) {
		this.model = model;
		this.brand = brand;
		this.year = year;
		this.color = color;
		this.price = price;
		this.sold = sold;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public boolean isSold() {
		return sold;
	}

	public void setSold(boolean sold) {
		this.sold = sold;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;

		if (!(obj instanceof Car))
			return false;

		Car compare = (Car) obj;

		return compare.model.equals(this.model);
	}

	@Override
	public int hashCode() {
		int hash = 1;

		return hash * 31 + model.hashCode();
	}

	@Override
	public String toString() {
		return "Car{" + "model=" + model + ", year=" + year + ", brand="
				+ brand + ", color=" + color + ", price=" + price + '}';
	}
}