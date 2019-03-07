package com.cap.biz.repository;

import java.util.List;

import com.cap.biz.pojo.Product;
import com.cap.biz.repositoryImpl.ProductNotFoundException;


public interface ProductRepository {
	
	void addProduct(Product product);
	
    Product findByPid(String id)throws ProductNotFoundException;
    
    List<Product> getProductList();
    
    boolean deleteProduct(String id)throws ProductNotFoundException ;
    
    Product editUser(Product toEditProduct)throws ProductNotFoundException;

}
