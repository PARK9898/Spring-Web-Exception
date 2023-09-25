package com.example.exception.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
public class RestApiController {

	@GetMapping(path = "")
	public void hello() {
		var list = List.of("hello");

		var element = list.get(1);
		//하나하나 try-catch 문을 사용한느것은 비효율적음
		log.info("element : {}",element);
	}
}
