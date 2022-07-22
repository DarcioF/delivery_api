package dev.delivery.delivery.service;

import dev.delivery.delivery.model.Pedido;
import dev.delivery.delivery.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CrudPedido {


    private final PedidoRepository repository;

    public CrudPedido(PedidoRepository repository) {
        this.repository = repository;
    }

    public Pedido save(Pedido pedido) {
        return repository.save(pedido);
    }

    public Optional<Pedido> findById(Integer id) {
        return this.repository.findById(id);
    }

    public Pedido update(Pedido pedido) {
        return repository.save(pedido);
    }

    public Iterable<Pedido> findAll() {
        return this.repository.findAll();
    }

    public boolean existsById(Integer id) {
        return repository.existsById(id);
    }

    public void destroy(Integer id) {
        repository.deleteById(id);
    }
}
