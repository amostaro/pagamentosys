package com.totalshake.pagamentosys.enums;

import lombok.Getter;
import org.springframework.util.StringUtils;

import java.util.Arrays;

public enum EnumFormaPagamento {

    DINHEIRO ("DINHEIRO", "DINHEIRO"),
    PIX ("PIX", "PIX"),
    CARTAO_DEBITO ("CARTAO_DEBITO", "CARTAO_DEBITO"),
    CARTAO_CREDITO ("CARTAO_CREDITO", "CARTAO_CREDITO");

    @Getter
    private String codigo;

    @Getter
    private String descricao;

    private EnumFormaPagamento(String codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public static EnumFormaPagamento valueOfCodigo(String codigo) {
        if (StringUtils.isEmpty(codigo)) {
            return null;
        }
        return Arrays.stream(EnumFormaPagamento.values())
                .filter(element -> element.getCodigo().equalsIgnoreCase(codigo))
                .findAny()
                .orElse(null);
    }
}
