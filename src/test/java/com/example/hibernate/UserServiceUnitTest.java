package com.example.hibernate;

import com.example.hibernate.entity.User;
import com.example.hibernate.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceUnitTest {
    @Autowired
    private UserService userService;

    @Test
    public void whenApplicationStarts_thenHibernateCreatesInitialRecords() {

        List<User> users = userService.list();

        Assert.assertEquals(users.size(), 3);
    }
}
