// REPOSITORIO de Libros
package egg.web.libreria.repositorios;

import egg.web.libreria.entidades.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepositorio extends JpaRepository<Libro,String> {
    
    @Query("SELECT l FROM Libro l WHERE l.titulo = :titulo")
    public Libro buscarPorTitulo(@Param("titulo") String titulo);
    
//    @Query("DELETE l FROM Libro l WHERE l.id = :id")
//    public Libro eliminarLibro(@Param("id") String id);
    
}
