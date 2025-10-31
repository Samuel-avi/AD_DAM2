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

        try (FileInputStream input = new FileInputStream("config.ini")) {
            p.load(input);
        } catch (FileNotFoundException e) {
            System.err.println("No se encontró el archivo config.ini");
            return;
        } catch (IOException e) {
            System.err.println("Error al leer config.ini");
            e.printStackTrace();
            return;
        }

        final String TABLA = "CREATE TABLE IF NOT EXISTS contadores(nombre VARCHAR(10) PRIMARY KEY, cuenta INT);";
        final String SQL_CONSULTA = "SELECT cuenta FROM contadores WHERE nombre=?;";
        final String SQL_ACTUALIZACION = "UPDATE contadores SET cuenta=? WHERE nombre=?;";
        final String SQL_INSERTAR = "INSERT OR IGNORE INTO contadores VALUES ('contador1', 0);";
        final String CLAVE_CONTADOR = "contador1";
        final String RUTA = p.getProperty("conexion");

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + RUTA)) {

            connection.setAutoCommit(false);

            try (PreparedStatement crear = connection.prepareStatement(TABLA);
                 PreparedStatement insertar = connection.prepareStatement(SQL_INSERTAR)) {

                crear.execute();
                insertar.execute();
            }

            try (PreparedStatement consulta = connection.prepareStatement(SQL_CONSULTA);
                 PreparedStatement actualizacion = connection.prepareStatement(SQL_ACTUALIZACION)) {

                int cuenta = 0;

                consulta.setString(1, CLAVE_CONTADOR);
                actualizacion.setString(2, CLAVE_CONTADOR);

                for (int i = 0; i < 1000; i++) {
                    try (ResultSet res = consulta.executeQuery()) {
                        if (res.next()) {
                            cuenta = res.getInt("cuenta") + 1;
                            actualizacion.setInt(1, cuenta);
                            actualizacion.executeUpdate();
                        } else {
                            System.out.println("Error: no se encontró el contador");
                        }
                    }
                }
                connection.commit();

                System.out.println("Valor final del contador: " + cuenta);
            } catch (SQLException e) {

                System.err.println("Error en la transacción, revirtiendo cambios...");
                connection.rollback();
                e.printStackTrace();
            }

        } catch (SQLException e) {
            System.err.println("Error al conectar o ejecutar en la base de datos:");
            e.printStackTrace();
        }
    }
}
