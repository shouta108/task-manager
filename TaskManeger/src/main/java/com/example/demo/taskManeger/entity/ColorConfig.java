package com.example.demo.taskManeger.entity;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="color")
public class ColorConfig {
	@Column(value ="phase1")
	private Integer phase1;
	@Column(value ="phase2")
	private Integer phase2;
	@Column(value ="startColor")
	private String startColor;
	@Column(value ="middleColor")
	private String middleColor;
	@Column(value ="endColor")
	private String endColor;
}
