package com.totalshake.pagamentosys.interfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "pedido", url = "http://localhost:8090/api/v1/pedidos/retrieve-all")
public interface PedidoPagoEndPoint {

    @RequestMapping(method = RequestMethod.GET, value = "")
    void retrieveAllPedidos();
}
