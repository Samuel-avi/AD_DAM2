package reto1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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
			
			/* 3. no, porque solo se puede guardar un valor por clave, para eso habria que usar ObjectOutputStream o guardarlo en csv */
			
			
		} catch (IOException e) {
			System.out.println("Esta mal");
		}
		
		
		
		
		
		
	

	}

}
