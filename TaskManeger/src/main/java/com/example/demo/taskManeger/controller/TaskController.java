package com.example.demo.taskManeger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.taskManeger.entity.TaskManeger;
import com.example.demo.taskManeger.form.TaskForm;
import com.example.demo.taskManeger.repository.TaskRepository;
import com.example.demo.taskManeger.service.TaskServiceImpl;

@Controller
public class TaskController {
	
	@Autowired
	TaskRepository repository;
	@Autowired
	TaskServiceImpl service;
	
	@GetMapping("show")
	public String showview(@ModelAttribute TaskManeger task, Model model) {
		model.addAttribute("taskList", service.selectAll());
		return "view";
	}
	
	@PostMapping("register")
	public String register(TaskForm f,@ModelAttribute TaskManeger taskEntity, Model model) {
		TaskManeger task = new TaskManeger(null, f.getTask(), f.getDate(), f.getSort(), false);
		task = repository.save(task);
		model.addAttribute("taskList", service.selectAll());
		return "view";
	}
	
	@PostMapping("delete")
	public String deleteTask(@RequestParam("id") String id, Model model) {
		repository.deleteById(Integer.parseInt(id));
		model.addAttribute("taskList", service.selectAll());
		return "view";
	}
	
	@PostMapping("completion")
	public String completion(TaskForm f, @RequestParam("id") String id, Model model) {
		Iterable<TaskManeger> tasks = service.selectAll();
		for (TaskManeger task : tasks) {
			int taskId = task.getId();
			if (taskId == Integer.parseInt(id) && task.getCompletion() == false) {
				task.setCompletion(true);
				repository.save(task);
				model.addAttribute("taskList", service.selectAll());
			} else if (taskId == Integer.parseInt(id) && task.getCompletion() == true) {
				task.setCompletion(false);
				repository.save(task);
				model.addAttribute("taskList", service.selectAll());
			}
		}
		return "view";
		
	}
	
}
