// Clase Controlador de Apache Tomcat que habilita un SERVIDOR LOCAL
// en localhost:8080/
package egg.web.libreria.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller                        // anotacion q determina q la clase es un CONTROLADOR                                  
@RequestMapping("/")               // el ingreso es a traves de la URL:   //localhost:8080
public class PortalControlador {
    
    @GetMapping("/")               // asigna una Solicitud HTTP GET en //localhost:8080/  
                                   // usado para consultar 'get' informacion
    public String index() {
        return "index.html";       // retorno el template que abre el controlador:   index.html
    }
        
}
