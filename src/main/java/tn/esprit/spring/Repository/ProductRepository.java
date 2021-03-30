package tn.esprit.spring.Repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.Entities.*;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> 
{

	
	@Query("Select "
			+ "DISTINCT proc from Product proc "
			+ "where proc.name=:name")
    public List<Product> ProductNames(@Param("name") String name);
	
	 @Query("Select "
				+ "DISTINCT proc from Product proc "
				+ "where proc.category.id=:id ")
	    public List<Product> getAllProductByCategory(@Param("id") long id);
	 
	 @Query("Select "
				+ "DISTINCT proc from Product proc "
				+ "where proc.category.name=:name ")
	    public List<Product> getAllProductByCategoryname(@Param("name") String name);
	 
	 @Query("Select p.code , p.name , p.price , p.picture , p.quantity , p.category.name FROM Product p")
	    public List<Product> getAllProduct();
	 
	 
}
