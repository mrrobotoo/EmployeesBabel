package com.babelgroup.employees.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@RequiredArgsConstructor
public class ResponseDto <T> implements Serializable {
	private static final long serialVersionUID = 1L;
	private  String msg;
    private  T data;
}
