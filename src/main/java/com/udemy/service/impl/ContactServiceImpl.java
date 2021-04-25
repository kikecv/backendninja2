package com.udemy.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.udemy.component.ContactCoverter;
import com.udemy.entity.Contact;
import com.udemy.model.ContactModel;
import com.udemy.repository.ContactRepository;
import com.udemy.service.ContactService;

@Service("contactService")
public class ContactServiceImpl implements ContactService{

	@Autowired
	@Qualifier("contactRepository")
	private ContactRepository contactRepository;
	
	@Autowired
	@Qualifier("contactCoverter")
	private ContactCoverter contactCoverter;
	
	@Override
	public ContactModel addContact(ContactModel contactModel) {
		Contact contact= contactRepository.save(contactCoverter.converterContactModel2Contact(contactModel));
		return contactCoverter.converterContact2ContactModel(contact);
	}

	@Override
	public List<ContactModel> listAllContacts() {
		List<Contact> contacts = contactRepository.findAll();
		List<ContactModel> contactsModel = new ArrayList<>();
		for (Contact contact : contacts) {
			contactsModel.add(contactCoverter.converterContact2ContactModel(contact));
		}
	
		return contactsModel;
	}

	@Override
	public ContactModel findContactById(int id) {

		Contact contact = contactRepository.findById(id);
		return contactCoverter.converterContact2ContactModel(contact);
		
	}

	@Override
	public void removeContact(int id) {
		Contact contact = contactRepository.findById(id);
		if (contact!=null) {
		contactRepository.delete(contact);
		}
		
	}

}
