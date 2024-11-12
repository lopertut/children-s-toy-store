package org.lopertut.appHelpers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.lopertut.models.Toy;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ToyAppHelperTest {

    private ToyAppHelper toyAppHelper;

    @BeforeEach
    void setUp() {
        toyAppHelper = spy(ToyAppHelper.class);
    }

    @Test
    void testCreateToy() {
        // Mocking input interactions for creating a toy with correct sequence
        doReturn("ToyName")               // Toy name
                .doReturn("2")                // Number of materials
                .doReturn("Material1")        // First material
                .doReturn("Material2")        // Second material
                .doReturn("5")                // Recommended age
                .doReturn("100")              // Price
                .when(toyAppHelper).getString();

        Toy toy = toyAppHelper.create();

        assertNotNull(toy);
        assertEquals("ToyName", toy.getName());
        assertEquals(2, toy.getMaterials().size());
        assertEquals("Material1", toy.getMaterials().get(0));
        assertEquals("Material2", toy.getMaterials().get(1));
        assertEquals(5, toy.getRecommendedAge());
        assertEquals(100, toy.getPrice());
    }

    @Test
    void testPrintListEmptyToys() {
        List<Toy> emptyToys = new ArrayList<>();
        assertFalse(toyAppHelper.printList(emptyToys));
    }

    @Test
    void testPrintListNonEmptyToys() {
        Toy toy = new Toy();
        toy.setName("Test Toy");
        toy.getMaterials().add("Material1");
        toy.setRecommendedAge(5);
        toy.setPrice(100);

        List<Toy> toys = new ArrayList<>();
        toys.add(toy);

        assertTrue(toyAppHelper.printList(toys));
    }
}
