package ud5.r1.main;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;



public interface RepositorioCliente extends MongoRepository<Cliente, String> {

  public Cliente findByNombre(String nombre);
  public List<Cliente> findByApellido(String apellido);

}
