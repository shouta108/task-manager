package com.example.demo.taskManeger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.taskManeger.entity.Sort;
import com.example.demo.taskManeger.entity.TaskManeger;
import com.example.demo.taskManeger.repository.SortRepository;
import com.example.demo.taskManeger.repository.TaskRepository;

@Service
@Transactional
public class SortServiceImpl implements SortServise {
	@Autowired
	SortRepository sortRepository;
	@Autowired
	TaskRepository taskRepository;
	@Autowired
	TaskServiceImpl taskService;
	
	@Override
	public Iterable<Sort> selectAll() {
		// TODO 自動生成されたメソッド・スタブ
		return sortRepository.findAll();
	}

	@Override
	public void changeSort(Integer integer, String str) {
		// TODO 自動生成されたメソッド・スタブ
		Iterable<Sort> sorts = sortRepository.findAll();
		Sort beforeSort = new Sort();
		for (Sort s : sorts) {
			if (s.getId() == integer) {
				beforeSort = new Sort(s.getId(), s.getSort());
				
			}
		}
		Sort sort = new Sort(integer, str);
		sortRepository.save(sort);
		Iterable<TaskManeger> tasks = taskService.selectAll();
		for (TaskManeger task : tasks) {
			System.out.println(beforeSort.getSort() + task.getSort());
			if (beforeSort.getSort().equals(task.getSort())) {
				task.setSort(str);
				taskService.updateTask(task);
			}
		}
	}

	

}
