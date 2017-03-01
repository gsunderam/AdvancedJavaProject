package daofactory;

import javax.sql.RowSet;
import java.util.Collection;

public class XmlCustomerDao implements CustomerDao {

	@Override
	public int insertCustomer(String name, String address) {
		return 0;
	}

	@Override
	public Customer findCustomer(String name) {
		return null;
	}

	@Override
	public RowSet selectCustomersRS(String name) {
		return null;
	}

	@Override
	public Collection selectCustomersTO(String name) {
		return null;
	}
}
