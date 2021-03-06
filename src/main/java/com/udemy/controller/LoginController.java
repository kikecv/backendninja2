package com.udemy.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.udemy.constant.ViewConstant;

@Controller
public class LoginController {

	private static final Log LOG = LogFactory.getLog(LoginController.class);

	/*
	 * @GetMapping("/") public String redirectToLogin() {
	 * LOG.info("Method: redirectToLogin()"); return "redirect:/login";
	 * 
	 * }
	 */

	@GetMapping("/login")
	private String showLoginForm(Model model, @RequestParam(name = "error", required = false) String error,
			@RequestParam(name = "logout", required = false) String logout) {
		LOG.info("Method: showLoginForm() -- PARAMS: error =" + error + ", logout=" + logout);
		model.addAttribute("logout", logout);
		model.addAttribute("error", error);
		//model.addAttribute("userCredential", new UserCredential());
		LOG.info("Returning to login view");
		return ViewConstant.LOGIN;
	}

	@GetMapping({"/loginsuccess","/"})
	private String loginCheck() {

		LOG.info("Method: loginCheck()");
		LOG.info("Returning to contacts view");
		return "redirect:/contacts/showcontacts";

	}

	/*
	 * @PostMapping("/logincheck") private String loginCheck(@ModelAttribute(name =
	 * "userCredential") UserCredential userCredential) {
	 * 
	 * LOG.info("Method: loginCheck() -- PARAMS: " + userCredential.toString()); if
	 * (userCredential.getUsername().equals("user") &&
	 * userCredential.getPassword().equals("user")) {
	 * LOG.info("Returning to contacts view"); return
	 * "redirect:/contacts/showcontacts"; } LOG.info("Redirect to login?error");
	 * return "redirect:/login?error";
	 * 
	 * }
	 */

}
