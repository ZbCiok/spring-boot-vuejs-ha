package blog.zciok.springboot.restful.product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRespository extends JpaRepository<Product, Long> {
	
    @Query("select c from Product c where c.category = :category")
    List<Product> findByCategory(@Param("category") String category);	

}
