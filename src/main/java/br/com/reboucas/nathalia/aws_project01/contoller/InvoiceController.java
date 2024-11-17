package br.com.reboucas.nathalia.aws_project01.contoller;

import br.com.reboucas.nathalia.aws_project01.model.Invoice;
import br.com.reboucas.nathalia.aws_project01.model.UrlResponse;
import br.com.reboucas.nathalia.aws_project01.repository.InvoiceRepository;
import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/invoices")
@RequiredArgsConstructor
public class InvoiceController {
    @Value("${aws.s3.bucket.invoice.name}")
    private String bucketName;

    private final AmazonS3 amazonS3;
    private final InvoiceRepository invoiceRepository;

    @GetMapping("/url")
    public ResponseEntity<UrlResponse> createInvoiceUrl() {
        UrlResponse urlResponse = new UrlResponse();
        Instant expirationTime = Instant.now().plus(Duration.ofMinutes(5));
        String processId = UUID.randomUUID().toString();
        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName, processId)
                .withMethod(HttpMethod.PUT)
                .withExpiration(Date.from(expirationTime));

        urlResponse.setUrl(amazonS3.generatePresignedUrl(generatePresignedUrlRequest).toString());
        urlResponse.setExpirationTime(expirationTime.getEpochSecond());

        return ResponseEntity.ok(urlResponse);
    }

    @GetMapping
    public ResponseEntity<Iterable<Invoice>> findAll() {
        List<Invoice> invoices = invoiceRepository.findAll();
        return ResponseEntity.ok(invoices);
    }

    @GetMapping(path = "/bycustomername")
    public ResponseEntity<Iterable<Invoice>> findByCustomerName(@RequestParam String customerName) {
        List<Invoice> invoices = invoiceRepository.findAllByCustomerName(customerName);
        return ResponseEntity.ok(invoices);
    }
}
