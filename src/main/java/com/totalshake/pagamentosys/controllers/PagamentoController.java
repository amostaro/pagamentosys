package com.totalshake.pagamentosys.controllers;

import com.totalshake.pagamentosys.exceptions.PagamentoNaoEncontradoException;
import com.totalshake.pagamentosys.models.Pagamento;
import com.totalshake.pagamentosys.services.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/pagamentos")
public class PagamentoController extends BaseController {

    @Autowired
    PagamentoService pagamentoService;

    @GetMapping("/retrieve-all")
    public ResponseEntity<List<Pagamento>> retrieveAllPagamentos() {
        List<Pagamento> pagamentosList = pagamentoService.retrieveAllPagamentos();
        return ResponseEntity.ok(pagamentosList);
    }

    @GetMapping("/retrieve/{id}")
    public ResponseEntity<Pagamento> retrievePagamentoById(@Valid @PathVariable("id") Long idPagamento) throws PagamentoNaoEncontradoException {
        Pagamento pagamento = pagamentoService.retrievePagamentoById(idPagamento);
        return ResponseEntity.ok(pagamento);
    }
}
