package com.totalshake.pagamentosys.services;

import com.totalshake.pagamentosys.DTO.PagamentoDTO;
import com.totalshake.pagamentosys.exceptions.PagamentoNaoEncontradoException;
import com.totalshake.pagamentosys.models.Pagamento;
import com.totalshake.pagamentosys.repositories.PagamentoRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagamentoService extends BaseService {

    @Autowired
    PagamentoRepository pagamentoRepository;
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
            throw new PagamentoNaoEncontradoException("Operação inválida! Item Pedido não pode ser vazio.");
        }

        Long idPedido = pagamentoDTO.getPedidoId();

//        Pedido pedido = pedidoRepository.findById(idPedido).orElseThrow(
//                () -> new PedidoNaoEncontradoException("Operação inválida! Pedido não cadastrado.")
//        );

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
}
