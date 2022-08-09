package com.totalshake.pagamentosys.external;

public interface IntegracaoPedidoSysPort {

    void makePagamentoByPedidoId(Long idPedido);
}
