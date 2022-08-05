package com.User.Controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
@TestMethodOrder(OrderAnnotation.class)
public class UserControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	@Order(1)
	public void getUsersTest() throws Exception {

		MvcResult result = mvc.perform(get("/users")).andReturn();
		MockHttpServletResponse resp = result.getResponse();
		assertNotNull(resp.getContentAsString());
		assertEquals(HttpStatus.OK.value(), resp.getStatus());
	}

	@Test
	@Order(2)
	public void saveUserTest() throws Exception {

		MvcResult result = mvc.perform(post("/save").header("Content-Type", "application/json")
				.content("{\"firstName\":\"kamal\",\"lastName\":\"Parsad\",\"age\":23}")).andReturn();
		MockHttpServletResponse resp = result.getResponse();
		assertNotNull(resp.getContentAsString());
		assertEquals(HttpStatus.OK.value(), resp.getStatus());

	}

// User Exist we need to update user
	@Test
	@Order(3)
	public void updateUserTest() throws Exception {
		MvcResult result = mvc.perform(put("/update/1").contentType(MediaType.APPLICATION_JSON)
		.content("{\"firstName\":\"kamal1\",\"lastName\":\"Parsad1\",\"age\":32}")).andReturn();
		MockHttpServletResponse resp = result.getResponse();
		assertNotNull(resp.getContentAsString());
		assertEquals(HttpStatus.OK.value(), resp.getStatus());
	}

	@Test
	@Order(4)
	public void deleteUserTest() throws Exception {
		MvcResult result = mvc.perform(delete("/delete/1")).andReturn();
		MockHttpServletResponse resp = result.getResponse();
		assertNotNull(resp.getContentAsString());
		assertEquals(200, resp.getStatus());

	}

}
