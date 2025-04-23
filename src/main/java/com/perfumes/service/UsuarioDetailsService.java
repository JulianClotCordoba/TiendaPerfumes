package com.perfumes.service;

import com.perfumes.domain.Rol;
import com.perfumes.domain.Usuario;
import com.perfumes.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email);

        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + email);
        }

        if (!usuario.isActivo()) {
            throw new UsernameNotFoundException("Usuario inactivo: " + email);
        }

        String[] authorities = usuario.getRoles().stream()
                .map(Rol::getNombre) 
                .toArray(String[]::new);

        return User.builder()
                .username(usuario.getEmail())
                .password(usuario.getPassword())
                .roles(
                        usuario.getRoles()
                                .stream()
                                .map(Rol::getNombre) // "ADMIN"
                                .toArray(String[]::new)
                ) // se convierte en "ROLE_ADMIN"
                .build();

    }
}
