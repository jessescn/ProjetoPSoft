package br.edu.ufcg.ccc.pharma.product;

import br.edu.ufcg.ccc.pharma.error.CustomErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") int id) {
        Product product = productService.findProductById(id);
        if (product == null)
            return new ResponseEntity<>(new CustomErrorMessage("Produto não encontrado"), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
