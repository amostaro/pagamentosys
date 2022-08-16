package com.totalshake.pagamentosys.interfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "pedido", url = "http://localhost:8090/api/v1/pedidos")
public interface PedidoSysEndPoint {

    @PutMapping("/receber/pagamento/pedido/{id}")
    void pagarPedidoById(@PathVariable("id") Long idPedido);

    @GetMapping("/buscar/status/pedido/{id}")
    String buscarStatusPedidoById(@PathVariable("id") Long pedidoId);
}
