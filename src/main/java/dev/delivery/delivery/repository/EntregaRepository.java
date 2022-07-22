package dev.delivery.delivery.repository;

import dev.delivery.delivery.model.Entrega;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntregaRepository extends CrudRepository<Entrega, Integer> {
}
