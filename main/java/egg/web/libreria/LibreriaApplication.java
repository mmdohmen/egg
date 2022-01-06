// clase encargada de arrancar la aplicacion (guia ProgramacionWeb - pag. 21)
package egg.web.libreria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication               // anotacion que habilita la AUTOCONFIGURACION y el SCANEO de COMPONENTES
public class LibreriaApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibreriaApplication.class, args);
	}

}
