//
package egg.web.libreria.controladores;

import egg.web.libreria.entidades.Autor;
import egg.web.libreria.entidades.Editorial;
import egg.web.libreria.entidades.Libro;
import egg.web.libreria.servicios.AutorServicios;
import egg.web.libreria.servicios.EditorialServicios;
import egg.web.libreria.servicios.LibroServicios;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/libros")        // localhost:8080/libros
public class LibroControlador {
    
    // ATRIBUTOS
    @Autowired                   // Inyecta (INSTANCIO) un BEAN (Objeto) de tipo LibroServicios()
    private LibroServicios ls;
    @Autowired
    private AutorServicios as;
    @Autowired
    private EditorialServicios es;
    
    @GetMapping("/")
    public String libros() {
        return "libros.html";
    }
    
    @GetMapping("/lista")        // localhost:8080/libros/lista
    public String lista(ModelMap m) {
        List<Libro> l = ls.listarLibros();   // guardo todas los LIBROS en una lista
        m.put("libros", l);                  // inyecto la lista en el MODELO bajo la clave 'libros'
        return "librosLista.html";
    }
    
    @GetMapping("/formulario")
    public String formulario(ModelMap ma, ModelMap me) {
        List<Autor> a = as.listarAutores();
        List<Editorial> e = es.listarEditoriales();
        ma.put("autores", a);
        me.put("editoriales", e);
        return "formLibro.html";
    }
    
    @PostMapping("/formulario")
    public String cargar(ModelMap m, @RequestParam Long isbn, @RequestParam String titulo, 
            @RequestParam Integer anio, @RequestParam Integer ejemplares, 
            @RequestParam String idAutor, 
            @RequestParam String idEditorial) {
        try {
            ls.cargarLibros(isbn, titulo, anio, ejemplares, idAutor, idEditorial);
            m.put("cargado", "LIBRO incorporado a la Base de Datos ...");
            return "formLibro.html";
        } catch (Exception e) {
            m.put("error", "DEBE INGRESAR un TODOS los DATOS ...");
            return "formLibro.html";
        }
    }
    
    @GetMapping("/modificar/{id}")
    public String formularioModificar(@PathVariable String id, ModelMap m, ModelMap ma, ModelMap me) {
        List<Autor> a = as.listarAutores();
        List<Editorial> e = es.listarEditoriales();
        m.put("libro", ls.buscarPorId(id));
        ma.put("autores", a);
        me.put("editoriales", e);
        return "formLibroModif.html";
    }
    
    @PostMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, @RequestParam Long isbn, 
                            @RequestParam String titulo, @RequestParam Integer anio, 
                            @RequestParam Integer ejemplares, @RequestParam Integer ejemplaresPrestados, 
                            @RequestParam String idAutor, @RequestParam String idEditorial, ModelMap m) {
        try {
            ls.modificarLibro(id, isbn, titulo, anio, ejemplares, ejemplaresPrestados, idAutor, idEditorial);
            return "redirect:/libros/lista";
        } catch (Exception e) {
            m.put("error", "DEBE INGRESAR todos los DATOS ...");
            return "formAutorModif.html";
        }
    }
    
}
