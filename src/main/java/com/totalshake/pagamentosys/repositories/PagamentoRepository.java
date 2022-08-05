package com.totalshake.pagamentosys.repositories;

import com.totalshake.pagamentosys.models.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}
