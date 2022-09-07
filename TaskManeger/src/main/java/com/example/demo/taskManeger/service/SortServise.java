package com.example.demo.taskManeger.service;

import com.example.demo.taskManeger.entity.Sort;

public interface SortServise {
	//全件取得
	Iterable<Sort> selectAll();
	//変更
	void changeSort(Integer integer, String str);
}
