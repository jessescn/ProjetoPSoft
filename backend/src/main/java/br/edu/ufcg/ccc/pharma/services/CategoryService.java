package br.edu.ufcg.ccc.pharma.services;

import br.edu.ufcg.ccc.pharma.models.Category;
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
}
