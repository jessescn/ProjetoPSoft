package br.edu.ufcg.ccc.pharma.service;

import br.edu.ufcg.ccc.pharma.model.Batch;
import br.edu.ufcg.ccc.pharma.model.Product;
import br.edu.ufcg.ccc.pharma.repository.BatchRepository;
import br.edu.ufcg.ccc.pharma.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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
        setupAvailability(batch);
        Product product = productDAO.save(batch.getProduct());
        batch.setProduct(product);
        return batchDAO.save(batch);
    }

    private void setupAvailability(Batch batch) {
        Optional<Product> productOptional = productDAO.findById(batch.getProduct().getId());

        if (productOptional.isPresent()) {
            Product productInDB = productOptional.get();

            if (!productInDB.isAvailable()) {
                if (batch.getExpiration().after(new Date()) && batch.getAmount() > 0)
                    batch.getProduct().setAvailable(true);
                else
                    batch.getProduct().setAvailable(false);
            }
        } else {
            if (batch.getExpiration().after(new Date()) && batch.getAmount() > 0)
                batch.getProduct().setAvailable(true);
            else
                batch.getProduct().setAvailable(false);
        }
    }

    public int countStock(long id) {
        int amount = -1;
        if (productDAO.existsById(id))
            amount = batchDAO.findTotalAmount(id);
        return amount;
    }
}
