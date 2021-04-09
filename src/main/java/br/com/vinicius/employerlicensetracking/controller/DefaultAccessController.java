package br.com.vinicius.employerlicensetracking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@CrossOrigin(origins = "*")
public class DefaultAccessController {
	
	@GetMapping("/")
	public String whatsappRedirection(){
		return "/index.jsf";
	}
}
