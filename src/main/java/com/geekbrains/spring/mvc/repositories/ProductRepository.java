package com.geekbrains.spring.mvc.repositories;

import com.geekbrains.spring.mvc.model.Product;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class ProductRepository {

    private List<Product> products;

    @PostConstruct
    public void init() {
        this.products = new ArrayList<>();
        this.products.add(new Product(1L, "Апельсин", "сладкий, как мед", "EcoFruit", "100"));
        this.products.add(new Product(2L, "Лимонад", "сильногазированный", "Coca-Cola", "30"));
        this.products.add(new Product(3L, "Хлеб", "мягкий", "Московский", "25"));
        this.products.add(new Product(4L, "Вода", "с гор Кавказа", "бонАква", "50"));
    }

    public List<Product> findAll() {
        return Collections.unmodifiableList(products);
    }

    public Product saveOrUpdate(Product product) {
        if (product.getId() == null) {
            product.setId(products.size()+1L);
            products.add(product);
            return product;
        } else {
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getId().equals(product.getId())) {
                    products.set(i, product);
                    return product;
                }
            }
        }
        throw new RuntimeException("Error save or update customer");
    }

    public Product findById(Long id) {
        for (Product product: products) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        throw new RuntimeException("Customer not found");
    }
}
