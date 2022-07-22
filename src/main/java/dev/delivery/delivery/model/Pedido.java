package dev.delivery.delivery.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "pedido")
@SequenceGenerator(name = "GEN_ID_PEDIDO", sequenceName = "GEN_ID_PEDIDO")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "GEN_ID_PEDIDO")
    private Integer id;
    private String descricao;
    @Column(precision = 12, scale = 2)
    private BigDecimal valor_total;
    private LocalDate data_pedido;
    @Column(name = "is_entregue")
    private Boolean isEntregue;
    @JsonIgnoreProperties({"pedidos"})
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;
    @OneToOne
    @JoinColumn(name = "entrega_id", nullable = false)
    private Entrega entrega;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor_total() {
        return valor_total;
    }

    public void setValor_total(BigDecimal valor_total) {
        this.valor_total = valor_total;
    }

    public LocalDate getData_pedido() {
        return data_pedido;
    }

    public void setData_pedido(LocalDate data_pedido) {
        this.data_pedido = data_pedido;
    }

    public Boolean getEntregue() {
        return isEntregue;
    }

    public void setEntregue(Boolean entregue) {
        isEntregue = entregue;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Entrega getEntrega() {
        return entrega;
    }

    public void setEntrega(Entrega entrega) {
        this.entrega = entrega;
    }
}
