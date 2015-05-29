package com.university.equationsapp.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:/test-context.xml" })
public class ProblemCreatedControllerTests {

	//Test Data
	private static String URL = "/problemcreated.htm";
	private static String VIEW = "problemcreated";

	@InjectMocks
	ProblemCreatedController problemCreatedController;

	private MockMvc mockMvc;

	@Before
	public void setup() {

		// this must be called for the @Mock annotations above to be processed
		// and for the mock service to be injected into the controller under
		// test.
		MockitoAnnotations.initMocks(this);

		this.mockMvc = MockMvcBuilders.standaloneSetup(problemCreatedController).build();
	}

	@Test
	public void loadPage() throws Exception {
		mockMvc.perform(get(URL)).andExpect(status().isOk()).andExpect(view().name(VIEW));
	}

}
