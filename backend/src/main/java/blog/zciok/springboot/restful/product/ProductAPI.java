package blog.zciok.springboot.restful.product;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
@Slf4j
@RequiredArgsConstructor
public class ProductAPI implements Serializable {

	private static final long serialVersionUID = 5864381013389092576L;
	
	private final ProductService productService;

    @GetMapping  
    public ResponseEntity<List<Product>> findAll() {
    	log.info(">>> All ");
        return ResponseEntity.ok(productService.findAll());
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody Product product) {
    	log.info(">>> save ");
        return ResponseEntity.ok(productService.save(product));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
    	log.info(">>> findById" + id);
        Optional<Product> stock = productService.findById(id);
        if (!stock.isPresent()) {
            log.error("Id " + id + " is not existed");
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(stock.get());
    }
    
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> findByCategory(@PathVariable String category) {
    	log.info(">>> findByCategory ");
        return ResponseEntity.ok(productService.findByCategory(category));
    }    

    @PutMapping("/category/{category}/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @Valid @RequestBody Product product) {
    	log.info(">>> update " + id);
        if (!productService.findById(id).isPresent()) {
            log.error("Id " + id + " is not existed");
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(productService.save(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        if (!productService.findById(id).isPresent()) {
            log.error("Id " + id + " is not existed");
            ResponseEntity.badRequest().build();
        }

        productService.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
