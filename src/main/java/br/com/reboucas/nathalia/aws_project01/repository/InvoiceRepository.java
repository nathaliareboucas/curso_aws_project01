package br.com.reboucas.nathalia.aws_project01.repository;

import br.com.reboucas.nathalia.aws_project01.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    Optional<Invoice> findByInvoiceNumber(String invoiceNumber);
    List<Invoice> findAllByCustomerName(String customerName);
}
