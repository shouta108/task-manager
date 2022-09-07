package com.example.demo.taskManeger.form;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Table(name="color")
public class ConfigForm {
	@Column(value ="phase1")
	private Integer phase1;
	@Column(value ="phase2")
	private Integer phase2;
	@Column(value ="startColor")
	private String startColor;
	@Column(value ="middleColr")
	private String middleColor;
	@Column(value ="endColor")
	private String endColor;
}
