package com.example.exception.exception;

import java.util.NoSuchElementException;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.exception.controller.RestApiBController;
import com.example.exception.controller.RestApiController;
import com.example.exception.model.Api;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice (basePackageClasses = {RestApiBController.class, RestApiController.class})// RestApi가 사용되는곳에 예외를 감지한다
@Order(1)
public class RestApiExceptionHandler {
	@ExceptionHandler(value = {Exception.class}) // 어떠한 예외를 받을것인가가
	public ResponseEntity exception(Exception e) {
		log.error("RestApiExceptionHandler", e);// 여기서 잡았다고 기록 남
		return ResponseEntity.status(200).build();
	}
	@ExceptionHandler(value = {IndexOutOfBoundsException.class})
	public ResponseEntity outOfBound(IndexOutOfBoundsException e) {
		log.error("IndexOutOfBoundsException", e);// 여기서 잡았다고 기록 남
		return ResponseEntity.status(200).build();
	}
	@ExceptionHandler(value = {NoSuchElementException.class})
	public ResponseEntity<Api> noSuchElement(NoSuchElementException e) {
		log.error("NoSuchElementException",e);

		var response = Api.builder()
			.resultMessage(String.valueOf(HttpStatus.NOT_FOUND.value()))
			.resultCode(HttpStatus.NOT_FOUND.getReasonPhrase())
			.build();
		return ResponseEntity
			.status(HttpStatus.NOT_FOUND)
			.body(response)
			;
	}
}
