// Clase con las funcionalidades para operar con la vista (html) que gestiona los AUTORES
package egg.web.libreria.controladores;

import egg.web.libreria.entidades.Autor;
import egg.web.libreria.servicios.AutorServicios;
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
@RequestMapping("/autores")               // el ingreso es a traves de la URL:   //localhost:8080/autores
public class AutorControlador {

    // ATRIBUTOS
    @Autowired                   // Inyecta (INSTANCIO) un BEAN (Objeto) de tipo AutorServicios
    private AutorServicios as;

    @GetMapping("/")
    public String autores() {
        return "autores.html";
    }

    @GetMapping("/lista")
    public String lista(ModelMap m1) {
        List<Autor> a = as.listarAutores();   // guardo todos los AUTORES en una lista
        m1.addAttribute("autores", a);        // inyecto la lista en el MODELO
        return "autoresLista.html";
    }

    @GetMapping("/formulario")
    public String formulario() {
        return "formAutor.html";
    }

    @PostMapping("/formulario")
    public String cargar(ModelMap m, @RequestParam String nombre) {            // uso ModelMap() para informar
        try {
            as.cargarAutor(nombre);
            m.put("cargado", "Autor INCORPORADO a la Base de Datos ...");      // CONFIRMACION de CARGA de AUTOR
            return "formAutor.html";
        } catch (Exception e) {
            m.put("error", "DEBRE INGRESAR un NOMBRE de Autor ...");           // ERROR en la CARGA del AUTOR
            return "formAutor.html";
        }
    }

    @GetMapping("/modificar/{id}")
    public String formularioModificar(@PathVariable String id, ModelMap m) {
        m.put("autor", as.buscarPorId(id));                          // llave "autor" asociada al Objeto(id)
        return "formAutorModif.html";
    }
    
    @PostMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, @RequestParam String nombre, ModelMap m) {
        try {
            as.modificarAutor(id, nombre);
            m.put("cargado", "Datos MODIFICADOS ..."); 
            return "redirect:/autores/lista";           
        } catch (Exception e) {
            m.put("error", "DEBRE INGRESAR un NOMBRE de Autor ...");
            return "formAutorModif.html";
        }
    }

}
