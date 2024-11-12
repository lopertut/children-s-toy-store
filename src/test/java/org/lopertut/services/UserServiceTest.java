package org.lopertut.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.lopertut.models.User;
import org.lopertut.interfaces.AppHelper;
import org.lopertut.interfaces.FileRepository;
import org.lopertut.services.UserService;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private UserService userService;
    private AppHelper<User> mockHelper;
    private FileRepository<User> mockRepository;
    private User mockUser;

    @BeforeEach
    void setUp() {
        mockHelper = mock(AppHelper.class);
        mockRepository = mock(FileRepository.class);
        userService = new UserService(mockHelper, mockRepository);
        mockUser = new User(); // Assuming User has a default constructor
    }

    @Test
    void testAddUser() {
        when(mockHelper.create()).thenReturn(mockUser);
        assertTrue(userService.add());
        verify(mockRepository).save(mockUser, "users");
    }

    @Test
    void testAddUserFailsWhenNull() {
        when(mockHelper.create()).thenReturn(null);
        assertFalse(userService.add());
        verify(mockRepository, never()).save(any(), anyString());
    }

    @Test
    void testEditUser() {
        assertFalse(userService.edit(mockUser));
    }

    @Test
    void testRemoveUser() {
        assertFalse(userService.remove(mockUser));
    }

    @Test
    void testPrintUsers() {
        List<User> users = new ArrayList<>();
        users.add(mockUser);
        when(mockRepository.load("users")).thenReturn(users);
        when(mockHelper.printList(users)).thenReturn(true);

        assertTrue(userService.print());
    }

    @Test
    void testListUsers() {
        List<User> users = new ArrayList<>();
        users.add(mockUser);
        when(mockRepository.load("users")).thenReturn(users);

        assertEquals(users, userService.list());
    }
}
