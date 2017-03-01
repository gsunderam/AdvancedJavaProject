package daofactory;

import javax.sql.RowSet;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static log.Logger.stdout;

public class RdbCustomerDao implements CustomerDao {
	private static final Map<String, Customer> map = new HashMap<String, Customer>();

	@Override
	public int insertCustomer(String name, String address) {
		stdout("RDB imp, of insert customer");
		Customer c = new Customer();
		c.name = name;
		c.address = address;

		map.put(name, c);
		return 1;
	}

	@Override
	public Customer findCustomer(String name) {
		stdout("RDB imp, of find customer");
		return map.get(name);
	}

	@Override
	public RowSet selectCustomersRS(String name) {
		stdout("RDB imp, of select RS customer");
		return null;
	}

	@Override
	public Collection selectCustomersTO(String name) {
		stdout("RDB imp, of insert Select TO customer");
		return null;
	}
}
