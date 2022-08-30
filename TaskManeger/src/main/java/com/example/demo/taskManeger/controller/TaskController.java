package com.example.demo.taskManeger.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.taskManeger.form.TaskForm;

@Controller
public class TaskController {
	
	@GetMapping("show")
	public String showview() {
		return "view";
	}
	
	@PostMapping("register")
	public String register(TaskForm f) {
		System.out.println(f.getTask());
		System.out.println(f.getSort());
		System.out.println(f.getDate());
//		TaskManeger task = new TaskManeger(null, f.getTask(), f.getDate(), f.getSort(), false);
//		task = repository.save(task);
		return "view";
	}
}
