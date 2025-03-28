package com.babelgroup.employees.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Generic Data Transfer Object (DTO) used to standardize API responses.
 * This class includes a message and optionally data of type {@code T}.
 * 
 * @param <T> The type of data contained in the response.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@RequiredArgsConstructor
public class ResponseDto <T> implements Serializable {
    /** Serial version UID for ensuring compatibility during serialization. */
	private static final long serialVersionUID = 1L;
    /** Message describing the response status or result. */
	private  String msg;
    /** Generic data field that can contain any type of response data. */
    private  T data;
}
