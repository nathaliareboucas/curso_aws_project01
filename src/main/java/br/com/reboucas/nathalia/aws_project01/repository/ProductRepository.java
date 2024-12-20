package br.com.reboucas.nathalia.aws_project01.repository;

import br.com.reboucas.nathalia.aws_project01.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByCode(String code);
}
