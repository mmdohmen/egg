// METODOS de la Clase Prestamo()
package egg.web.libreria.servicios;

import egg.web.libreria.entidades.Libro;
import egg.web.libreria.entidades.Prestamo;
import egg.web.libreria.repositorios.ClienteRepositorio;
import egg.web.libreria.repositorios.LibroRepositorio;
import egg.web.libreria.repositorios.PrestamoRepositorio;
//import java.sql.Date;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PrestamoServicios {
    
    // ATRIBUTOS
    @Autowired
    PrestamoRepositorio prestamoRepo;
    @Autowired
    LibroRepositorio libroRepo;
    @Autowired
    ClienteRepositorio clienteRepo;
    @Autowired
    LibroServicios ls;
    
    // METODOS
    @Transactional
    public void cargarPrestamo(Date fechaPrestamo, String titulo, Long documento) throws Exception {
        validar(fechaPrestamo);
        Prestamo p = new Prestamo();
        p.setFechaPrestamo(fechaPrestamo);
        p.setAlta(true);
        Libro l = libroRepo.buscarPorTitulo(titulo);
        p.setLibro(l);
        ls.prestarLibro(l.getId());
        
        p.setCliente(clienteRepo.buscarPorDocumento(documento));
        
        prestamoRepo.save(p);
    }
    
    @Transactional
    public List<Prestamo> listarPrestamos() {
        return prestamoRepo.findAll();
    }
    
    @Transactional
    public Prestamo buscarPorId(String id) {
        return prestamoRepo.getById(id);
    }
    
    @Transactional
    public void modificarPrestamo(String id, Date fechaDevolucion, String titulo) {
        Optional<Prestamo> respuesta = prestamoRepo.findById(id);
        Prestamo p = respuesta.get();
        p.setFechaDevolucion(fechaDevolucion);
        p.setAlta(false);
        
        Libro l = libroRepo.buscarPorTitulo(titulo);
        ls.devolverLibro(l.getId());
        
        prestamoRepo.save(p);
    }
    
    public void validar(Date fechaPrestamo) throws Exception {
        if (fechaPrestamo == null) {
            throw new Exception("debe ingresar la FECHA del prestamo");
        }
    }
    
}
