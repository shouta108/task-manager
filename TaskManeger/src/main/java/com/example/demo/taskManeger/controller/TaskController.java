package com.example.demo.taskManeger.controller;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.taskManeger.entity.ColorConfig;
import com.example.demo.taskManeger.entity.TaskManeger;
import com.example.demo.taskManeger.form.ConfigForm;
import com.example.demo.taskManeger.form.SortForm;
import com.example.demo.taskManeger.form.TaskForm;
import com.example.demo.taskManeger.repository.ColorRepository;
import com.example.demo.taskManeger.repository.SortRepository;
import com.example.demo.taskManeger.repository.TaskRepository;
import com.example.demo.taskManeger.service.ColorServiceImpl;
import com.example.demo.taskManeger.service.SortServiceImpl;
import com.example.demo.taskManeger.service.TaskServiceImpl;


@Controller
public class TaskController {
	
	@Autowired
	TaskRepository taskRepository;
	@Autowired
	ColorRepository colorConf;
	@Autowired
	SortRepository sortRepository;
	
	@Autowired
	TaskServiceImpl service;
	@Autowired
	ColorServiceImpl config;
	@Autowired
	SortServiceImpl sort;
	
	@GetMapping("show")
	public String showview(Model model) {
		model.addAttribute("taskList", service.sortDate());
		model.addAttribute("sort", sort.selectAll());
		model.addAttribute("colorList", config.setColor());
		return "view";
	}
	
	//登録機能
	@PostMapping("register")
	public String register(TaskForm f, @ModelAttribute TaskManeger task, Model model) {
		Calendar cal = Calendar.getInstance();
		TaskManeger registerTask = new TaskManeger(null, f.getTask(), f.getDate(), f.getSort(), false, new java.sql.Date(cal.getTimeInMillis()));
		task = taskRepository.save(registerTask);
		model.addAttribute("colorList", config.setColor());
		model.addAttribute("sort", sort.selectAll());
		model.addAttribute("taskList", service.sortDate());
		return "view";
	}
	
	//編集画面へ遷移
	@PostMapping("revise")
	public String showrevice(@ModelAttribute TaskManeger task, Model model, @RequestParam("id") String id) {
		model.addAttribute("colorList", config.setColor());
		model.addAttribute("sort", sort.selectAll());
		model.addAttribute("reviseTask", service.selectOneById(Integer.parseInt(id)));
		return "revise";
	}
	
	//編集内容を保存
	@PostMapping("confirm")
	public String confirm(TaskForm f ,@ModelAttribute TaskManeger task, Model model) {
		TaskManeger revisedTask = new TaskManeger(f.getId(), f.getTask(), f.getDate(), f.getSort(), f.getCompletion(), f.getRegistrationDate());
		revisedTask = taskRepository.save(revisedTask);
		model.addAttribute("colorList", config.setColor());
		model.addAttribute("sort", sort.selectAll());
		model.addAttribute("taskList", service.sortDate());
		return "view";
	}
	
	//削除機能
	@PostMapping("delete")
	public String deleteTask(@RequestParam("id") String id, Model model) {
		taskRepository.deleteById(Integer.parseInt(id));
		model.addAttribute("colorList", config.setColor());
		model.addAttribute("sort", sort.selectAll());
		model.addAttribute("taskList", service.sortDate());
		return "view";
	}
	
	//完了、未完了切り替え
	@PostMapping("completion")
	public String completion(@RequestParam("id") String id, Model model) {
		Iterable<TaskManeger> tasks = service.sortDate();
		for (TaskManeger task : tasks) {
			int taskId = task.getId();
			if (taskId == Integer.parseInt(id) && task.getCompletion() == false) {
				task.setCompletion(true);
				taskRepository.save(task);
				model.addAttribute("colorList", config.setColor());
				model.addAttribute("sort", sort.selectAll());
				model.addAttribute("taskList", service.sortDate());
			} else if (taskId == Integer.parseInt(id) && task.getCompletion() == true) {
				task.setCompletion(false);
				taskRepository.save(task);
				model.addAttribute("colorList", config.setColor());
				model.addAttribute("sort", sort.selectAll());
				model.addAttribute("taskList", service.sortDate());
			}
		}
		return "view";
	}
	
	//設定画面へ遷移
	@PostMapping("config")
	public String config(Model model) {
		model.addAttribute("config", config.get());
		model.addAttribute("sort", sort.selectAll());
		return "config";
	}
	
	//設定の編集
	@PostMapping("configRevise")
	public String configRevise(ConfigForm f, Model model) {
		ColorConfig colorConfig = new ColorConfig(f.getId(), f.getPhase1(), f.getPhase2(), f.getStartColor() , f.getMiddleColor(), f.getEndColor());
		config.updateConfig(colorConfig);
		model.addAttribute("colorList", config.setColor());
		model.addAttribute("sort", sort.selectAll());
		model.addAttribute("taskList", service.sortDate());
		return "view";
	}
	
	//ソートの編集
	@PostMapping("sortRevise")
	public String sortRevise(SortForm f, Model model) {
		sort.changeSort(f.getId(), f.getSort());
		model.addAttribute("colorList", config.setColor());
		model.addAttribute("sort", sort.selectAll());
		model.addAttribute("taskList", service.sortDate());
		return "view";
	}
	
}
