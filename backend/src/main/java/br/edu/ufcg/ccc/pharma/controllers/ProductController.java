package br.edu.ufcg.ccc.pharma.controllers;

import br.edu.ufcg.ccc.pharma.models.Batch;
import br.edu.ufcg.ccc.pharma.models.Product;
import br.edu.ufcg.ccc.pharma.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("products")
public class ProductController {
    private final ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(service.getProducts(), HttpStatus.OK);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> saveBatch(@RequestBody Batch batch) {
        return new ResponseEntity<>(service.saveBatch(batch), HttpStatus.CREATED);
    }

    @GetMapping("/{id}/stock")
    public ResponseEntity<?> getProductAmount(@PathVariable long id) {
        int amount = service.countStock(id);
        if (amount < 0) return new ResponseEntity(HttpStatus.NOT_FOUND);
        return new ResponseEntity(amount, HttpStatus.OK);
    }
}
