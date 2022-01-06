// METODOS de la Clase Autor()
package egg.web.libreria.servicios;

import egg.web.libreria.entidades.Autor;
import egg.web.libreria.repositorios.AutorRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service                                  // anotacion q indica q esta Clase es un SERVICIO de SPRING
public class AutorServicios {

    // ATRIBUTOS
    @Autowired                            // anotacion que INSTANCIA un Objeto de la Clase Repositorio
    private AutorRepositorio autorRepo;
    
    // METODOS
    @Transactional
    public void cargarAutor(String nombre) throws Exception {
        // validaciones antes de persistir
        if (nombre == null || nombre.isEmpty()) {
            throw new Exception("debe ingresar un Nombre de Autor ...");
        }
        
        Autor a = new Autor();   // creao un Objeto de tipo Autor() y lo agrego a la base de datos
        a.setNombre(nombre);
        a.setAlta(true);
        autorRepo.save(a);       // guarda los datos de 'a' en la base de datos
    }

    @Transactional
    public void modificarAutor(String id, String nombre) throws Exception {
        // validaciones
//        if (id == null || id.isEmpty()) {
//            throw new Exception("debe ingresar un Nombre de Autor ...");
//        }
        if (nombre == null || nombre.isEmpty()) {
            throw new Exception("debe ingresar un Nombre de Autor ...");
        }
        // verifico que exista el id en la base de datos
        //Optional<Autor> respuesta = autorRepo.findById(id);   // Objeto 'respuesta' de tipo Optional()
//        if (respuesta.isPresent()) {
//            Autor a = respuesta.get();
//            a.setNombre(nombre);         // modifica el nombre
//            autorRepo.save(a);           // guarda los datos de 'a' en la base de datos
//        } else {
//            throw new Exception("el Id ingresado NO se encuentra en la base de datos ...");
//        }
        //Autor a = respuesta.get();   // creao un Objeto de tipo Autor() y lo agrego a la base de datos
        Autor a = autorRepo.buscarPorId(id);
        a.setNombre(nombre);
        //a.setAlta(true);
        autorRepo.save(a);   
    }
    
    @Transactional(readOnly = true)        // readOnly agiliza la consulta
    public List<Autor> listarAutores() {
        return autorRepo.findAll();
    }
    
    @Transactional
    public Autor buscarPorId(String id) {
        return autorRepo.getOne(id);
    }

}
