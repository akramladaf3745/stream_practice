package com.stream;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Employee {
	@Id
	private int id;
	private String name;
	private int referId;
	private double salary;
	private String designation;
	@JsonFormat(shape = Shape.STRING,pattern = "MM-dd-yyyy HH:mm:ss")
	private LocalDateTime createdDate;

}
