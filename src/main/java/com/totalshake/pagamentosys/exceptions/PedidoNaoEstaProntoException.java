package com.totalshake.pagamentosys.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PedidoNaoEstaProntoException extends RuntimeException {
    public PedidoNaoEstaProntoException(String message) {
        super(message);
    }
}
