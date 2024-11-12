package org.lopertut.appHelpers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.lopertut.appHelpers.PurchaseAppHelper;
import org.lopertut.models.Purchase;
import org.lopertut.models.User;
import org.lopertut.models.Toy;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PurchaseAppHelperTest {

    private PurchaseAppHelper purchaseAppHelper;

    @BeforeEach
    void setUp() {
        purchaseAppHelper = new PurchaseAppHelper();
    }

    @Test
    void testCreatePurchase() {
        // Используем Mockito для мокирования метода getString()
        PurchaseAppHelper helperSpy = spy(purchaseAppHelper);

        // Указываем последовательные возвращаемые значения для getString
        doReturn("Иван")        // Имя покупателя
                .doReturn("Иванов")      // Фамилия покупателя
                .doReturn("Мяч")         // Название игрушки
                .doReturn("500")         // Цена игрушки
                .doReturn("2024-10-15")  // Дата покупки
                .when(helperSpy).getString();

        Purchase purchase = helperSpy.create();

        assertNotNull(purchase, "Покупка должна быть создана");
        assertEquals("Иван", purchase.getUser().getFirstname());
        assertEquals("Иванов", purchase.getUser().getLastname());
        assertEquals("Мяч", purchase.getToy().getName());
        assertEquals(500, purchase.getToy().getPrice());
        assertEquals(LocalDate.of(2024, 10, 15), purchase.getPurchaseDate());
    }
}
