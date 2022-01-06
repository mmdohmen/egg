// CLIENTES
package egg.web.libreria.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Cliente {
    
    // ATRIBUTOS
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private Long documento;
    private String nombre;
    private String apellido;
    private String telefono;
    private Boolean alta;
    
    // CONSTRUCTOR
    public Cliente() {}
    
    // GETTER & SETTER
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public Long getDocumento() {
        return documento;
    }
    public void setDocumento(Long documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Boolean getAlta() {
        return alta;
    }
    public void setAlta(Boolean alta) {
        this.alta = alta;
    }
    
    
    
}
