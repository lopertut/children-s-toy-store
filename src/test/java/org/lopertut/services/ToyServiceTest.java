package org.lopertut.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.lopertut.models.Toy;
import org.lopertut.interfaces.AppHelper;
import org.lopertut.interfaces.FileRepository;
import org.lopertut.services.ToyService;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ToyServiceTest {

    private ToyService toyService;
    private AppHelper<Toy> mockHelper;
    private FileRepository<Toy> mockRepository;
    private Toy mockToy;

    @BeforeEach
    void setUp() {
        mockHelper = mock(AppHelper.class);
        mockRepository = mock(FileRepository.class);
        toyService = new ToyService(mockHelper, mockRepository);
        mockToy = new Toy(); // Assuming Toy has a default constructor
    }

    @Test
    void testAddToy() {
        when(mockHelper.create()).thenReturn(mockToy);
        assertTrue(toyService.add());
        verify(mockRepository).save(mockToy, "toys");
    }

    @Test
    void testAddToyFailsWhenNull() {
        when(mockHelper.create()).thenReturn(null);
        assertFalse(toyService.add());
        verify(mockRepository, never()).save(any(), anyString());
    }

    @Test
    void testEditToy() {
        assertFalse(toyService.edit(mockToy));
    }

    @Test
    void testRemoveToy() {
        assertFalse(toyService.remove(mockToy));
    }

    @Test
    void testPrintToys() {
        List<Toy> toys = new ArrayList<>();
        toys.add(mockToy);
        when(mockRepository.load("toys")).thenReturn(toys);
        when(mockHelper.printList(toys)).thenReturn(true);

        assertTrue(toyService.print());
    }

    @Test
    void testListToys() {
        List<Toy> toys = new ArrayList<>();
        toys.add(mockToy);
        when(mockRepository.load("toys")).thenReturn(toys);

        assertEquals(toys, toyService.list());
    }
}
