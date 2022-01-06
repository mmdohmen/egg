// METODOS de la Clase Libro()
package egg.web.libreria.servicios;

import egg.web.libreria.entidades.Libro;
import egg.web.libreria.repositorios.AutorRepositorio;
import egg.web.libreria.repositorios.EditorialRepositorio;
import egg.web.libreria.repositorios.LibroRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service                        // anotacion q indica q esta Clase es un Servicio de SPRING
public class LibroServicios {

    // ATRIBUTOS
    @Autowired                            // anotacion que INICIALIZA la variable del servidor de aplicaciones
    private LibroRepositorio libroRepo;   // Objeto de tipo LibroRepositorio()
    @Autowired
    private AutorRepositorio autorRepo;
    @Autowired
    private EditorialRepositorio editorialRepo;

    // METODOS
    @Transactional
    public void cargarLibros(Long isbn, String titulo, Integer anio, Integer ejemplares, String idAutor,
                             String idEditorial) throws Exception {
        validar(isbn, titulo, anio, ejemplares);
        Libro l = new Libro();
        l.setIsbn(isbn);
        l.setTitulo(titulo);
        l.setAnio(anio);
        l.setEjemplares(ejemplares);
        l.setEjemplaresPrestados(0);
        l.setEjemplaresRestantes(ejemplares);
        l.setAlta(true);
        // busco el autor y la editorial con los id recibidos
        l.setAutor(autorRepo.buscarPorId(idAutor));
        l.setEditorial(editorialRepo.buscarPorId(idEditorial));
        // agrego el libro a la base de datos
        libroRepo.save(l);
    }

    @Transactional
    public List<Libro> listarLibros() {
        return libroRepo.findAll();
    }

    @Transactional
    public Libro buscarPorId(String id) {
        return libroRepo.getById(id);
    }

    @Transactional
    public void modificarLibro(String id, Long isbn, String titulo, Integer anio, Integer ejemplares,
            Integer ejemplaresPrestados, String idAutor, String idEditorial) throws Exception {
        //validar(isbn, titulo, anio, ejemplares);
        Optional<Libro> respuesta = libroRepo.findById(id);   // Objeto 'respuesta' de tipo Optional()
        //if (respuesta.isPresent()) {
        Libro l = respuesta.get();   // creo un Objeto de tipo Libro() y lo agrego a la base de datos
        // seteo los nuevos datos 
        l.setIsbn(isbn);
        l.setTitulo(titulo);
        l.setAnio(anio);
        l.setEjemplares(ejemplares);
        l.setEjemplaresPrestados(ejemplaresPrestados);
        l.setEjemplaresRestantes(ejemplares - ejemplaresPrestados);
        l.setAutor(autorRepo.buscarPorId(idAutor));
        l.setEditorial(editorialRepo.buscarPorId(idEditorial));
        // agrego el libro a la base de datos
        libroRepo.save(l);
        //} else {
        //throw new Exception("el libro no existe en la base de datos");
        //}
    }
    
    @Transactional
    public void prestarLibro(String id) {
        Optional<Libro> respuesta = libroRepo.findById(id);   // Objeto 'respuesta' de tipo Optional()
        Libro l = respuesta.get(); 
        l.setEjemplaresPrestados(l.getEjemplaresPrestados()+1);
        l.setEjemplaresRestantes(l.getEjemplares() - 1);
        libroRepo.save(l);
    }
    
    @Transactional
    public void devolverLibro(String id) {
        Optional<Libro> respuesta = libroRepo.findById(id);
        Libro l = respuesta.get();
        l.setEjemplaresPrestados(l.getEjemplaresPrestados()-1);
        l.setEjemplaresRestantes(l.getEjemplares() + 1);
        libroRepo.save(l);
    }

    
    public void eliminarLibro(String id) throws Exception {
        Optional<Libro> respuesta = libroRepo.findById(id);
        Libro l = respuesta.get();
        if (respuesta.isPresent()) {
            l.setEjemplares(0);
            l.setEjemplaresPrestados(0);
            l.setEjemplaresRestantes(0);
            libroRepo.save(l);
        } else {
            throw new Exception("el libro no existe en la base de datos");
        }
    }

    public void validar(Long isbn, String titulo, Integer anio, Integer ejemplares) throws Exception {
        if (titulo == null || titulo.isEmpty()) {
            throw new Exception("se debe ingresar un TITULO de libro");
        }
        if (anio == null) {
            throw new Exception("se debe ingresar el AÑO de publicacion del libro");
        }
        if (ejemplares == null) {
            throw new Exception("se debe ingresar la CANTIDAD de EJEMPLARES DISPONIBLES");
        }
    }
}
