package com.university.equationsapp.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.university.equationsapp.domain.Method;
import com.university.equationsapp.repository.MethodRepository;

@Service
public class MethodManagerImpl implements MethodManager {

	private static final long serialVersionUID = 1L;

	@Autowired
	private MethodRepository methodRepository;

	public Method getMethod(String name) {
		return methodRepository.findByName(name);
	}

	public List<Method> getMethods2() {
		List<Method> tmpList = new ArrayList<Method>();
		Iterable<Method> tmpIterable = methodRepository.findAll();

		if (tmpIterable != null) {
			Iterator<Method> tmpIterator = tmpIterable.iterator();
			while (tmpIterator.hasNext()) {
				tmpList.add(tmpIterator.next());
			}
		}
		return tmpList;
	}

	public void setMethodRepository(MethodRepository methodRepository) {
		this.methodRepository = methodRepository;
	}

	public List<Method> getMethodList() {
		return this.methodRepository.getMethodList();
	}

}
