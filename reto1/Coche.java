package reto1;

public class Coche {

	String marca;
	String modelo;
	String color;
	int ruedas;
	
	
	public Coche(String marca, String modelo, String color, int ruedas) {
		this.marca = marca;
		this.modelo = modelo;
		this.color = color;
		this.ruedas = ruedas;
	}

	@Override
	public String toString() {
		return marca + "," + modelo + "," + color + "," + ruedas;
	}
	
}

