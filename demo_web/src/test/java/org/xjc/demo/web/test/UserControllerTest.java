package org.xjc.demo.web.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.xjc.demo.bean.User;
import org.xjc.demo.controller.UserController;

import java.util.ArrayList;

import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by xjc on 2018-12-4.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserController userController;

    @Test
    public void testUserController() throws Exception {
        given(this.userController.getUserList()).willReturn(new ArrayList<>());

        this.mvc.perform(get("/users/").accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk()).andExpect(content().string("[]"));

        when(this.userController.postUser(any(User.class))).thenReturn("success");
        this.mvc.perform(post("/users/"))
                .andExpect(status().isOk()).andExpect(content().string("success"));

    }

    public void testPut() throws Exception{

    }
}