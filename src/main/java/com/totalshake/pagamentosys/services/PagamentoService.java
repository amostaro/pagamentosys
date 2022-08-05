package com.totalshake.pagamentosys.services;

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
}
