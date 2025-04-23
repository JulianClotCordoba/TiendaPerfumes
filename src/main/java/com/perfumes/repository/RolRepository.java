package com.perfumes.repository;

import com.perfumes.domain.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Rol, Long> {

    Rol findByNombre(String nombre);
}
