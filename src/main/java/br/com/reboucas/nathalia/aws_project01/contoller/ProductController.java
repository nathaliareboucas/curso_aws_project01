package br.com.reboucas.nathalia.aws_project01.contoller;

import br.com.reboucas.nathalia.aws_project01.model.Product;
import br.com.reboucas.nathalia.aws_project01.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository repository;

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        List<Product> products = repository.findAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        Optional<Product> byId = repository.findById(id);
        return byId.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        Product productCreated = repository.save(product);
        return new ResponseEntity<Product>(productCreated, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable Long id) {
        Optional<Product> byId = repository.findById(id);
        if (byId.isPresent()) {
            product.setId(id);
            Product productUpdated = repository.save(product);
            return ResponseEntity.ok(productUpdated);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id) {
        Optional<Product> byId = repository.findById(id);
        if (byId.isPresent()) {
            Product product = byId.get();
            repository.delete(product);
            return ResponseEntity.ok(product);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<Product> findByCode(@PathVariable String code) {
        Optional<Product> byCode = repository.findByCode(code);
        return byCode.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}