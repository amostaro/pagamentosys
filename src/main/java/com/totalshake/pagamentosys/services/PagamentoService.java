package com.totalshake.pagamentosys.services;

import com.totalshake.pagamentosys.DTO.PagamentoDTO;
import com.totalshake.pagamentosys.enums.EnumStatus;
import com.totalshake.pagamentosys.exceptions.PagamentoNaoEncontradoException;
import com.totalshake.pagamentosys.exceptions.PedidoNaoEstaProntoException;
import com.totalshake.pagamentosys.interfaces.PedidoPagoEndPoint;
import com.totalshake.pagamentosys.models.Pagamento;
import com.totalshake.pagamentosys.repositories.PagamentoRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PagamentoService extends BaseService {

    @Autowired
    PagamentoRepository pagamentoRepository;

    @Autowired
    PedidoPagoEndPoint pedidoPagoEndPoint;

    public List<Pagamento> retrieveAllPagamentos() {
        List<Pagamento> pagamentosList = pagamentoRepository.findAll();
        pagamentosList.stream()
                .map(pag -> (super.convertToDTO(pag, PagamentoDTO.class)))
                .toList();
        return pagamentosList;
    }

    public Pagamento retrievePagamentoById(Long idPagamento) {

        Pagamento pagamento = null;
        try {
            pagamento = pagamentoRepository.findById(idPagamento).orElseThrow(
                    () -> new PagamentoNaoEncontradoException("Pagamento: "+idPagamento+" não encontrado.")
            );
        } catch (Exception e) {
            throw new PagamentoNaoEncontradoException("Pagamento: "+idPagamento+" não encontrado.");
        }
        return pagamento;
    }

    public void deletePagamentoById(Long idPagamento) throws PagamentoNaoEncontradoException {

        this.retrievePagamentoById(idPagamento);
        pagamentoRepository.deleteById(idPagamento);
    }

    public Pagamento createPagamento(PagamentoDTO pagamentoDTO) throws PagamentoNaoEncontradoException {

        if (ObjectUtils.isEmpty(pagamentoDTO)) {
            throw new PagamentoNaoEncontradoException("Operação inválida! Pagamento não pode ser vazio.");
        }

        String statusPedido = this.pedidoPagoEndPoint.buscarStatusPedidoById(pagamentoDTO.getPedidoId());
        if (statusPedido != "PRONTO") {
            throw new PedidoNaoEstaProntoException("Operação inválida! Pedido ainda não esta pronto.");
        }

        pagamentoDTO.setId(null);
        pagamentoDTO.setValor(pagamentoDTO.getValor());
        pagamentoDTO.setNome(pagamentoDTO.getNome());
        pagamentoDTO.setNumero(pagamentoDTO.getNumero());
        pagamentoDTO.setExpiracao(pagamentoDTO.getExpiracao());
        pagamentoDTO.setCodigo(pagamentoDTO.getCodigo());
        pagamentoDTO.setStatus(pagamentoDTO.getStatus());
        pagamentoDTO.setPedidoId(pagamentoDTO.getPedidoId());
        pagamentoDTO.setFormaPagamento(pagamentoDTO.getFormaPagamento());

        Pagamento pagamento = super.convertToModel(pagamentoDTO, Pagamento.class);
//        pagamento.setPedido(pedido);

        this.pagamentoRepository.save(pagamento);
        return pagamento;
    }

    public Pagamento updatePagamento(PagamentoDTO pagamentoDTO) {

        Pagamento pagamento = null;
        if (!ObjectUtils.isEmpty(pagamentoDTO)) {
            pagamento = this.retrievePagamentoById(pagamentoDTO.getId());

            pagamento.setValor(pagamentoDTO.getValor());
            pagamento.setNome(pagamentoDTO.getNome());
            pagamento.setNumero(pagamentoDTO.getNumero());
            pagamento.setExpiracao(pagamentoDTO.getExpiracao());
            pagamento.setCodigo(pagamentoDTO.getCodigo());
            pagamento.setStatus(pagamentoDTO.getStatus());
            pagamento.setPedidoId(pagamentoDTO.getPedidoId());
            pagamento.setFormaPagamento(pagamentoDTO.getFormaPagamento());

        } else {
            throw new PagamentoNaoEncontradoException("Pagamento: "+pagamentoDTO.getId() + " não encontrado.");
        }

        return pagamentoRepository.save(pagamento);
    }

    public void pagarPedidoById(Long idPagamento) {

        Pagamento pagamento = this.retrievePagamentoById(idPagamento);

        pagamento.setStatus(EnumStatus.CONFIRMADO);
        pagamento.setAtualizadoEm(new Date());

        this.pagamentoRepository.save(pagamento);

        this.pedidoPagoEndPoint.pagarPedidoById(pagamento.getPedidoId());

    }
}
