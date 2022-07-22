package dev.delivery.delivery.controller;

import dev.delivery.delivery.model.Cliente;
import dev.delivery.delivery.service.CrudCliente;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    private final CrudCliente crudCliente;

    public ClienteController(CrudCliente crudCliente) {
        this.crudCliente = crudCliente;
    }

    @GetMapping
    ResponseEntity<Iterable<Cliente>> all() {
        Iterable<Cliente> clientes = crudCliente.findAll();
        if (!clientes.iterator().hasNext()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<Cliente> save(@RequestBody Cliente cliente) {
        Cliente clienteSave = crudCliente.save(cliente);
        return new ResponseEntity<>(clienteSave, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    ResponseEntity<Cliente> findById(@PathVariable Integer id) {
        Cliente cliente = crudCliente.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente com o id " + id + " não encontrado "));
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    ResponseEntity<Cliente> update(@RequestBody Cliente novoCliente, @PathVariable Integer id) {
        Cliente clienteUpdate = crudCliente.findById(id)
                .map(cliente -> {
                    cliente.setNome(novoCliente.getNome() == null ? cliente.getNome() : novoCliente.getNome());
                    cliente.setCpf(novoCliente.getCpf() == null ? cliente.getCpf() : novoCliente.getCpf());
                    cliente.setEmail(novoCliente.getEmail() == null ? cliente.getEmail() : novoCliente.getEmail());
                    cliente.setTelefone(novoCliente.getTelefone() == null ? cliente.getTelefone() : novoCliente.getTelefone());
                    return crudCliente.update(cliente);
                })
                .orElseGet(() -> {
                    novoCliente.setId(id);
                    return crudCliente.save(novoCliente);
                });
        return new ResponseEntity<>(clienteUpdate, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    ResponseEntity<HttpStatus> deleteCliente(@PathVariable Integer id) {
        if (!crudCliente.existsById(id)) {
            throw new RuntimeException("Cliente com o id " + id + " não encontrado");
        }
        crudCliente.destroy(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
