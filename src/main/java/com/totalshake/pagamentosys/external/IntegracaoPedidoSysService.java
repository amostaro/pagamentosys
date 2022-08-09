package com.totalshake.pagamentosys.external;

import com.totalshake.pagamentosys.DTO.PagamentoDTO;
import com.totalshake.pagamentosys.exceptions.PedidoNaoEncontradoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class IntegracaoPedidoSysService implements IntegracaoPedidoSysPort {

    @Value("http://localhost:8090/api/v1/pedidos")
    private String host;

    private static final Logger LOGGER = LoggerFactory.getLogger(IntegracaoPedidoSysPort.class);

    @Override
    public void makePagamentoByPedidoId(Long idPedido) {

        try {
            RestTemplate restTemplate = new RestTemplate();

            HttpEntity<String> request = new HttpEntity<>(
                    "{" + "\"idPedido\": \"" +idPedido+ "\" }"
            );

            String url = "/makePagamentoByPedidoId/"+idPedido+"";

            restTemplate.exchange(host + url, HttpMethod.POST, request, PagamentoDTO.class);

        } catch(PedidoNaoEncontradoException p) {
            throw new PedidoNaoEncontradoException("Pedido: " +idPedido+ " não encontrado.");
        }
    }

}
