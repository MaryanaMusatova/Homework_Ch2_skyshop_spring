package org.skypro.skyshop.service;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.exceptions.NoSuchProductException;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.service.BasketService;
import org.skypro.skyshop.model.service.StorageService;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BasketServiceTest {

    @Mock
    private ProductBasket productBasket;

    @Mock
    private StorageService storageService;

    @InjectMocks
    private BasketService basketService;

    @Test
    void addProductByID_WhenProductDoesNotExist_ThrowsException() {
        //Тест: Добавление несуществующего товара в корзину
        //Ожидается: Выброс исключения NoSuchProductException

        UUID productId = UUID.randomUUID();
        when(storageService.getProductById(productId)).thenReturn(Optional.empty());

        assertThrows(NoSuchProductException.class,
                () -> basketService.addProductByID(productId));

        verify(storageService).getProductById(productId);
        verifyNoInteractions(productBasket);
    }

    @Test
    void addProductByID_WhenProductExists_AddsToBasket() {
        //Тест: Добавление существующего товара в корзину
        //Ожидается: Товар успешно добавляется в корзину

        UUID productId = UUID.randomUUID();
        Product product = new SimpleProduct("Ноутбук", 50000, productId);
        when(storageService.getProductById(productId)).thenReturn(Optional.of(product));

        basketService.addProductByID(productId);

        verify(storageService).getProductById(productId);
        verify(productBasket).addProduct(productId);
    }

    @Test
    void getUserBasket_WhenBasketIsEmpty_ReturnsEmptyBasket() {
        //Тест: Получение пустой корзины
        //Ожидается: Возвращается пустой список товаров и общая сумма 0
        when(productBasket.getAllProductsInBasket()).thenReturn(Map.of());

        UserBasket result = basketService.getUserBasket();

        assertTrue(result.getUserBasket().isEmpty());
        assertEquals(0, result.getTotal());
        verify(productBasket).getAllProductsInBasket();
    }

    @Test
    void getUserBasket_WhenBasketHasOneProduct_ReturnsCorrectBasket() {
        //Тест: Получение корзины с одним товаром
        //Ожидается: Корзина содержит 1 товар с правильными данными

        UUID productId = UUID.randomUUID();
        Product product = new SimpleProduct("Ноутбук", 50000, productId);
        when(productBasket.getAllProductsInBasket()).thenReturn(Map.of(productId, 2));
        when(storageService.getProductById(productId)).thenReturn(Optional.of(product));

        UserBasket result = basketService.getUserBasket();

        List<BasketItem> items = result.getUserBasket();
        assertEquals(1, items.size());

        BasketItem item = items.getFirst();
        assertEquals(product, item.getProduct());
        assertEquals(2, item.getQuantity());

        assertEquals(100000, result.getTotal()); // 50000 * 2
        verify(productBasket).getAllProductsInBasket();
        verify(storageService).getProductById(productId);
    }

    @Test
    void getUserBasket_WhenProductInBasketNotFound_ThrowsException() {
        //Тест: Получение корзины с товаром, которого нет в хранилище
        //Ожидается: Выброс исключения NoSuchProductException

        UUID productId = UUID.randomUUID();
        when(productBasket.getAllProductsInBasket()).thenReturn(Map.of(productId, 1));
        when(storageService.getProductById(productId)).thenReturn(Optional.empty());


        assertThrows(NoSuchProductException.class,
                () -> basketService.getUserBasket());

        verify(productBasket).getAllProductsInBasket();
        verify(storageService).getProductById(productId);
    }

    @Test
    void getUserBasket_WhenMultipleProducts_CalculatesTotalCorrectly() {
        //Тест: Расчет общей суммы для корзины с несколькими товарами
        //Ожидается: Корректный расчет общей суммы

        UUID laptopId = UUID.randomUUID();
        UUID phoneId = UUID.randomUUID();
        Product laptop = new SimpleProduct("Ноутбук", 50000, laptopId);
        Product phone = new SimpleProduct("Телефон", 20000, phoneId);

        when(productBasket.getAllProductsInBasket())
                .thenReturn(Map.of(laptopId, 1, phoneId, 3));
        when(storageService.getProductById(laptopId)).thenReturn(Optional.of(laptop));
        when(storageService.getProductById(phoneId)).thenReturn(Optional.of(phone));

        UserBasket result = basketService.getUserBasket();

        assertEquals(2, result.getUserBasket().size());
        assertEquals(110000, result.getTotal()); // 50000*1 + 20000*3
    }
}