package com.cap.biz.service;

import java.util.Collection;
import java.util.List;

import com.cap.biz.pojo.Product;
import com.cap.biz.repositoryImpl.ProductNotFoundException;


public interface ProductService {

	
	public Product findBypid(String pid) throws ProductNotFoundException;
	
	
	void saveProduct(Product product);
	
	 public List<Product> getProductList ();
	 
	 boolean deleteProduct(String id) throws ProductNotFoundException;
	 
	 Product editUser(Product toEditProduct)throws ProductNotFoundException ;
	

}
