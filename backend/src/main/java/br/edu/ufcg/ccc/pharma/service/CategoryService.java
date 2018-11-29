package br.edu.ufcg.ccc.pharma.service;

import br.edu.ufcg.ccc.pharma.model.Category;
import br.edu.ufcg.ccc.pharma.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository dao;

    @Autowired
    public CategoryService(CategoryRepository dao) {
        this.dao = dao;
    }

    public List<Category> getCategories() {
        return (List<Category>) dao.findAll();
    }

    public Category save(Category category) {
        if (category == null) throw new RuntimeException("Produto vazio!");
        return dao.save(category);
    }
}
