package com.example.demo.taskManeger.service;

import java.util.List;

import com.example.demo.taskManeger.entity.ColorConfig;

public interface ColorService {
	//取得
	ColorConfig get();
	//段階変更
	void updatePhase(ColorConfig conf);
	//
	List<String> setColor();
}
