// METODOS de la Clase Editorial()
package egg.web.libreria.servicios;

import egg.web.libreria.entidades.Editorial;
import egg.web.libreria.repositorios.EditorialRepositorio;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service                            // anotacion q indica q esta Clase es un Servicio de SPRING
public class EditorialServicios {
    
    // ATRIBUTOS
    @Autowired                                    // anotacion que INICIALIZA la variable del servidor de aplicaciones
    private EditorialRepositorio editorialRepo;
    
    // METODOS
    @Transactional   // Si salta una excepción, el método vuelve atrás con la transacción 
                     // y no guarda cambios en la base.
    public void cargarEditorial(String nombre) throws Exception {
        // validaciones antes de persistir
        if (nombre == null || nombre.isEmpty()) {
            throw new Exception("debe ingresar un Nombre de Editorial ...");
        }
        Editorial e = new Editorial();
        e.setNombre(nombre);
        e.setAlta(true);
        editorialRepo.save(e);
    }
    
    @Transactional
    public void modificarEditorial(String id, String nombre) throws Exception {
        // validaciones antes de persistir
        if (nombre == null || nombre.isEmpty()) {
            throw new Exception("debe ingresar un Nombre de Editorial ...");
        }
        Optional<Editorial> respuesta = editorialRepo.findById(id);   // Objeto 'respuesta' de tipo Optional()
        Editorial e = respuesta.get();   // creao un Objeto de tipo Editorial() y lo agrego a la base de datos
        e.setNombre(nombre);
        e.setAlta(true);
        editorialRepo.save(e); 
    }
    
    @Transactional
    public List<Editorial> listarEditoriales() {
        return editorialRepo.findAll();
    }
    
    @Transactional
    public Editorial buscarPorId(String id) {
        return editorialRepo.getById(id);
    }
    
    
}
