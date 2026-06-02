package com.rays.ctl;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.common.ORSResponse;
import com.rays.dto.RoleDTO;
import com.rays.form.RoleForm;
import com.rays.service.RoleService;

@RestController
@RequestMapping(value = "Role")
public class RoleCtl extends BaseCtl {

	@Autowired
	RoleService service;

	@PostMapping("save")
	public ORSResponse save(@RequestBody @Valid RoleForm form, BindingResult bindingResult) {

		ORSResponse res = new ORSResponse();

		res = validate(bindingResult);

		if (res.isSuccess() == false) {
			return res;
		}

		RoleDTO dto = (RoleDTO) form.getDto();

		Long id = service.add(dto);

		if (id != null && id > 0) {
			res.addData(dto);
			res.addMessage("role saved successfully");
		} else {
			res.addMessage("error in role add");
		}

		return res;

	}

	@PostMapping("update")
	public ORSResponse update(@RequestBody @Valid RoleForm form, BindingResult bindingResult) {

		ORSResponse res = new ORSResponse();

		res = validate(bindingResult);

		if (res.isSuccess() == false) {
			return res;
		}

		RoleDTO dto = (RoleDTO) form.getDto();

		service.update(dto);

		res.addData(dto);
		res.addMessage("role updated successfully");

		return res;

	}

	@PostMapping("delete/{ids}")
	public ORSResponse update(@PathVariable Long[] ids) {

		ORSResponse res = new ORSResponse();

		if (ids != null && ids.length > 0) {
			for (Long id : ids) {
				service.delete(id);
				res.addMessage("record deleted successfully");
				res.setSuccess(true);
			}
		}

		return res;

	}

	@GetMapping("get/{id}")
	public ORSResponse update(@PathVariable Long id) {

		ORSResponse res = new ORSResponse();
		RoleDTO dto = new RoleDTO();
		dto = service.findById(id);
		if (dto != null) {
			res.addData(dto);
			res.setSuccess(true);
		} else {
			res.addMessage("record not found");
		}

		return res;

	}

}