package com.example.SalesManagment.Controller.categoriesController;

import com.example.SalesManagment.Model.Product.Categories;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public interface CategoriesService {
    void addCategory(Categories categories);

    List<Categories> getAllCategory();

    Optional<Categories> findByName(String name);

    long countCategory();

}
