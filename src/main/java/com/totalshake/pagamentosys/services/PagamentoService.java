package com.totalshake.pagamentosys.services;

import com.totalshake.pagamentosys.exceptions.PagamentoNaoEncontradoException;
import com.totalshake.pagamentosys.models.Pagamento;
import com.totalshake.pagamentosys.repositories.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagamentoService extends BaseService {

    @Autowired
    PagamentoRepository pagamentoRepository;
    public List<Pagamento> retrieveAllPagamentos() {
        List<Pagamento> pagamentosList = pagamentoRepository.findAll();
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
}
