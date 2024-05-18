package ru.shop.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.shop.exception.EntityNotFoundException;
import ru.shop.model.Product;
import ru.shop.model.ProductType;
import ru.shop.repository.ProductRepository;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ProductServiceTest {

    private final ProductRepository repository = Mockito.mock();
    private final ProductService service = new ProductService(repository);

    @Test
    public void shouldGetProduct() {
        //given
        UUID productId = UUID.randomUUID();
        Product mockedProduct = new Product(productId, "Product1", 1000, ProductType.GOOD);

        Mockito.when(repository.findById(productId))
                .thenReturn(Optional.of(mockedProduct));

        Product product = service.getById(productId);

        //Assertions.assertEquals(mockedProduct, product);
        assertThat(product).isEqualTo(mockedProduct);
    }

    @Test
    public void shouldThrowWhenProductNotFound() {
        // then
//        Assertions.assertThrows(
//                EntityNotFoundException.class,
//                () -> service.getById(UUID.randomUUID())
//        );

        assertThatThrownBy(()->service.getById(UUID.randomUUID()))
                .isInstanceOf(EntityNotFoundException.class);
    }
}