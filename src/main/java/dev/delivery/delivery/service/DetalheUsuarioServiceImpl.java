package dev.delivery.delivery.service;

import dev.delivery.delivery.data.DetalheUsuarioData;
import dev.delivery.delivery.model.Usuario;
import dev.delivery.delivery.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DetalheUsuarioServiceImpl implements UserDetailsService {
    private final UsuarioRepository repository;

    public DetalheUsuarioServiceImpl(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = repository.findByLogin(username);
        if (!usuario.isPresent()) {
            throw new UsernameNotFoundException("Usuário " + username + " não encontrado");
        }
        return new DetalheUsuarioData(usuario);
    }
}
