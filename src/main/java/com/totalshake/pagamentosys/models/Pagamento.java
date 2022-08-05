package com.totalshake.pagamentosys.models;

import com.totalshake.pagamentosys.enums.EnumFormaPagamento;
import com.totalshake.pagamentosys.enums.EnumStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pagamentos")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Positive
    @Column(name = "valor")
    private BigDecimal valor;

    @NotBlank
    @Length(max = 100)
    @Column(name = "nome")
    private String nome;

    @NotBlank
    @Length(max = 100)
    @Column(name = "numero")
    private String numero;

    @Column(name = "expiracao")
    private String expiracao;

    @NotBlank
    @Length(min = 3, max = 3)
    @Column(name = "codigo")
    private String codigo;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private EnumStatus status;

    @Column(name = "pedido_id")
    private Long pedidoId;

    @Column(name = "forma_pagamento")
    private EnumFormaPagamento formaPagamento;

}
