package com.cap.biz.repositoryImpl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import org.springframework.transaction.annotation.Transactional;

import com.cap.biz.pojo.Product;
import com.cap.biz.repository.ProductRepository;


@Component
public class ProductRepositoryImpl implements ProductRepository {
	
	@Autowired
	SessionFactory sessionFactory;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	 public void addProduct(Product product) {
	       logger.info("start adding product");
	        Session session = sessionFactory.openSession();
	        Transaction tx=session.getTransaction();
	        tx.begin();
	        session.persist(product);
	        tx.commit();
		   
		    logger.info("product is added successfully");
	    }


	@Override
	public Product  findByPid(String id) throws ProductNotFoundException {
		logger.info("start finding product");
	Session session=	sessionFactory.openSession();
	
	Transaction tx=session.getTransaction();
	tx.begin();
	Product product=(Product)session.get(Product.class, new Long(id));
	if(null==product) {
		session.flush();
		logger.error("invalid id  throw ProductNotFoundException ");
		throw new ProductNotFoundException("invalid product id");
	
		
	}
	
	session.flush();
	logger.info("product finded successfully");
	
	return product;
	}


	@Override
	public List<Product> getProductList() {
		
		logger.info("start retriving list of product");
		Session session=sessionFactory.openSession();
		Transaction tx=session.getTransaction();
	    tx.begin();
	  // org.hibernate.Criteria criteria=session.createCriteria(Product.class);
	   //List< Product> list= criteria.list();
	    //or
	    Query query= session.createNativeQuery("select * from biz.product",Product.class);
	    List< Product> list=(List< Product>)query.getResultList();
	    
	    session.flush();
	    logger.info("product list retrive successfully");
		return list;
	}


	@Override
	@Transactional
	public boolean deleteProduct(String id) throws ProductNotFoundException {
		logger.info("start deleting product");
		 Product product=findByPid(id);
		 System.out.println(product);
		 
		 if(product==null)
		 {
			 logger.info("ProductNotFoundException  invalid product id ");
			 throw  new ProductNotFoundException("invalid product id");
		 }
		 if(product.getPid()==Long.parseLong(id) ) {
			 System.out.println("above session factory");
		Session session=sessionFactory.openSession();
		
		Transaction tx=session.getTransaction();
		tx.begin();
		System.out.println("transaction started");
		Query query=session.createNativeQuery("delete from biz.product where p_id="+Long.parseLong(id)+";");
		int deletedpId=query.executeUpdate();
		logger.info("product is deleted successfully");
		
		return true;
		
		}
		 else{
			 return false;
		     }
	}

	

	@Override
	@Transactional
	public Product editUser(Product toEditProduct) throws ProductNotFoundException {
		String id= Long.toString(toEditProduct.getPid());
	
	Product product=findByPid(id);
	Product updatedProduct=null;
	
	if(null==product)
	{
		throw new ProductNotFoundException(" user not present");
	}
	
	 if(null!=toEditProduct.getPname())	
	 {
		product.setPname(toEditProduct.getPname()); 
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
        updatedProduct=(Product)	session.merge(product);
        tx.commit();
	
	  }
	 
		return updatedProduct;
	}

}
