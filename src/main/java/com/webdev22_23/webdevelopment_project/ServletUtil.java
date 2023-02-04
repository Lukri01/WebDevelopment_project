package com.webdev22_23.webdevelopment_project;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ServletUtil {
	
	@RequestMapping(value = "/views/**", method = {RequestMethod.GET, RequestMethod.POST})
	public String templateHandler(HttpServletRequest request) {
		String resource = request.getRequestURI().substring("/views/".length());
		resource = resource.substring(0, resource.indexOf(".html"));
		return resource;
	}
	
}
