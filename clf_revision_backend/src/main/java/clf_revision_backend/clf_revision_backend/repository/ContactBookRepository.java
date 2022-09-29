package clf_revision_backend.clf_revision_backend.repository;

import clf_revision_backend.clf_revision_backend.mapper.AddressBookRowMapper;
import clf_revision_backend.clf_revision_backend.model.Contact;
import clf_revision_backend.clf_revision_backend.model.Contacts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContactBookRepository {

    private static String SQL_INSERT_NEW_CONTACT = "insert into address_book (id, name, mobile, email) values (?,?,?,?)";
    private static String SQL_GET_ALL_CONTACT = "select * from address_book";
    private static String SQL_DELETE_CONTACT_BY_ID = "delete from address_book where id= ?";
    private static String SQL_UPDATE_CONTACT_BY_ID = "update address_book set name=? , mobile =?, email =? where ID=?";
    @Autowired
    private JdbcTemplate template;

    public boolean insertNewContact(String id, String name, Integer mobile, String email){
        final int rowCount = template.update(SQL_INSERT_NEW_CONTACT, id, name, mobile, email);
        return rowCount > 0;
    }

    public List<Contact> getContactList (){
        return template.query(SQL_GET_ALL_CONTACT, new AddressBookRowMapper());
    }

    public boolean deleteContactById(String id){
        final int rowCount = template.update(SQL_DELETE_CONTACT_BY_ID, id);
        return rowCount > 0;
    }

    public boolean updateContactById(String id, String name, Integer mobile, String email){
        final int rowCount = template.update(SQL_UPDATE_CONTACT_BY_ID, name, mobile, email, id);
        return rowCount > 0;
    }



}
