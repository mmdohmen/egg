//
package egg.web.libreria.controladores;

import egg.web.libreria.entidades.Cliente;
import egg.web.libreria.servicios.ClienteServicios;
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
@RequestMapping("/clientes")
public class ClienteControlador {
    
    // ATRIBUTOS
    @Autowired
    private ClienteServicios cs;
    
    @GetMapping("/")
    public String clientes() {
        return "clientes.html";
    }
    
    @GetMapping("/lista")
    public String lista(ModelMap m) {
        List<Cliente> c = cs.listarClientes();
        m.put("clientes", c);
        return "clientesLista.html";
    }
    
    @GetMapping("/formulario")
    public String formulario() {
        return "formCliente.html";
    }
    
    @PostMapping("/formulario")
    public String cargar(@RequestParam Long documento, @RequestParam String nombre, @RequestParam String apellido, 
                         @RequestParam String telefono, ModelMap m) {
        try {
            cs.cargarCliente(documento, nombre, apellido, telefono);
            m.put("cargado", "CLIENTE incorporado a la Base de Datos ...");
            return "formCliente.html";
        } catch (Exception e) {
            m.put("error", "DEBE INGRESAR un TODOS los DATOS ...");
            return "formCliente.html";
        }
    }
    
    @GetMapping("/modificar/{id}")
    public String formularioModificar(ModelMap m, @PathVariable String id) {
        m.put("cliente", cs.buscarPorId(id));
        return "formClienteModif.html";
    }
    
    @PostMapping("/modificar/{id}")
    public String modificar(ModelMap m, @PathVariable String id, @RequestParam Long documento,
                            @RequestParam String nombre, @RequestParam String apellido,
                            @RequestParam String telefono, @RequestParam Boolean alta) {
        try {
            cs.modificarCliente(id, documento, nombre, apellido, telefono, alta);
            return "redirect:/clientes/lista";
        } catch (Exception e) {
            m.put("error", "DEBE INGRESAR todos los DATOS ...");
            return "";
        }
    }
    
}
