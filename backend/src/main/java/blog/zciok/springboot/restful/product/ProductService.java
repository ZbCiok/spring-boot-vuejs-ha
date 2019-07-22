package blog.zciok.springboot.restful.product;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService implements Serializable {

	private static final long serialVersionUID = 6563372562747081329L;
	
	private final ProductRespository productRespository;

    public List<Product> findAll() {
        return productRespository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return productRespository.findById(id);
    }

    public Product save(Product stock) {
        return productRespository.save(stock);
    }

    public void deleteById(Long id) {
        productRespository.deleteById(id);
    }

	public List<Product> findByCategory(String category) {
		return productRespository.findByCategory(category);
	}
}
