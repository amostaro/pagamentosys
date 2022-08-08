package com.totalshake.pagamentosys.enums;

import lombok.Getter;
import org.springframework.util.StringUtils;

import java.util.Arrays;

public enum EnumStatus {

    CRIADO ("CRIADO", "CRIADO"),
    CANCELADO ("CANCELADO", "CANCELADO"),
    CONFIRMADO ("CONFIRMADO", "CONFIRMADO");

    @Getter
    private String codigo;

    @Getter
    private String descricao;

    private EnumStatus(String codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

}
