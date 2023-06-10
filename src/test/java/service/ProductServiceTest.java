package service;

import lombok.extern.slf4j.Slf4j;
import net.noir1915.converters.ProductConverter;
import net.noir1915.dto.ProductDto;
import net.noir1915.exception.ResourceNotFoundException;
import net.noir1915.model.Preparation;
import net.noir1915.model.Product;
import net.noir1915.model.ProductType;
import net.noir1915.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class ProductServiceTest {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductConverter productConverter;

    @Test
    void getByIdTest() {

        var productDB = productService.getById(1L);

        assertAll(
                () -> assertEquals("pro1", productDB.getProductTitle()),
                () -> assertEquals(ProductType.NEW, productDB.getProductType()),
                () -> assertTrue(productDB.isProgramWritten()),
                () -> assertEquals(Preparation.NOT_DONE, productDB.getPreparation())
        );
    }

    @Test
    void getAllTest() {
        long count = productService.getAll().size();
        assertTrue(count > 0);
    }

    @Test
    void createUpdateTest() {
        ProductDto newProduct = new ProductDto();
        newProduct.setProductTitle("Cool product");
        newProduct.setProductType("NEW");
        newProduct.setPreparation("NOT_DONE");
        newProduct.setMaterialId(2L);

        Product savedProduct = productService.createOrUpdate(newProduct);

        assertAll(
                () -> assertEquals("Cool product", savedProduct.getProductTitle()),
                () -> assertEquals(Preparation.NOT_DONE, savedProduct.getPreparation()),
                () -> assertEquals(2L, savedProduct.getMaterial().getId()),
                () -> assertEquals(ProductType.NEW, savedProduct.getProductType()),
                () -> assertFalse(savedProduct.isProgramWritten())
        );

        savedProduct.setProductTitle("Renamed Title");
        savedProduct.setPreparation(Preparation.DONE);

        Product updatedPproduct = productService.createOrUpdate(productConverter.entityToDto(savedProduct));

        assertAll(
                () -> assertEquals(savedProduct.getId(), updatedPproduct.getId()),
                () -> assertEquals("Renamed Title", updatedPproduct.getProductTitle()),
                () -> assertEquals(Preparation.DONE, updatedPproduct.getPreparation())
        );

    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @Test
    void deleteTest() {
        Product product = productService.getById(2L);

        assertAll(
                () -> assertEquals(2L, product.getId()),
                () -> assertEquals("pro2", product.getProductTitle())
        );
        productService.remove(product.getId());

        assertThrows(ResourceNotFoundException.class,
                () -> productService.getById(2L));

    }
}