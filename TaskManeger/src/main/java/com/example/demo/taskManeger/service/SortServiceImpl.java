package com.example.demo.taskManeger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.taskManeger.entity.Sort;
import com.example.demo.taskManeger.repository.SortRepository;

@Service
@Transactional
public class SortServiceImpl implements SortServise {
	@Autowired
	SortRepository repository;
	
	@Override
	public Iterable<Sort> selectAll() {
		// TODO 自動生成されたメソッド・スタブ
		return repository.findAll();
	}

	@Override
	public void changeSort(Sort sort) {
		// TODO 自動生成されたメソッド・スタブ
		repository.save(sort);
	}

	

}
