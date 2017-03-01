package daofactory;

/**
 * Heirarchy of factories.
 */
public class XmlDaoFactory extends AbstractDaoFactory {
	
	public CustomerDao getCustomerDao() {
		//XmlCustomerDao etc are heirarchies of Objects
		return new XmlCustomerDao();
	}
	
	public OrderDao getOrderDao() {
		return new XmlOrderDao();
	}

}
