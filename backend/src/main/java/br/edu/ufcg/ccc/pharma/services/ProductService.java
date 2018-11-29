package br.edu.ufcg.ccc.pharma.services;

import br.edu.ufcg.ccc.pharma.models.Batch;
import br.edu.ufcg.ccc.pharma.models.Product;
import br.edu.ufcg.ccc.pharma.repository.BatchRepository;
import br.edu.ufcg.ccc.pharma.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
        if (batch.getExpiration().after(new Date()) && batch.getAmount() > 0)
            batch.getProduct().setAvailable(true);
        else
            batch.getProduct().setAvailable(false);

        Product product = productDAO.save(batch.getProduct());
        batch.setProduct(product);
        return batchDAO.save(batch);
    }

    public int countStock(long id) {
        int amount = -1;
        if (productDAO.existsById(id))
            amount = batchDAO.findTotalAmount(id);
        return amount;
    }
}
