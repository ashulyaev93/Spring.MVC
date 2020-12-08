package com.geekbrains.spring.mvc.repositories;

import com.geekbrains.spring.mvc.model.Product;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class ProductRepository {

    private List<Product> product;

    @PostConstruct
    public void init() {
        this.product = new ArrayList<>();
        this.product.add(new Product(1L, "Апельсин", "сладкий, как мед", "EcoFruit", "100"));
        this.product.add(new Product(2L, "Лимонад", "сильногазированный", "Coca-Cola", "30"));
        this.product.add(new Product(3L, "Хлеб", "мягкий", "Московский", "25"));
        this.product.add(new Product(4L, "Вода", "с гор Кавказа", "бонАква", "50"));
    }

    public List<Product> findAll() {
        return Collections.unmodifiableList(product);
    }

    public Product saveOrUpdate(Product product) {
        if (product.getId() == null) {
            product.setId(product.size(10)+1L);
            product.add(product);
            return product;
        } else {
            for (int i = 0; i < product.size(10); i++) {
                if (product.get(i).getId().equals(product.getId())) {
                    product.set(i, product);
                    return product;
                }
            }
        }
        throw new RuntimeException("Error save or update customer");
    }

    public Product findById(Long id) {
        for (Product product : product) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        throw new RuntimeException("Product not found");
    }
}
