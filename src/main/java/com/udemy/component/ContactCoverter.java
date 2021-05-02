package com.udemy.component;

import org.springframework.stereotype.Component;

import com.udemy.entity.Contact;
import com.udemy.model.ContactModel;

// TODO: Auto-generated Javadoc
/**
 * The Class ContactCoverter.
 */
@Component("contactCoverter")
public class ContactCoverter {
	
	/**
	 * Converter contact model 2 contact.
	 *
	 * @param contactModel the contact model
	 * @return the contact
	 */
	public Contact converterContactModel2Contact(ContactModel contactModel) {
		
		Contact contact = new Contact();
		contact.setId(contactModel.getId());
		contact.setFirstname(contactModel.getFirstname());
		contact.setLastname(contactModel.getLastname());
		contact.setTelephone(contactModel.getTelephone());
		contact.setCity(contactModel.getCity());
		return contact;
		
	}
	
	/**
	 * Converter contact 2 contact model.
	 *
	 * @param contact the contact
	 * @return the contact model
	 */
	public ContactModel converterContact2ContactModel(Contact contact) {
		
		ContactModel contactModel = new ContactModel();
		contactModel.setId(contact.getId());
		contactModel.setFirstname(contact.getFirstname());
		contactModel.setLastname(contact.getLastname());
		contactModel.setTelephone(contact.getTelephone());
		contactModel.setCity(contact.getCity());

		return contactModel;
		
	}

}
