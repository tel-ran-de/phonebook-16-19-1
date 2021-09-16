package com.telran.phonebookapi.service;

import com.telran.phonebookapi.entity.Contact;
import com.telran.phonebookapi.entity.Email;
import com.telran.phonebookapi.repo.IContactRepo;
import com.telran.phonebookapi.repo.IEmailRepo;
import com.telran.phonebookapi.service.exceptions.AddressNotFoundException;
import com.telran.phonebookapi.service.exceptions.ContactNotFoundException;
import com.telran.phonebookapi.service.exceptions.EmailNotFountException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService {

    private static final String EMAIL_NOT_FOUND = "email is not found";
    private static final String CONTACT_NOT_FOUND = "required contact is not found";
    private IEmailRepo emailRepo;
    private IContactRepo contactRepo;

    public EmailService(IEmailRepo emailRepo, IContactRepo contactRepo) {
        this.emailRepo = emailRepo;
        this.contactRepo = contactRepo;
    }

    public List<Email> getAllEmailsByContactIDd(long contactId) {

        return emailRepo.findAllByContactId(contactId);
    }

    public void editEmailById(String email, boolean isFavorite, long emailId) {

        Email email1ToEdit = emailRepo.findById(emailId)
                .orElseThrow(() -> new EmailNotFountException(EMAIL_NOT_FOUND));

        email1ToEdit.setEmail(email);
        email1ToEdit.setFavorite(isFavorite);

        emailRepo.save(email1ToEdit);
    }

    public Email addEmail(String email, boolean isFavorite, long contactId) {

        Contact contact = contactRepo.findById(contactId)
                .orElseThrow(() -> new ContactNotFoundException(CONTACT_NOT_FOUND));
        Email newEmail = new Email(email, isFavorite, contact);
        return emailRepo.save(newEmail);
    }

    public Email getEmailById(long emailId) {

        return emailRepo.findById(emailId)
                .orElseThrow(() -> new EmailNotFountException(EMAIL_NOT_FOUND));
    }

    public void deleteEmailById(long emailId) {

        if (emailRepo.existsById(emailId))
            emailRepo.deleteById(emailId);
        else
            throw new EmailNotFountException(EMAIL_NOT_FOUND);
    }
}
