package Pruebajar;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class ContadorEnBD {

	public static void main(String[] args) {
		
        Properties p = new Properties();
        
		try (FileInputStream input = new FileInputStream("config.ini");) {
			
			p.load(input);
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			
		}catch (IOException e) {
			
			e.printStackTrace();
			
		}
        
		final String TABLA = "CREATE TABLE IF NOT EXISTS contadores(nombre varchar(10) primary key, cuenta int);";
		final String sqlConsulta = "SELECT cuenta FROM contadores WHERE nombre=?;";
		final String sqlActualización = "UPDATE contadores SET cuenta=? WHERE nombre=?;";
		final String INSERTAR = "INSERT OR REPLACE INTO contadores VALUES ('contador1', 0);";
		final String claveContador = "contador1";
		final String RUTA = p.getProperty("conexion");

		try {
			Connection connection = DriverManager.getConnection("jdbc:sqlite:" + RUTA);
			
			PreparedStatement crear = connection.prepareStatement(TABLA);
			crear.execute();
			
			PreparedStatement insertar = connection.prepareStatement(INSERTAR);
			insertar.execute();
			
			PreparedStatement consulta = connection.prepareStatement(sqlConsulta);
			PreparedStatement actualización = connection.prepareStatement(sqlActualización);
			int cuenta = 0;
			int contador1 = 0;

			consulta.setString(1, claveContador);
			actualización.setString(2, claveContador);
			for (int i = 0; i < 1000; i++) {
				ResultSet res = consulta.executeQuery();
				if (res.next()) {
					cuenta = res.getInt(1) + 1;
					actualización.setInt(1, cuenta);
					actualización.executeUpdate();
				}
				// else break;
				else
					System.out.println("Error");
				// if (i%10==0) System.out.println(i/10 + "%");
			}
			System.out.println("Valor final: " + cuenta);

		} // try
		catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // main

} // class
