package com.uxpsystems;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uxpsystems.assignment.dao.User;
import com.uxpsystems.assignment.dao.UserBuilder;
import com.uxpsystems.assignment.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.stubbing.answers.DoesNothing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    protected UserService userServiceMock;

    @Before
    public void init(){
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("admin", "admin", authorities));

        userServiceMock = org.mockito.Mockito.mock(UserService.class);
    }

    @Test
    public void findAllUsersShouldReturnALlUsers() throws Exception {
        User user1 = new UserBuilder()
                .id(1L)
                .username("Jayden")
                .password("Jayden")
                .status("Activated")
                .build();
        User user2 = new UserBuilder()
                .id(2L)
                .username("Zessie")
                .password("Zessie")
                .status("Activated")
                .build();
        User user3 = new UserBuilder()
                .id(3L)
                .username("Thron")
                .password("Thron")
                .status("DeActivated")
                .build();
        when(userServiceMock.findAllUsers()).thenReturn(Arrays.asList(user1, user2, user3));
        mockMvc.perform(get("/user/")
                .with(httpBasic("admin", "admin")))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$[0].username").value("Jayden"))
                .andExpect(jsonPath("$[0].password").value("Jayden"))
                .andExpect(jsonPath("$[0].status").value("Activated"))
                .andExpect(jsonPath("$[1].username").value("Zessie"))
                .andExpect(jsonPath("$[1].password").value("Zessie"))
                .andExpect(jsonPath("$[1].status").value("Activated"))
                .andExpect(jsonPath("$[2].username").value("Thorn"))
                .andExpect(jsonPath("$[2].password").value("Thorn"))
                .andExpect(jsonPath("$[2].status").value("DeActivated"));
    }

    @Test
    public void createUserShouldReturnSuccess() throws Exception {
        User user = new UserBuilder()
                .id(4L)
                .username("Hulk")
                .password("Hulk")
                .status("Activated")
                .build();
        Mockito.when(userServiceMock.saveUser(user)).thenReturn(user);
        String json = mapper.writeValueAsString(user);
        mockMvc.perform(post("/user/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .with(httpBasic("admin", "admin")))
                .andExpect(status().isCreated());
    }

    @Test
    public void updateUserReturnSccuess() throws Exception {
        User user = new UserBuilder()
                .id(1L)
                .username("Jayden")
                .password("Jayden")
                .status("DeActivated")
                .build();
        when(userServiceMock.findById(user.getId())).thenReturn(user);
        doNothing().when(userServiceMock).updateUser(user);
        String json = mapper.writeValueAsString(user);
        mockMvc.perform(put("/user/{id}", user.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .with(httpBasic("admin", "admin")))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteUserShouldReturnSuccess() throws Exception {
        User user = new UserBuilder()
                .id(1L)
                .username("Jayden")
                .password("Jayden")
                .status("Activated")
                .build();
        when(userServiceMock.findById(user.getId())).thenReturn(user);
        doNothing().when(userServiceMock).deleteUserById(user.getId());
        mockMvc.perform(delete("/user/{id}", user.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .with(httpBasic("admin", "admin")))
                .andExpect(status().isOk());
    }
}

