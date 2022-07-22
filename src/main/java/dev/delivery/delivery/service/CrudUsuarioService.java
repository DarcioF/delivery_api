package dev.delivery.delivery.service;

import dev.delivery.delivery.model.Usuario;
import dev.delivery.delivery.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class CrudUsuarioService {
    private final UsuarioRepository repository;

    public CrudUsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public void save(Usuario usuario) {
        repository.save(usuario);
    }

    public void update(Usuario usuario) {
        repository.save(usuario);
    }

    public Iterable<Usuario> findAll() {
        return this.repository.findAll();
    }

    public void destroy(Usuario usuario) {
        repository.deleteById(usuario.getId());
    }
}
