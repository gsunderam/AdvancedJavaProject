package daofactory;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Feb 27, 2017
 */
public class DaoFactoryClient {
	public static void main(String[] args) {
		//Get main DAO from the factory. i.e. RDBMS, XML, Object DB
		AbstractDaoFactory factory = AbstractDaoFactory.getDaoFactory(DBTypes.RDB.type);

		//Then get the specific dao i.e. CustomerDao, USerDao, OrderDao, OrderDetailsDao, EmployeeDao etc
		CustomerDao customerDao = factory.getCustomerDao();
		int id = customerDao.insertCustomer("Sundar", "765 W stottler pl Gilbert 85233");
		stdout("Inserted customer with " + id + " address " + customerDao.findCustomer("Sundar").address);

		factory = AbstractDaoFactory.getDaoFactory(DBTypes.XML.type);
		OrderDao orderDao = factory.getOrderDao();
		orderDao.insertOrder("room items", 35);
		orderDao.insertOrder("office items", 36);

		orderDao.findOrder(35);
	}
}
