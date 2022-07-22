package dev.delivery.delivery.controller;

import dev.delivery.delivery.model.Entrega;
import dev.delivery.delivery.service.CrudEntrega;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/entrega")
public class EntregaController {

    private final CrudEntrega crudEntrega;

    public EntregaController(CrudEntrega crudEntrega) {
        this.crudEntrega = crudEntrega;
    }

    @GetMapping
    ResponseEntity<Iterable<Entrega>> all() {
        Iterable<Entrega> entregas = crudEntrega.findAll();
        if (!entregas.iterator().hasNext()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(entregas, HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<Entrega> save(@RequestBody Entrega entrega) {
        Entrega entregaSave = crudEntrega.save(entrega);
        return new ResponseEntity<>(entregaSave, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    ResponseEntity<Entrega> findById(@PathVariable Integer id) {
        Entrega entrega = crudEntrega.findById(id)
                .orElseThrow(() -> new RuntimeException("Entrega com o id " + id + " não encontrado "));
        return new ResponseEntity<>(entrega, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    ResponseEntity<Entrega> update(@RequestBody Entrega novaEntrega, @PathVariable Integer id) {
        Entrega entregaUpadte = crudEntrega.findById(id)
                .map(entrega -> {
                    entrega.setBairro(novaEntrega.getBairro() == null ? entrega.getBairro() : novaEntrega.getBairro());
                    entrega.setCep(novaEntrega.getCep() == null ? entrega.getCep() : novaEntrega.getCep());
                    entrega.setCidade(novaEntrega.getCidade() == null ? entrega.getCidade() : novaEntrega.getCidade());
                    entrega.setNumero(novaEntrega.getNumero() == null ? entrega.getNumero() : novaEntrega.getNumero());
                    entrega.setRua(novaEntrega.getRua() == null ? entrega.getRua() : novaEntrega.getRua());
                    entrega.setComplemento(novaEntrega.getComplemento() == null ? entrega.getComplemento() : novaEntrega.getComplemento());
                    entrega.setTaxa_entrega(novaEntrega.getTaxa_entrega() == null ? entrega.getTaxa_entrega() : novaEntrega.getTaxa_entrega());
                    return crudEntrega.update(entrega);
                })
                .orElseGet(() -> {
                    novaEntrega.setId(id);
                    return crudEntrega.save(novaEntrega);
                });
        return new ResponseEntity<>(entregaUpadte, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    ResponseEntity<HttpStatus> deleteEntrega(@PathVariable Integer id) {
        if (!crudEntrega.existsById(id)) {
            throw new RuntimeException("Entrega com o id " + id + " não encontrado");
        }
        crudEntrega.destroy(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
