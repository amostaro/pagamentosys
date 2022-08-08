package com.totalshake.pagamentosys.controllers;

import com.totalshake.pagamentosys.DTO.PagamentoDTO;
import com.totalshake.pagamentosys.exceptions.PagamentoNaoEncontradoException;
import com.totalshake.pagamentosys.models.Pagamento;
import com.totalshake.pagamentosys.services.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Pagamento> deletePedidoById(@Valid @PathVariable("id") Long idPagamento) throws PagamentoNaoEncontradoException {
        pagamentoService.deletePagamentoById(idPagamento);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Pagamento> createItemPedido(@Valid @RequestBody PagamentoDTO pagamentoDTO) throws Exception {
        Pagamento novoPagamento = pagamentoService.createPagamento(pagamentoDTO);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novoPagamento.getId()).toUri();
        return ResponseEntity.created(location).body(novoPagamento);
    }

    @PutMapping("/update")
    public ResponseEntity<Pagamento> updatePagamento(@Valid @RequestBody PagamentoDTO pagamentoDTO) throws PagamentoNaoEncontradoException {
        Pagamento pagamento = pagamentoService.updatePagamento(pagamentoDTO);
        return ResponseEntity.ok(pagamento);
    }
}
