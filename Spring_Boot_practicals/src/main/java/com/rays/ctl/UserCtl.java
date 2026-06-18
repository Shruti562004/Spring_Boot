package com.rays.ctl;

import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rays.common.BaseCtl;
import com.rays.common.ORSResponse;
import com.rays.dto.AttachmentDTO;
import com.rays.dto.UserDTO;
import com.rays.form.UserForm;
import com.rays.service.AttachmentService;
import com.rays.service.RoleService;
import com.rays.service.UserService;

@RestController
@RequestMapping(value = "User")
public class UserCtl extends BaseCtl {

	@Autowired
	public 	UserService service;
	
	@Autowired
	public RoleService roleService;
	
	@Autowired
	public AttachmentService attachmentService;
	
	@GetMapping("preload")
	public ORSResponse preload() {

		ORSResponse res = new ORSResponse();

		List roleList = roleService.search(null, 0, 0);

		res.addResult("roleList", roleList);
		res.setSuccess(true);

		return res;

	}
	
	

	@PostMapping("save")
	public ORSResponse save(@RequestBody @Valid UserForm form, BindingResult bindingResult) {

		ORSResponse res = new ORSResponse();

		res = validate(bindingResult);

		if (res.isSuccess() == false) {
			return res;
		}

		UserDTO dto = (UserDTO) form.getDto();

		Long id = service.add(dto);

		if (id != null && id > 0) {
			res.addData(dto);
			res.addMessage("User saved successfully");
		} else {
			res.addMessage("error in User add");
		}

		return res;

	}

	@PostMapping("update")
	public ORSResponse update(@RequestBody @Valid UserForm form, BindingResult bindingResult) {

		ORSResponse res = new ORSResponse();

		res = validate(bindingResult);

		if (res.isSuccess() == false) {
			return res;
		}

		UserDTO dto = (UserDTO) form.getDto();

		service.update(dto);

		res.addData(dto);
		res.addMessage("User updated successfully");

		return res;

	}

	@GetMapping("delete/{ids}")
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
		UserDTO dto = new UserDTO();
		dto = service.findById(id);
		if (dto != null) {
			res.addData(dto);
			res.setSuccess(true);
		} else {
			res.addMessage("record not foud");
		}

		return res;

	}
	
	
	@RequestMapping(value = "search/{pageNo}", method = { RequestMethod.POST, RequestMethod.GET })
	public ORSResponse search(@RequestBody UserForm form, @PathVariable int pageNo) {

		UserDTO dto = (UserDTO) form.getDto();
		ORSResponse res = new ORSResponse();

		int pageSize = 5;

		List<UserDTO> list = service.search(dto, pageNo, pageSize);

		if (list != null) {
			res.addData(list);
			res.setSuccess(true);
		} else {
			res.addMessage("record not found");
		}

		return res;

	}
	

	@PostMapping("/profilePic/{userId}")
	public ORSResponse uploadPic(@PathVariable Long userId, @RequestParam("file") MultipartFile file) {

		AttachmentDTO attachmentDto = new AttachmentDTO(file);

		attachmentDto.setDescription("profile pic");

		attachmentDto.setUserId(userId);

		UserDTO userDto = service.findById(userId);

		if (userDto.getImageId() != null && userDto.getImageId() > 0) {
			attachmentDto.setId(userDto.getImageId());
		}

		Long imageId = attachmentService.save(attachmentDto);

		if (userDto.getImageId() == null) {
			userDto.setImageId(imageId);
			service.update(userDto);
		}

		ORSResponse res = new ORSResponse();
		res.addResult("imageId", imageId);
		res.setSuccess(true);

		return res;
	}

	@GetMapping("/profilePic/{userId}")
	public void downloadPic(@PathVariable Long userId, HttpServletResponse response) {

		try {

			UserDTO userDto = service.findById(userId);

			AttachmentDTO attachmentDTO = null;

			if (userDto != null) {
				attachmentDTO = attachmentService.findById(userDto.getImageId());
			}

			if (attachmentDTO != null) {
				
				response.setContentType(attachmentDTO.getType());
				OutputStream out = response.getOutputStream();
				out.write(attachmentDTO.getDoc());
				out.close();

			} else {
				response.getWriter().write("ERROR: File not found");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	


}