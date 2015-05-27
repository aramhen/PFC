package com.university.equationsapp.web;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.university.equationsapp.common.constants.CommonConstants;
import com.university.equationsapp.common.utils.WebUtils;
import com.university.equationsapp.domain.Method;
import com.university.equationsapp.domain.Problem;
import com.university.equationsapp.domain.Teacher;
import com.university.equationsapp.service.ProblemManager;
import com.university.equationsapp.web.json.DTOToJsonObject;
import com.university.equationsapp.web.json.ListProblemJsonDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:/test-context.xml" })
public class ListProblemControllerTests {

	//Test Data
	private static String URL = "/listproblem.htm";
	private static String VIEW = "listproblem";
	private static String URL_PROBLEM_PAGINATION = "/listproblempagination.htm";

	@Mock
	ProblemManager problemManager;

	@InjectMocks
	ListProblemController listProblemController;

	private MockMvc mockMvc;

	List<Problem> pList;

	@Before
	public void setup() {

		// this must be called for the @Mock annotations above to be processed
		// and for the mock service to be injected into the controller under
		// test.
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(listProblemController).build();

		//Set up data test
		pList = new ArrayList<Problem>();
		Teacher t = new Teacher();
		t.setName("profesor");
		Method m = new Method();
		m.setName("metodo");
		Problem p = new Problem();

		p.setTitle("titulo1");
		p.setMethodRef(m);
		p.setTeacherRef(t);
		p.setEndDate(new Date());
		p.setInitDate(new Date());
		p.setEquations("x|y");
		pList.add(p);
		p = new Problem();
		p.setTitle("titulo2");
		p.setMethodRef(m);
		p.setTeacherRef(t);
		p.setEndDate(new Date());
		p.setInitDate(new Date());
		p.setEquations("x|y");
		pList.add(p);
		p = new Problem();
		p.setTitle("titulo3");
		p.setMethodRef(m);
		p.setTeacherRef(t);
		p.setEndDate(new Date());
		p.setInitDate(new Date());
		p.setEquations("x|y");
		pList.add(p);

	}

	@Test
	public void loadPage() throws Exception {
		mockMvc.perform(get(URL)).andExpect(status().isOk()).andExpect(view().name(VIEW));
	}

	@Test
	public void getlistproblem() throws Exception {

		when(problemManager.getProblemList()).thenReturn(pList);
		ResultActions ra = mockMvc.perform(get(URL_PROBLEM_PAGINATION)).andExpect(status().isOk());

		List<ListProblemJsonDTO> problemsList = getListProblemDTO(pList);

		int problemSize = problemsList.size();
		DTOToJsonObject<ListProblemJsonDTO> problemJsonObject = new DTOToJsonObject<ListProblemJsonDTO>();
		problemJsonObject.setiTotalDisplayRecords(problemSize);
		problemJsonObject.setiTotalRecords(problemSize);
		problemJsonObject.setAaData(problemsList);

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json2 = gson.toJson(problemJsonObject);
		String res = ra.andReturn().getResponse().getContentAsString();

		//We test that the json of the answer is correct
		assertEquals(json2, res);
	}

	private List<ListProblemJsonDTO> getListProblemDTO(List<Problem> problemList) {
		ListProblemJsonDTO tmp = new ListProblemJsonDTO();
		Problem node;
		List<ListProblemJsonDTO> tmpList = new ArrayList<ListProblemJsonDTO>();

		SimpleDateFormat format = new SimpleDateFormat(CommonConstants.DATE_FORMAT, new Locale(
				CommonConstants.LOCALE_ES));
		Iterator<Problem> itProblem = problemList.iterator();

		while (itProblem.hasNext()) {
			node = itProblem.next();
			tmp = new ListProblemJsonDTO();

			tmp.setIdProblem(String.valueOf(node.getIdProblems()));
			tmp.setProblemTitle(node.getTitle());
			tmp.setTeacherName(node.getTeacherRef().getName());
			tmp.setMethodName(node.getMethodRef().getName());
			tmp.setNumVariables(String.valueOf(node.getNumVariables()));
			tmp.setUniqueAnswer(String.valueOf(node.isUniqueAnswer()));
			tmp.setInitDate(format.format(node.getInitDate()));
			tmp.setEndDate(format.format(node.getEndDate()));
			tmp.setEquations(WebUtils.equationsAndSolutionToMathJax(node.getEquations()));
			if (node.getSolution() != null) {
				tmp.setSolution(WebUtils.equationsAndSolutionToMathJax(node.getSolution()));
			}
			tmpList.add(tmp);
		}
		return tmpList;
	}
}
