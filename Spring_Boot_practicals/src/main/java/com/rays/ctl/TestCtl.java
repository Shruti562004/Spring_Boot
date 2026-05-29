package com.rays.ctl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "Test")
public class TestCtl {

	@GetMapping
	public String display() {
		return "in display method";
	}

	@PostMapping
	public String submit() {
		return "in submit method";
	}

	@GetMapping("get")
	public String display1() {
		return "in get method";
	}

	@PostMapping("save")
	public String sumbmit1() {
		return "in save method";
	}

	@RequestMapping(value = "search", method = { RequestMethod.POST, RequestMethod.GET })
	public String demo() {
		return "in demo method";
	}

}