package com.rays.ctl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.ORSResponse;
import com.rays.dto.TestDTO;

@RestController
@RequestMapping(value = "Ors")
public class ORSCtl {

	@GetMapping("getDto")
	public ORSResponse display() {

		ORSResponse res = new ORSResponse();

		TestDTO dto = new TestDTO();

		dto.setId(1L);
		dto.setFirstName("Ram");
		dto.setLastName("Sharma");
		dto.setLoginId("ram@gmail.com");
		dto.setPassword("ram123");

		res.addData(dto);
		// res.setSuccess(true);

		return res;

	}

	@GetMapping("getList")
	public ORSResponse display1() {

		List list = new ArrayList();
		ORSResponse res = new ORSResponse();

		TestDTO dto = new TestDTO();

		dto.setId(1L);
		dto.setFirstName("Ram");
		dto.setLastName("Sharma");
		dto.setLoginId("ram@gmail.com");
		dto.setPassword("ram123");

		TestDTO dto1 = new TestDTO();

		dto1.setId(2L);
		dto1.setFirstName("Shyam");
		dto1.setLastName("Yadav");
		dto1.setLoginId("shyam@gmail.com");
		dto1.setPassword("shyam123");

		list.add(dto);
		list.add(dto1);

		res.addData(list);

		return res;

	}

	@GetMapping("getMessage")
	public ORSResponse display2() {

		ORSResponse res = new ORSResponse();

		res.addMessage("invalid login or password");

		return res;

	}

	@GetMapping("getInputError")
	public ORSResponse display3() {

		ORSResponse res = new ORSResponse();

		Map<String, String> error = new HashMap<String, String>();

		error.put("firstName", "firstName is required");
		error.put("lastName", "lastName is required");
		error.put("loginId", "loginId is required");
		error.put("password", "password is required");

		res.addInputError(error);

		return res;

	}

	@GetMapping("preload")
	public ORSResponse display4() {

		ORSResponse res = new ORSResponse();

		List userList = new ArrayList();

		TestDTO dto = new TestDTO();

		dto.setId(1L);
		dto.setFirstName("Ram");
		dto.setLastName("Sharma");
		dto.setLoginId("ram@gmail.com");
		dto.setPassword("ram123");

		TestDTO dto1 = new TestDTO();

		dto1.setId(2L);
		dto1.setFirstName("Shyam");
		dto1.setLastName("Yadav");
		dto1.setLoginId("shyam@gmail.com");
		dto1.setPassword("shyam123");

		userList.add(dto);
		userList.add(dto1);

		res.addResult("userList", userList);

		return res;

	}

}