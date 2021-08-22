package org.velichko.finalproject.logic.service.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.velichko.finalproject.logic.dao.UserDao;
import org.velichko.finalproject.logic.dao.impl.UserDaoImpl;
import org.velichko.finalproject.logic.entity.User;
import org.velichko.finalproject.logic.entity.type.UserRole;
import org.velichko.finalproject.logic.entity.type.UserStatus;
import org.velichko.finalproject.logic.exception.DaoException;
import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.logic.service.UserService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class UserServiceImplTest {
    private UserService toTest;

    @Mock
    private UserDaoImpl userDao;

    @BeforeEach
    public void init() throws DaoException {
        User user1 = new User("Ivan", "Velichko", "login", "email@gmail.com", UserRole.STUDENT, UserStatus.ACTIVE);
        User user2 = new User("Ivan2", "Velichko2", "login2", "email2@gmail.com", UserRole.STUDENT, UserStatus.ACTIVE);
        User user3 = new User("Ivan3", "Velichko3", "login3", "email3@gmail.com", UserRole.STUDENT, UserStatus.ACTIVE);
        MockitoAnnotations.openMocks(this);
        when(userDao.findAll()).thenReturn(List.of(user1, user2, user3));
        when(userDao.findByPage(eq(1))).thenReturn(List.of(user1, user2, user3));
        when(userDao.findByPage(eq(2))).thenReturn(List.of(user1));
        toTest = new UserServiceImpl(userDao);
    }

    @Test
    void readAll() throws ServiceException {
        int actualSize = toTest.readAll().size();
        assertEquals(3, actualSize);
    }

    @Test
    void readAllAndExpectedExceptionWhenDBisDown() throws DaoException {
        when(userDao.findAll()).thenThrow(DaoException.class);
        assertThrows(ServiceException.class, () -> {
            toTest.readAll();
        });
    }

    @Test
    void readByPageOne() throws ServiceException {
        int pageToDisplay = 1;
        List<User> users = toTest.readByPage(pageToDisplay);
        int expectedSize = 3;
        int actualSize = users.size();
        assertEquals(expectedSize, actualSize);
    }

    @Test
    void readByPageTwo() throws ServiceException {
        int pageToDisplay = 2;
        List<User> users = toTest.readByPage(pageToDisplay);
        int expectedSize = 1;
        int actualSize = users.size();
        assertEquals(expectedSize, actualSize);
    }

    @Test
    void getUserCount() {
    }

    @Test
    void createNewUser() {
    }

    @Test
    void testCreateNewUser() {
    }

    @Test
    void changeUserGit() {
    }

    @Test
    void isLoginUnique() {
    }

    @Test
    void isEmailUnique() {
    }

    @Test
    void isGitLinkUnique() {
    }

    @Test
    void findUserByLoginAndPassword() {
    }

    @Test
    void findUserById() {
    }

    @Test
    void changeUserRole() {
    }

    @Test
    void changeUserStatus() {
    }

    @Test
    void getUserByRegistrationKey() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void changeUserImage() {
    }
}