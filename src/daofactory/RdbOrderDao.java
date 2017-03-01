package daofactory;

import static log.Logger.stdout;

public class RdbOrderDao implements OrderDao {

	@Override
	public int insertOrder(String name, int id) {
		stdout("RDB imp, of insert order");
		return 23;
	}

	@Override
	public Order findOrder(int name) {
		stdout("RDB imp, of find order");
		return null;
	}
}
