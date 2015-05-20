package com.university.equationsapp.web;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.university.equationsapp.domain.Teacher;
import com.university.equationsapp.service.TeacherManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:/test-context.xml" })
public class HomeControllerTest {

	//Test Data
	private static String TEACHER_NAME_PARAM_VALUE = "Nombre_profesor";
	private static String TEACHER_NAME_PARAM = "teacherName";
	private static String URL = "/home.htm";
	private static String VIEW = "home";
	private static String MODEL = "model";

	@Mock
	TeacherManager teacherManager;

	@InjectMocks
	HomeController homeController;

	private MockMvc mockMvc;
	Teacher t;

	@Before
	public void setup() {

		// this must be called for the @Mock annotations above to be processed
		// and for the mock service to be injected into the controller under
		// test.
		MockitoAnnotations.initMocks(this);

		this.mockMvc = MockMvcBuilders.standaloneSetup(homeController).build();

		t = new Teacher();
		t.setName(TEACHER_NAME_PARAM_VALUE);
	}

	@Test
	public void loadPage() throws Exception {
		when(teacherManager.findByidTeachers(7)).thenReturn(t);
		ResultActions ra = mockMvc.perform(get(URL)).andExpect(status().isOk()).andExpect(view().name(VIEW));
		HashMap<String, String> params = (HashMap<String, String>) ra.andReturn().getModelAndView().getModel()
				.get(MODEL);
		Assert.assertTrue(params.get(TEACHER_NAME_PARAM).equals(TEACHER_NAME_PARAM_VALUE));
	}

}
