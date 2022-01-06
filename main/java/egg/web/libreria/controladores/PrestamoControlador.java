//
package egg.web.libreria.controladores;

import egg.web.libreria.entidades.Cliente;
import egg.web.libreria.entidades.Prestamo;
import egg.web.libreria.servicios.ClienteServicios;
import egg.web.libreria.servicios.LibroServicios;
import egg.web.libreria.servicios.PrestamoServicios;
//import java.sql.Date;
import java.util.Date;
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
@RequestMapping("/prestamos")        // ingreso es a traves de la URL:   localhost:8080/prestamos
public class PrestamoControlador {
    
    // ATRIBUTOS
    @Autowired
    private PrestamoServicios ps;
    @Autowired
    private LibroServicios ls;
    @Autowired
    private ClienteServicios cs;
    
    @GetMapping("/")               // ingreso es a traves de la URL:   localhost:8080/prestamos
    public String prestamos() {
        return "prestamos.html";
    }
    
    @GetMapping("/lista")               // ingreso es a traves de la URL:   localhost:8080/prestamos/lista
    public String lista(ModelMap m) {
        List<Prestamo> p = ps.listarPrestamos();
        m.put("prestamos", p);
        return "prestamosLista.html";
    }
    
    @GetMapping("/formulario/{id}")   // ingreso es a traves de la URL:   localhost:8080/prestamos/formulario
    public String formulario(@PathVariable String id, ModelMap m, ModelMap mc) {
        m.put("libro", ls.buscarPorId(id));
        List<Cliente> c = cs.listarClientes();
        mc.put("clientes", c);
        return "formPrestamo.html";
    }
    
    @PostMapping("/formulario/{id}")
    public String cargar(@PathVariable String id, @RequestParam Date fechaPrestamo, @RequestParam String titulo, 
                         @RequestParam Long documento, ModelMap m) {
        try {
            ps.cargarPrestamo(fechaPrestamo, titulo, documento);
            m.put("cargado", "PRESTAMO incorporado a la Base de Datos ...");
            return "redirect:/libros/lista";
        } catch (Exception e) {
            m.put("error", "DEBE INGRESAR todos los DATOS ...");
            return "formPrestamo.html";
        }
    }
    
    @GetMapping("/modificar/{id}")
    public String formularioModificar(@PathVariable String id, ModelMap m) {
        m.put("prestamo", ps.buscarPorId(id));
        return "formPrestamoModif.html";
    }
    
    @PostMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, @RequestParam Date fechaDevolucion, 
                            @RequestParam String titulo, ModelMap m) {
        try {
            ps.modificarPrestamo(id, fechaDevolucion, titulo);
            return "redirect:/prestamos/lista";
        } catch (Exception e) {
            m.put("error", "DEBE INGRESAR la FECHA de DEVOLUCION ...");
            return "formPrestamoModif.html";
        }
    }
    
    
}
