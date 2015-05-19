package com.university.equationsapp.web;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@WebAppConfiguration("WebConfig") 
@RunWith(MockitoJUnitRunner.class)
public class ProblemCreatedControllerTest {

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = standaloneSetup(new ProblemCreatedController()).setViewResolvers(viewResolver()).build();

	}

	@Test
	public void testProblemCreatedPage() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/problemcreated.htm")).andExpect(MockMvcResultMatchers.status().isOk());
	}

	private ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
}
