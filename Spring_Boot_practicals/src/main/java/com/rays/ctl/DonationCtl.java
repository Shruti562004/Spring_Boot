package com.rays.ctl;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.common.ORSResponse;
import com.rays.dto.DonationDTO;
import com.rays.form.DonationForm;
import com.rays.service.DonationService;
import org.springframework.validation.BindingResult;
@RestController
@RequestMapping(value="Donation") 
public class DonationCtl extends BaseCtl {
	
	public DonationService service;
	
	@PostMapping("save")
	public ORSResponse save(@RequestBody @Valid DonationForm form, BindingResult bindingResult) {

		ORSResponse res = new ORSResponse();

		res = validate(bindingResult);

		if (res.isSuccess() == false) {
			return res;
		}

		DonationDTO dto = (DonationDTO) form.getDto();

		Long id = service.add(dto);

		if (id != null && id > 0) {
			res.addData(dto);
			res.addMessage("Donation saved successfully");
		} else {
			res.addMessage("error in Donation add");
		}

		return res;

	}

}
