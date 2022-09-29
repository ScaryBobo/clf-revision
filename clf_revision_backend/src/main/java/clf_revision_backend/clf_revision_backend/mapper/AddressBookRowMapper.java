package clf_revision_backend.clf_revision_backend.mapper;

import clf_revision_backend.clf_revision_backend.model.Contact;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressBookRowMapper implements RowMapper<Contact> {

    public Contact mapRow (ResultSet rs, int numRow) throws SQLException {
        Contact contact = new Contact();
        contact.setId(rs.getString("id"));
        contact.setName(rs.getString("name"));
        contact.setEmail(rs.getString("email"));
        contact.setMobile(rs.getInt("mobile"));

        return contact;
    }
}
