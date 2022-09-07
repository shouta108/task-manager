package com.example.demo.taskManeger.form;

import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Table(name="sort")
public class SortForm {
	private Integer id;
	
	private String sort;
}
