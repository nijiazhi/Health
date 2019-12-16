package iscas.otcaix.di.controller;

import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-servlet.xml")
@WebAppConfiguration
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class RetrievalControllerTest {
	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = webAppContextSetup(this.wac).build();
	}

	@Test
	public void testRetrievalById() throws Exception {
		mockMvc.perform(get("/retrieval/id/lsq")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void testRetrievalByKeywords() {
		//
		fail("Not yet implemented");
	}

	@Test
	public void testRetrievalByCase() {
		//
		fail("Not yet implemented");
	}

}
