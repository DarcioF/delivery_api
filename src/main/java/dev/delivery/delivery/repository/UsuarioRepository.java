package dev.delivery.delivery.repository;

import dev.delivery.delivery.model.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {
    public Optional<Usuario> findByLogin(String login);
}
