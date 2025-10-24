package gestionAlumnos.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ModeloAlumnosJDBC implements IModeloAlumnos {

	private static String cadenaConexion =  "jdbc:mysql://localhost:3306/instituto";
	
	//CLASE
	//private static String user = "dam2";
	//private static String pass = "asdf.1234";
	
	//CASA	
	private static String user = "root";
	private static String pass = "root";
	
	public ModeloAlumnosJDBC() {
		crearTabla();
	}
	
	//crea la tabla si no existe
    private void crearTabla() {
        final String TABLA = """
            CREATE TABLE IF NOT EXISTS Alumno (
                dni VARCHAR(9) PRIMARY KEY,
                nombre VARCHAR(50) NOT NULL,
                apellidos VARCHAR(100) NOT NULL,
                cp CHAR(5)
            );
        """;

        try (Connection conexion = DriverManager.getConnection(cadenaConexion, user, pass);
             PreparedStatement stmt = conexion.prepareStatement(TABLA)) {
            stmt.execute();
            System.out.println("✅ Tabla 'Alumno' verificada o creada correctamente.");
        } catch (SQLException e) {
            System.err.println("❌ Error al crear la tabla Alumno: " + e.getMessage());
        }
    }

	@Override
	public List<String> getAll() {

		String consulta = "SELECT * FROM Alumno";
		List<String> lista = new ArrayList<>();
		
        try (Connection conexion = DriverManager.getConnection(cadenaConexion, user, pass);
        	 PreparedStatement sentencia = conexion.prepareStatement(consulta);
             ResultSet rs = sentencia.executeQuery()) {

        	lista.add("📋 Lista de alumnos:");
            while (rs.next()) {
                String alumno = String.format("%s - %s %s - CP: %s",
                        rs.getString("dni"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("cp"));
                lista.add(alumno);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al leer: " + e.getMessage());
        }
	
        return lista;
	}

	@Override
	public Alumno getAlumnoPorDNI(String DNI) {
		// no se donde se ejecuta esto, ¿no tiene boton?
		String consulta = "SELECT * FROM Alumno where DNI = ?;";
		try(Connection conexion = DriverManager.getConnection(cadenaConexion, user, pass);
				PreparedStatement sentencia = conexion.prepareStatement(consulta); 	
				){
			
			sentencia.setString(1, DNI); 
			sentencia.execute(); 
			
		}catch(SQLException e) {
			
	}		
		
		return null;
	}

	@Override
	public boolean modificarAlumno(Alumno alumno) {

		String consulta = "UPDATE Alumno SET nombre = ?, apellidos = ?, cp = ? WHERE dni = ?";
		try(Connection conexion = DriverManager.getConnection(cadenaConexion, user, pass);
				PreparedStatement sentencia = conexion.prepareStatement(consulta); 	
				){
			
			sentencia.setString(1, alumno.getNombre()); 
			sentencia.setString(2,alumno.getApellidos());
			sentencia.setString(3,alumno.getCP());
			sentencia.setString(4,alumno.getDNI());
			sentencia.execute(); 
			
		}catch(SQLException e) {	

	}
		
		return false;
	}

	@Override
	public boolean eliminarAlumno(String DNI) {
		String consulta = "DELETE FROM Alumno WHERE dni = ?";
		try(Connection conexion = DriverManager.getConnection(cadenaConexion, user, pass);
				PreparedStatement sentencia = conexion.prepareStatement(consulta); 	
				){
			
			sentencia.setString(1, DNI); 
			sentencia.execute(); 
				
		}catch(SQLException e) {
			
	}
		return false;}

	@Override
	public boolean crear(Alumno alumno) {
		
		String consulta ="INSERT INTO Alumno (dni, nombre, apellidos, cp) VALUES (?, ?, ?, ?)";
		try(Connection conexion = DriverManager.getConnection(cadenaConexion, user, pass);
				PreparedStatement sentencia = conexion.prepareStatement(consulta); 	
				){
			
			sentencia.setString(1,alumno.getDNI());
			sentencia.setString(2,alumno.getNombre());
			sentencia.setString(3,alumno.getApellidos());
			sentencia.setString(4,alumno.getCP());
			sentencia.execute(); 
				
		}catch(SQLException e) {
			
		}
		
		return false;
	}
	
}
