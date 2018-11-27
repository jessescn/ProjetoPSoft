package br.edu.ufcg.ccc.pharma.product;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private List<Product> products;

    public ProductService() {
        products = new ArrayList<>();
        products.add(new Product(1, "Sabonete Granado", "12341241", "Granado S.A.", "disponível"));
        products.add(new Product(2, "Shampoo Granado", "12341242", "Granado S.A.", "disponível"));
    }

    public List<Product> findAll() {
        return products;
    }

    public Product findProductById(int id) {
        Product product = new Product(id, null, null, null, null);
        int index = products.indexOf(product);
        if (index == -1)
            return null;
        return products.get(index);
    }
}
