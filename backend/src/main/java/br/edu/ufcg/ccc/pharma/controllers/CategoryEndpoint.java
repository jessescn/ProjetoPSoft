package br.edu.ufcg.ccc.pharma.controllers;

import br.edu.ufcg.ccc.pharma.models.Category;
import br.edu.ufcg.ccc.pharma.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("categories")
public class CategoryEndpoint {
    private final CategoryService service;

    @Autowired
    public CategoryEndpoint(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return new ResponseEntity(service.getCategories(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Category category) {
        if (category == null) return new ResponseEntity(HttpStatus.NOT_FOUND);
        return new ResponseEntity(service.save(category), HttpStatus.CREATED);
    }
}
