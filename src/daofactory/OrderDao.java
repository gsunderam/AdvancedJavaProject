package daofactory;

import javax.sql.RowSet;
import java.util.Collection;

public interface OrderDao {
	public int insertOrder(String item, int id);
  public Order findOrder(int id);

}
