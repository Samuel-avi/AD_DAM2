package ud5.r1.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;


@SpringBootApplication
public class MongodbApplication implements CommandLineRunner {

  @Autowired
  private RepositorioCliente repositorio;
  
  @Autowired
  private MongoTemplate mongoTemplate;

  public static void main(String[] args) {
    SpringApplication.run(MongodbApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
	  
		  
		//Para saber en que db estoy
		System.out.println("Colecciones en la BD:");
		  
		System.out.println("Base de datos usada por Spring: " + mongoTemplate.getDb().getName());
	
		System.out.println("Colecciones que ve Spring:");
		mongoTemplate.getCollectionNames().forEach(System.out::println);
	  

	    // crear clientes
	    repositorio.save(new Cliente("Alicia", "Sanz"));
	    repositorio.save(new Cliente("Carlos", "Sanz"));


	    // buscar a todos los clientes
	    System.out.println("Clientes encontrados con findAll():");
	    System.out.println("-------------------------------");
	    for (Cliente cliente : repositorio.findAll()) {
	      System.out.println(cliente);
	    }
	    System.out.println();

	    // buscar un solo cliente
	    System.out.println("Clientes encontrados con findByNombre('Alicia'):");
	    System.out.println("--------------------------------");
	    System.out.println(repositorio.findByNombre("Alicia"));
	    System.out.println();
	    System.out.println("Clientes encontrados con  findByApellido('Sanz'):");
	    System.out.println("--------------------------------");
	    for (Cliente cliente : repositorio.findByApellido("Sanz")) {
	      System.out.println(cliente);
	    }
	    
	    /*He creado desde el terminal un cliente de nombre samuel
	     	db.cliente.insertOne({
	  			nombre: 'Samuel',
	  			apellido: 'Avila'
			})
			cada vez que se ejecute el programa hay que volver a crearlo
	    */
	    System.out.println();
	    System.out.println("Clientes creado desde el terminal:");
	    System.out.println("--------------------------------");
	    System.out.println(repositorio.findByNombre("Samuel")); //me devuelve null
    
    
	    repositorio.deleteAll(); // si no pongo esto da error al volver a ejecutarse por que los objetos ya estan creados.
    

  }

}