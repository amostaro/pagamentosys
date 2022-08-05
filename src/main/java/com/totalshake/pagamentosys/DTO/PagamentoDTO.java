package com.totalshake.pagamentosys.DTO;

import com.totalshake.pagamentosys.enums.EnumFormaPagamento;
import com.totalshake.pagamentosys.enums.EnumStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PagamentoDTO {

    private Long id;

    @NotNull
    @Positive
    private BigDecimal valor;

    @NotBlank
    @Length(max = 100)
    private String nome;

    @NotBlank
    @Length(max = 100)
    private String numero;

    private String expiracao;

    @NotBlank
    @Length(min = 3, max = 3)
    private String codigo;

    @NotNull
    @Enumerated(EnumType.STRING)
    private EnumStatus status;

    private Long pedidoId;

    private EnumFormaPagamento formaPagamento;

}
