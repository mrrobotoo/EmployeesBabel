package com.babelgroup.employees.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@RequiredArgsConstructor
public class ResponseDto <T> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private final String msg;
    private final List<T> employees;
}
