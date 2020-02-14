package com.example.oprs.controller;

import com.example.oprs.pojo.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Sql("/data.sql")
public class TestAuthController {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void testSignUpGetRequest() throws Exception {

        mockMvc.perform(get("/signup"))
                .andExpect(view().name("register"));
    }

    @Test
    public void testSignUpPostRequest() throws Exception {
        String socialNumber= "123456789";
        String email = "Jim@gmail.com";
        String password = "EncodedPassword";
        String firstName = "Jim";
        String lastName = "kerry";
        String message = firstName + " you successfully registered";

        mockMvc.perform(post("/signup")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .param("socialNumber",socialNumber)
                .param("firstName", firstName)
                .param("lastName", lastName)
                .param("password", password)
                .param("email", email)
                .sessionAttr("user", new User())
        )
                .andExpect(view().name("message"))
                .andExpect(model().attribute("message", message));

    }

    @Test
    public void testLoginPost() throws Exception {
        String email = "James@gmail.com";
        String password = "EncodedPassword";

        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .param("username", email)
                .param("password", password)
        )
                .andExpect(redirectedUrl("/account"));

    }

    @Test
    public void testLoginPostWhitWrongUserNameOrPassword() throws Exception {
        String email = "wrong@gmail.com";
        String password = "WrongEncodedPassword";

        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .param("username", email)
                .param("password", password)
        )
                .andExpect(redirectedUrl("/login?error"));


    }


    @Test
    public void testIndex() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(view().name("index"));
    }


    @Test
    public void testSignIn() throws Exception {

        mockMvc.perform(get("/login"))
                .andExpect(view().name("login"));
    }


}
