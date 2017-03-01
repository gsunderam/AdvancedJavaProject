package daofactory;

import javax.sql.RowSet;
import java.util.Collection;

public interface CustomerDao {
	public int insertCustomer(String name, String address);
  public Customer findCustomer(String name);
  public RowSet selectCustomersRS(String name);
  public Collection selectCustomersTO(String name);
}
