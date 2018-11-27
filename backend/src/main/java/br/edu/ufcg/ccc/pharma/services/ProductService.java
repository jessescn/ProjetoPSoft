package br.edu.ufcg.ccc.pharma.services;

import br.edu.ufcg.ccc.pharma.models.Batch;
import br.edu.ufcg.ccc.pharma.models.Product;
import br.edu.ufcg.ccc.pharma.repository.BatchRepository;
import br.edu.ufcg.ccc.pharma.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productDAO;
    private final BatchRepository batchDAO;

    @Autowired
    public ProductService(ProductRepository productDAO, BatchRepository batchDAO) {
        this.productDAO = productDAO;
        this.batchDAO = batchDAO;
    }

    public List<Product> getProducts() {
        return (List<Product>) productDAO.findAll();
    }

    public Batch saveBatch(Batch batch) {
        Product product = productDAO.save(batch.getProduct());
        batch.setProduct(product);
        return batchDAO.save(batch);
    }
}
