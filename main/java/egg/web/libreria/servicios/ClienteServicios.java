// METODOS de la Clase Cliente()
package egg.web.libreria.servicios;

import egg.web.libreria.entidades.Cliente;
import egg.web.libreria.entidades.Libro;
import egg.web.libreria.repositorios.ClienteRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteServicios {
    
    // ATRIBUTOS
    @Autowired
    private ClienteRepositorio clienteRepo;
    
    // METODOS
    @Transactional
    public void cargarCliente(Long documento, String nombre, String apellido, String telefono) throws Exception {
        validar(documento, nombre, apellido, telefono);
        Cliente c = new Cliente();
        c.setDocumento(documento);
        c.setNombre(nombre);
        c.setApellido(apellido);
        c.setTelefono(telefono);
        c.setAlta(true);
        clienteRepo.save(c);
    }
    
    @Transactional
    public List<Cliente> listarClientes() {
        return clienteRepo.findAll();
    }
    
    @Transactional
    public Cliente buscarPorId(String id){
        return clienteRepo.getById(id);
    }
    
    @Transactional
    public void modificarCliente(String id, Long documento, String nombre, String apellido, String telefono, 
                                 Boolean alta) throws Exception {
        validar(documento, nombre, apellido, telefono);
        Optional<Cliente> respuesta = clienteRepo.findById(id);
        Cliente c = respuesta.get();
        c.setDocumento(documento);
        c.setNombre(nombre);
        c.setApellido(apellido);
        c.setTelefono(telefono);
        c.setAlta(alta);
        clienteRepo.save(c);
    }
    
    public void validar(Long documento, String nombre, String apellido, String telefono) throws Exception {
        if (documento == null) {
            throw new Exception("debe ingresar el DOCUMENTO del Cliente");
        }
        if (nombre == null || nombre.isEmpty()) {
            throw new Exception("debe ingresar el NOMBRE del Cliente");
        }
        if (apellido == null || apellido.isEmpty()) {
            throw new Exception("debe ingresar el APELLIDO del Cliente");
        }
        if (telefono == null || telefono.isEmpty()) {
            throw new Exception("debe ingresar el TELEFONO del Cliente");
        }
    }
    
}
