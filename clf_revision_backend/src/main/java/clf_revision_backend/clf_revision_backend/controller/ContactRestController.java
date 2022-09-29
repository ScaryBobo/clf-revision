package clf_revision_backend.clf_revision_backend.controller;

import clf_revision_backend.clf_revision_backend.model.Contact;
import clf_revision_backend.clf_revision_backend.model.Contacts;
import clf_revision_backend.clf_revision_backend.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/")
public class ContactRestController {

    @Autowired
    private ContactService contactSvc;

    private Logger logger = Logger.getLogger(ContactRestController.class.getName());


    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> postContact (@RequestBody String payload){
        logger.info("payload is>>>>>>>>>> %s".formatted(payload));
        Gson gson = new Gson();
        Contacts contacts = gson.fromJson(payload, Contacts.class);
        System.out.println(">>>> after conversion >>>:  " + contacts.toString());
        contacts.getContacts().stream().forEach(x ->{
            String id = UUID.randomUUID().toString().substring(0,8);
            x.setId(id);
            contactSvc.addContact(x);
        });
        return null;
    }

    @GetMapping(path = "/retrieve")
    public ResponseEntity<List<Contact>> findAllContacts(){
        List <Contact> contactList = contactSvc.retrieveContactList();
        return ResponseEntity.accepted().body(contactList);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> destroyContact(@PathVariable String id){
        logger.info("DELETE MAPPING IS CALLED");
        contactSvc.deleteContact(id);
        return ResponseEntity.status(HttpStatus.OK).body("");
    }

    @PutMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateById(@RequestBody String payload){
        System.out.println("Update is called");
        logger.info(">>>> payload of object to be updated %s".formatted(payload));
        Gson gson = new Gson();
        Contact contact = gson.fromJson(payload,Contact.class);
        contactSvc.updateContact(contact);

        return null;
    }


}
