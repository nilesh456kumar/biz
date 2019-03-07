package com.cap.biz.serviceImpl;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.cap.biz.pojo.Product;
import com.cap.biz.repositoryImpl.ProductNotFoundException;
import com.cap.biz.repositoryImpl.ProductRepositoryImpl;
import com.cap.biz.service.ProductService;


@Component
public class ProductServiceImp implements ProductService{
	
	
	@Autowired 
	ProductRepositoryImpl productRepositoryImpl;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());


@Override
public Product findBypid(String pid) throws ProductNotFoundException {
	Product product=productRepositoryImpl.findByPid(pid);
	
	return product;
}

  
public void saveProduct(Product product)
{
	productRepositoryImpl.addProduct(product);
}


@Override
public List<Product> getProductList() {
      List<Product> productList=productRepositoryImpl.getProductList();
	  return productList;
}


@Override
public boolean deleteProduct(String id)throws ProductNotFoundException {
	
	return productRepositoryImpl.deleteProduct(id);
}


@Override
public Product editUser(Product toEditProduct) throws ProductNotFoundException {

	return productRepositoryImpl.editUser(toEditProduct);
}


}
