package com.udemy.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.udemy.model.ContactModel;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/rest")
public class RestController {

	@GetMapping("/checkrest")
	public ResponseEntity<ContactModel> checkRest(){
		
		ContactModel cm = new ContactModel(5, "Arturo", "Vidal", "987654321", "Santiago");
		return new ResponseEntity<ContactModel>(cm, HttpStatus.OK);
	}
	
}
