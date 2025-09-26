package reto1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class Main {

	public static void main(String[] args) {
		
		Properties p = new Properties();
		
		p.setProperty("coche","rojo");
		p.setProperty("camion","verde");
		p.setProperty("autobus","azul");
		p.setProperty("remolque","blanco");
		p.setProperty("tren","negro");
		
		try {
			
			FileOutputStream fos = new FileOutputStream("datos1");
			p.store(fos, "si");
			
			FileOutputStream fos2 = new FileOutputStream("datos2.xml");
			p.storeToXML(fos2, "si");
			
			
			FileInputStream fis = new FileInputStream("datos1");
			p.load(fis);
			
			System.out.println("Valores requperados de datos 1" + p);
			
			System.out.println("Valor la clave tren: " + p.getProperty("tren"));
			
			FileInputStream fis2 = new FileInputStream("datos2.xml");
			p.loadFromXML(fis2);
			
			System.out.println("Valores requperados de datos 2" + p);
			
			System.out.println("Valor la clave coche: " + p.getProperty("coche"));
			
			/// 3. no, porque solo se puede guardar un valor por clave, para eso habria que usar ObjectOutputStream o guardarlo en csv 
			
			
		} catch (IOException e) {
			System.out.println("¿Que ha pasado?");
		}
		
		
		List<Coche> lc = new ArrayList<>();
		
		lc.add(new Coche("Toyota", "Corolla", "Rojo", 4));
		lc.add(new Coche("Honda", "Civic", "Azul", 4));
		lc.add(new Coche("Ford", "Focus", "Negro", 4));
		lc.add(new Coche("Chevrolet", "Camaro", "Amarillo", 4));
		lc.add(new Coche("Tesla", "Model 3", "Blanco", 4));
		lc.add(new Coche("BMW", "X5", "Gris", 4));
		lc.add(new Coche("Audi", "A4", "Plata", 4));
		lc.add(new Coche("Mercedes", "C200", "Negro", 4));
		lc.add(new Coche("Volkswagen", "Golf", "Verde", 4));
		lc.add(new Coche("Fiat", "500", "Rojo", 4));
		lc.add(new Coche("Jeep", "Wrangler", "Verde", 4));
		lc.add(new Coche("Subaru", "Impreza", "Azul", 4));
		lc.add(new Coche("Volvo", "XC90", "Plata", 4));
		lc.add(new Coche("Porsche", "911", "Negro", 4));

		try {
			PrintWriter pw = new PrintWriter("coches.csv");
			
			pw.println("Marca,Modelo,Color,Ruedas");
			
			for (Coche coche : lc) {
				pw.println(coche);
			}
			
			pw.close();
			
		} catch (Exception e) {

		} ///Siguiente ---> generar una nueva lista a partir del csv con BufferedReader 
		
	}

}
