package com.university.equationsapp.common.utils;

import java.util.Arrays;
import java.util.List;

import com.university.equationsapp.common.constants.CommonConstants;

public final class WebUtils {

	/**
	 * Transform the equations read from DB into MathJax output language to render it on the html
	 * 
	 * @param equations
	 * @return
	 */
	public final static String equationsAndSolutionToMathJax(String equations) {

		List<String> equationsList = Arrays.asList(equations.split(CommonConstants.SEPARATOR_FOR_SPLIT));
		StringBuilder sb = new StringBuilder();

		if (equationsList.size() == 1) {
			sb.append("<div>$ ").append(equationsList.get(0)).append(" $</div>");

		} else if (equationsList.size() == 2) {
			sb.append("<div style='margin-bottom: 1%;'>$ ").append(equationsList.get(0)).append(" $</div>")
					.append("<div>$ ").append(equationsList.get(1)).append(" $</div>");

		} else if (equationsList.size() == 3) {
			sb.append("<div style='margin-bottom: 1%;'>$ ").append(equationsList.get(0)).append(" $</div>")
					.append("<div style='margin-bottom: 1%;'>$ ").append(equationsList.get(1)).append(" $</div>")
					.append("<div>$ ").append(equationsList.get(2)).append(" $</div>");
		}
		return sb.toString();
	}

}
