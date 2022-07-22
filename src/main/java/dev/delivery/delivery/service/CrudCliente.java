package dev.delivery.delivery.service;

import dev.delivery.delivery.model.Cliente;
import dev.delivery.delivery.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CrudCliente {

    private final ClienteRepository repository;

    public CrudCliente(ClienteRepository repository) {
        this.repository = repository;
    }

    public Cliente save(Cliente cliente) {
        return repository.save(cliente);
    }

    public Cliente update(Cliente cliente) {
        return repository.save(cliente);
    }

    public Iterable<Cliente> findAll() {
        return this.repository.findAll();
    }

    public Optional<Cliente> findById(Integer id) {
        return this.repository.findById(id);
    }

    public boolean existsById(Integer id) {
        return repository.existsById(id);
    }

    public void destroy(Integer id) {
        repository.deleteById(id);
    }
}
