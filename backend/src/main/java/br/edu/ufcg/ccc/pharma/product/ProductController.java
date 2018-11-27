package br.edu.ufcg.ccc.pharma.product;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {

    @RequestMapping
    public ResponseEntity<?> findAll() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(1, "Sabonete Granado", "12341241", "Granado S.A.", "disponível"));
        products.add(new Product(2, "Shampoo Granado", "12341242", "Granado S.A.", "disponível"));
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
