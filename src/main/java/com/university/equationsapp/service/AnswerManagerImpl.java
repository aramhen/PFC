package com.university.equationsapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.university.equationsapp.domain.Answer;
import com.university.equationsapp.repository.AnswerRepository;

@Service
public class AnswerManagerImpl implements AnswerManager {

	private static final long serialVersionUID = 1L;

	@Autowired
	private AnswerRepository answerRepository;

	public List<Answer> getAnswerList() {
		return answerRepository.getAnswerList();
	}

}
