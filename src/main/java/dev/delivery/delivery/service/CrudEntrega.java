package dev.delivery.delivery.service;

import dev.delivery.delivery.model.Entrega;
import dev.delivery.delivery.repository.EntregaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CrudEntrega {
    private final EntregaRepository repository;

    public CrudEntrega(EntregaRepository repository) {
        this.repository = repository;
    }

    public Entrega save(Entrega entrega) {
        return repository.save(entrega);
    }

    public Entrega update(Entrega entrega) {
        return repository.save(entrega);
    }

    public Iterable<Entrega> findAll() {
        return this.repository.findAll();
    }

    public void destroy(Integer id) {
        repository.deleteById(id);
    }

    public boolean existsById(Integer id) {
        return repository.existsById(id);
    }

    public Optional<Entrega> findById(Integer id) {
        return this.repository.findById(id);
    }
}
