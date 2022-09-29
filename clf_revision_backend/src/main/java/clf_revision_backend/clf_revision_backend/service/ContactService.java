package clf_revision_backend.clf_revision_backend.service;

import clf_revision_backend.clf_revision_backend.model.Contact;
import clf_revision_backend.clf_revision_backend.repository.ContactBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    @Autowired
    private ContactBookRepository contactRepo;

    public boolean addContact(Contact contact){
        return contactRepo.insertNewContact(contact.getId(), contact.getName(), contact.getMobile(), contact.getEmail());
    }
    public List <Contact> retrieveContactList(){
        return contactRepo.getContactList();
    }

    public boolean deleteContact(String id){
        return contactRepo.deleteContactById(id);
    }
    public boolean updateContact(Contact contact){
        return contactRepo.updateContactById(contact.getId(), contact.getName(), contact.getMobile(), contact.getEmail());
    }
}
