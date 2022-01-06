//
package egg.web.libreria.controladores;

import egg.web.libreria.entidades.Editorial;
import egg.web.libreria.servicios.EditorialServicios;
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
@RequestMapping("/editoriales")       // URL de ingreso a este controlador => localhost:8080/editoriales
public class EditorialControlador {

    // ATRIBUTOS
    @Autowired                       // Inyecta (INSTANCIO) un BEAN (Objeto) de tipo EditorialServicios()
    private EditorialServicios es;

    @GetMapping("/")
    public String editoriales() {
        return "editoriales.html";
    }

    @GetMapping("/lista")
    public String lista(ModelMap m) {
        List<Editorial> e = es.listarEditoriales();   // guardo todas las EDITORIALES en una lista
        m.put("editoriales", e);                      // inyecto la lista en el MODELO bajo la clave 'editoriales'
        return "editorialesLista.html";
    }

    @GetMapping("/formulario")
    public String formulario() {
        return "formEditorial.html";
    }

    @PostMapping("/formulario")
    public String cargar(@RequestParam String nombre, ModelMap m) {
        try {
            es.cargarEditorial(nombre);
            m.put("cargado", "EDITORIAL incorporada a la Base de Datos ...");
            return "formEditorial.html";
        } catch (Exception e) {
            m.put("error", "DEBE INGRESAR un nombre de EDITORIAL ...");
            return "formEditorial.html";
        }
    }
    
    @GetMapping("/modificar/{id}")
    public String formularioModificar(@PathVariable String id, ModelMap m) {
        m.put("editorial", es.buscarPorId(id));
        return "formEditorialModif.html";
    }
    
    @PostMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, @RequestParam String nombre, ModelMap m) {
        try {
            es.modificarEditorial(id, nombre);
            return "redirect:/editoriales/lista";
        } catch (Exception e) {
            m.put("error", "DEBE INGRESAR un NOMBRE de EDITORIAL ...");
            return "formAutorModif.html";
        }
    }

}
