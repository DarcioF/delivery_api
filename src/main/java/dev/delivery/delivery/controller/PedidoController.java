package dev.delivery.delivery.controller;

import dev.delivery.delivery.model.Pedido;
import dev.delivery.delivery.service.CrudPedido;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pedido")
public class PedidoController {
    private final CrudPedido crudPedido;

    public PedidoController(CrudPedido crudPedido) {
        this.crudPedido = crudPedido;
    }

    @GetMapping
    ResponseEntity<Iterable<Pedido>> all() {
        Iterable<Pedido> pedidos = crudPedido.findAll();
        if (!pedidos.iterator().hasNext()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(pedidos, HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<Pedido> save(@RequestBody Pedido pedido) {
        Pedido pedidoSave = crudPedido.save(pedido);
        return new ResponseEntity<>(pedidoSave, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    ResponseEntity<Pedido> findById(@PathVariable Integer id) {
        Pedido pedido = crudPedido.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido com o id " + id + " não encontrado "));
        return new ResponseEntity<>(pedido, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    ResponseEntity<Pedido> update(@RequestBody Pedido novoPedido, @PathVariable Integer id) {
        Pedido pedidoUpadte = crudPedido.findById(id)
                .map(pedido -> {
                    pedido.setCliente(novoPedido.getCliente() == null ? pedido.getCliente() : novoPedido.getCliente());
                    pedido.setDescricao(novoPedido.getDescricao() == null ? pedido.getDescricao() : novoPedido.getDescricao());
                    pedido.setEntregue(novoPedido.getEntregue() == null ? pedido.getEntregue() : novoPedido.getEntregue());
                    pedido.setEntrega(novoPedido.getEntrega() == null ? pedido.getEntrega() : novoPedido.getEntrega());
                    pedido.setData_pedido(novoPedido.getData_pedido() == null ? pedido.getData_pedido() : novoPedido.getData_pedido());
                    pedido.setValor_total(novoPedido.getValor_total() == null ? pedido.getValor_total() : novoPedido.getValor_total());
                    return crudPedido.update(pedido);
                })
                .orElseGet(() -> {
                    novoPedido.setId(id);
                    return crudPedido.save(novoPedido);
                });
        return new ResponseEntity<>(pedidoUpadte, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    ResponseEntity<HttpStatus> deleteEntrega(@PathVariable Integer id) {
        if (!crudPedido.existsById(id)) {
            throw new RuntimeException("Pedido com o id " + id + " não encontrado");
        }
        crudPedido.destroy(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
