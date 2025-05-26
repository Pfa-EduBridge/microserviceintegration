package tn.esprit.module;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {
    // Compter les modules avec stater = true
    @Query("SELECT COUNT(m) FROM Module m WHERE m.state = true")
    Long countByStateTrue();
}
