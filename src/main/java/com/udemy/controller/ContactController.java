package com.udemy.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.udemy.constant.ViewConstant;
import com.udemy.model.ContactModel;
import com.udemy.service.ContactService;

@Controller
@PreAuthorize("permitAll()")
@RequestMapping("/contacts")
public class ContactController {

	private static final Log LOG = LogFactory.getLog(ContactController.class);

	@Autowired
	@Qualifier("contactService")
	private ContactService contactService;


	@GetMapping("/contactform")
	public String redirectContacForm(@RequestParam(name = "id", required = false) int id, Model model) {
		ContactModel contactModel = new ContactModel();
		if (id != 0) {
			contactModel = contactService.findContactById(id);
		}
		model.addAttribute("contactModel", contactModel);

		return ViewConstant.CONTACT_FORM;
	}

	@GetMapping("/cancel")
	public String cancel() {

		return "redirect:/contacts/showcontacts";
	}

	@PostMapping("/addcontact")
	public String addContact(@ModelAttribute(name = "contactModel") ContactModel contactModel, Model model) {
		LOG.info("Method: addContact() -- PARAMS: " + contactModel.toString());
		if (contactService.addContact(contactModel) != null) {
			model.addAttribute("result", 1);
		} else {
			model.addAttribute("result", 0);
		}

		return "redirect:/contacts/showcontacts";

	}


	@GetMapping("/showcontacts")
	public ModelAndView showContacts() {

		ModelAndView mav = new ModelAndView(ViewConstant.CONTACTS);
		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		mav.addObject("username", user.getUsername());
		mav.addObject("contacts", contactService.listAllContacts());
		return mav;

	}

	@GetMapping("/removecontact")
	private ModelAndView removeContact(@RequestParam(name = "id", required = true) int id) {
		contactService.removeContact(id);

		return showContacts();
	}

}
