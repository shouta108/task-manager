package com.example.demo.taskManeger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.taskManeger.entity.ColorConfig;
import com.example.demo.taskManeger.repository.ColorRepository;

@Service
@Transactional
public class ColorServiceImpl implements ColorService {

	@Autowired
	ColorRepository repository;
	
	

	@Override
	public void updatePhase(ColorConfig conf) {
		// TODO 自動生成されたメソッド・スタブ
		repository.save(conf);
	}



	@Override
	public ColorConfig get() {
		// TODO 自動生成されたメソッド・スタブ
		Iterable<ColorConfig> config = repository.findAll();
		ColorConfig conf = null;
		for (ColorConfig con : config) {
			conf = con;
		}
		return conf;
	}
}
