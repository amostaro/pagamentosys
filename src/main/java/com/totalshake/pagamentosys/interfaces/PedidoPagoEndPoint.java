package com.totalshake.pagamentosys.interfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "pedido", url = "http://localhost:8090/api/v1/pedidos")
public interface PedidoPagoEndPoint {

    @RequestMapping(method = RequestMethod.GET, value = "")
    void retrieveAllPedidos();

    @PutMapping("/receber/pagamento/pedido/{id}")
    void pagarPedidoById(@PathVariable("id") Long idPedido);
}
