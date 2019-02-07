package com.yoojin282;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class WebConfigTest {
	@Autowired
	private MockMvc mvc;
	
	@Test
	public void testIndex() throws Exception {
		mvc.perform(get("/"))
			.andExpect(status().isOk())
			.andExpect(view().name("index"));
	}
	
	@Test
	public void testSecured_notLoggedIn() throws Exception {
		mvc.perform(get("/secured"))
			.andExpect(status().is3xxRedirection());
	}
	
	@Test
	@WithMockUser(username="abcd")
	public void testSecured_loggedIn() throws Exception {
		mvc.perform(get("/secured"))
			.andExpect(status().isOk())
			.andExpect(view().name("secured"));
	}

}
