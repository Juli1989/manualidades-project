package com.manualidades.backend.service;

import com.manualidades.backend.entity.Product;
import com.manualidades.backend.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts() {

        return productRepository.findAll();
    }

    public Product saveProduct(Product product) {

        return productRepository.save(product);
    }

    public Product getProductById(Long id) {

        return productRepository.findById(id)
                .orElseThrow();
    }

    public Product updateProduct(
            Long id,
            Product updatedProduct
    ) {

        Product existingProduct = productRepository
                .findById(id)
                .orElseThrow();

        existingProduct.setName(updatedProduct.getName());
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setPrice(updatedProduct.getPrice());

        return productRepository.save(existingProduct);
    }

    public void deleteProduct(Long id) {

        productRepository.deleteById(id);
    }
}