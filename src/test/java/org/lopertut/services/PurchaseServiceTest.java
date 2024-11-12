package org.lopertut.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.lopertut.interfaces.AppHelper;
import org.lopertut.interfaces.FileRepository;
import org.lopertut.models.Purchase;
import org.lopertut.models.User;
import org.lopertut.models.Toy;
import org.lopertut.services.PurchaseService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PurchaseServiceTest {

    private PurchaseService purchaseService;
    private AppHelper<Purchase> mockAppHelperPurchase;
    private FileRepository<Purchase> mockStorage;

    @BeforeEach
    void setUp() {
        mockAppHelperPurchase = mock(AppHelper.class);
        mockStorage = mock(FileRepository.class);
        purchaseService = new PurchaseService(mockAppHelperPurchase, mockStorage);
    }

    @Test
    void testAddPurchaseSuccess() {
        User user = new User("Иван", "Иванов", "1234567890");
        Toy toy = new Toy("Мяч", 500, new ArrayList<>(), 5);
        Purchase purchase = new Purchase(user, toy, LocalDate.of(2024, 10, 15));
        when(mockAppHelperPurchase.create()).thenReturn(purchase);

        boolean result = purchaseService.add();

        assertTrue(result, "Покупка должна быть успешно добавлена");
        verify(mockStorage).save(purchase, "purchase");
    }

    @Test
    void testAddPurchaseFailure() {
        when(mockAppHelperPurchase.create()).thenReturn(null); // Имитируем, что создание покупки не удалось

        boolean result = purchaseService.add();

        assertFalse(result, "Покупка не должна быть добавлена, если создание не удалось");
        verify(mockStorage, never()).save(any(Purchase.class), anyString());
    }

    @Test
    void testPrintWithPurchases() {
        List<Purchase> purchases = List.of(
                new Purchase(
                        new User("Иван", "Иванов", "1234567890"),
                        new Toy("Мяч", 500, List.of("Резина"), 5),
                        LocalDate.of(2024, 10, 15)
                )
        );
        when(mockStorage.load("purchase")).thenReturn(purchases);
        when(mockAppHelperPurchase.printList(purchases)).thenReturn(true);

        boolean result = purchaseService.print();

        assertTrue(result, "Метод print должен возвращать true, если список не пуст");
        verify(mockAppHelperPurchase).printList(purchases);
    }

    @Test
    void testPrintEmptyList() {
        List<Purchase> emptyList = new ArrayList<>();
        when(mockStorage.load("purchase")).thenReturn(emptyList);
        when(mockAppHelperPurchase.printList(emptyList)).thenReturn(false);

        boolean result = purchaseService.print();

        assertFalse(result, "Метод print должен возвращать false, если список пуст");
        verify(mockAppHelperPurchase).printList(emptyList);
    }

    @Test
    void testList() {
        List<Purchase> expectedPurchases = List.of(
                new Purchase(
                        new User("Иван", "Иванов", "1234567890"),
                        new Toy("Кукла", 700, List.of("Пластик"), 3),
                        LocalDate.of(2024, 10, 15)
                )
        );
        when(mockStorage.load("purchase")).thenReturn(expectedPurchases);

        List<Purchase> purchases = purchaseService.list();

        assertEquals(expectedPurchases, purchases, "Метод list должен возвращать ожидаемый список покупок");
        verify(mockStorage).load("purchase");
    }
}
