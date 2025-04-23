package com.perfumes;

import com.perfumes.domain.Rol;
import com.perfumes.domain.Usuario;
import com.perfumes.repository.RolRepository;
import com.perfumes.repository.UsuarioRepository;
import java.util.Set;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class TiendaPerfumesApplication {

    public static void main(String[] args) {
        SpringApplication.run(TiendaPerfumesApplication.class, args);
    }

    @Bean
    CommandLineRunner crearAdmin(UsuarioRepository usuarioRepo, 
                                RolRepository rolRepo, 
                                BCryptPasswordEncoder encoder) {
        return args -> {
            Rol rolAdmin = rolRepo.findByNombre("ADMIN");
            if(rolAdmin == null) {
                rolAdmin = new Rol();
                rolAdmin.setNombre("ADMIN");
                rolRepo.save(rolAdmin);
            }

            // Crear usuario admin si no existe
            if(!usuarioRepo.existsByEmail("admin@example.com")) {
                Usuario admin = new Usuario();
                admin.setNombre("Admin");
                admin.setApellido("Sistema");
                admin.setEmail("admin@example.com");
                admin.setPassword(encoder.encode("admin123"));
                admin.setActivo(true);
                admin.setRoles(Set.of(rolAdmin));
                
                usuarioRepo.save(admin);
            }
        };
    }
}
