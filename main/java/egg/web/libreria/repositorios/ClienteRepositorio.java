//
package egg.web.libreria.repositorios;

import egg.web.libreria.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, String>{
    
    // QUERYs personales
    @Query("SELECT c FROM Cliente c WHERE c.apellido = :apellido")
    public Cliente buscarPorApellido();
    
    @Query("SELECT c FROM Cliente c WHERE c.documento = :documento")
    public Cliente buscarPorDocumento(@Param("documento") Long documento);
    
}
