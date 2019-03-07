package com.cap.biz.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.cap.biz.pojo.Product;
import com.cap.biz.repositoryImpl.ProductNotFoundException;
import com.cap.biz.service.ProductService;

@RestController


public class ProductController {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	ProductService productService;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value="/product/check")
	public String test()
	{

		logger.debug("This is a debug message");
		logger.info("This is an info message");
		logger.warn("This is a warn message");
		logger.error("This is an error message");
		return "working fine";
	}
	
	//add new product
	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public ResponseEntity<Object>  saveProduct(@Valid @RequestBody Product product)
	{
		productService.saveProduct(product);	
		return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);
	}
	
	
	//get product by its id
	@RequestMapping(value="/products/{id}",method= RequestMethod.GET,produces="Application/json")

	public ResponseEntity<Object> getProduct(@PathVariable String id)
	{
		
	    Product product=	productService.findBypid(id);
	    if(null!=product) {
		return new ResponseEntity<Object>(product, HttpStatus.OK);
	    }
	    else {
	    	return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	    }
	}

	//get list of product
	@RequestMapping(value="/products",method= RequestMethod.GET,produces="Application/Json")
	public ResponseEntity<Object> getProductList()
	{
	List< Product> productList=	productService.getProductList();
		System.out.println(productList);
		return new ResponseEntity<Object>(productList, HttpStatus.OK);
	}
	
	
	//delete product
	
	@RequestMapping(value="/products/{id}",method= RequestMethod.DELETE,produces="Application/json")
	
	public ResponseEntity<Object>deleteProduct(@PathVariable String id) {
		
		boolean result=	productService.deleteProduct(id);
		/*try {
	boolean result=	productService.deleteProduct(id);
		}catch(ProductNotFoundException px) {
			throw new ResponseStatusException(
			          HttpStatus.NOT_FOUND, px.getMessage());
			
		}*/
	return new ResponseEntity<>("product is successfully deleted",HttpStatus.OK);
	
			/*if(result==true)
		   {
			return new ResponseEntity<>("product is successfully deleted",HttpStatus.OK);
		   }
		   else {
			return new ResponseEntity<>("product doesnot exist",HttpStatus.NO_CONTENT);
		        }*/
    }
	
	
	//update product
	@RequestMapping(value="/products/{id}",method=RequestMethod.PUT,produces="Application/json")

	public ResponseEntity<Object> editProduct(@PathVariable long id,@Valid @RequestBody Product toEditProduct) throws ProductNotFoundException	{
		
		//toEditProduct.setPid(Long.parseLong(id));
		toEditProduct.setPid(id);
		Product product=productService.editUser(toEditProduct);
		return new ResponseEntity<>(product,HttpStatus.OK);
	}
	
	
		
	}
	
	

