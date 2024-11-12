package org.lopertut.appHelpers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.lopertut.models.User;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserAppHelperTest {

    private UserAppHelper userAppHelper;

    @BeforeEach
    void setUp() {
        userAppHelper = spy(UserAppHelper.class);
    }

    @Test
    void testCreateUser() {
        // Correct order of input mocks to match expected calls
        doReturn("John").doReturn("Doe").doReturn("123456789").when(userAppHelper).getString();

        User user = userAppHelper.create();

        assertNotNull(user);
        assertEquals("John", user.getFirstname());
        assertEquals("Doe", user.getLastname());
        assertEquals("123456789", user.getPhone());
    }

    @Test
    void testPrintListEmptyUsers() {
        List<User> emptyUsers = new ArrayList<>();
        assertFalse(userAppHelper.printList(emptyUsers));
    }

    @Test
    void testPrintListNonEmptyUsers() {
        // Assuming `printList` should return true if the list is not empty.
        User user = new User();
        user.setFirstname("John");
        user.setLastname("Doe");
        user.setPhone("123456789");

        List<User> users = new ArrayList<>();
        users.add(user);

        assertTrue(userAppHelper.printList(users));
    }
}
