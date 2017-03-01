package daofactory;

/**
 * This is also the "Factory Method" pattern. The subclasses (RDBDaoFac, XMLDao?Fac...) determine which object to instantitiate.
 * There are two heirarchies. Heirarchy of factories and those of dao's. Sweet!
 */
public abstract class AbstractDaoFactory {
	
	//Object heirarchies  -- 2
	public abstract CustomerDao getCustomerDao();
	public abstract OrderDao getOrderDao();
	
	//Heirarchies of factories -- 1
	public static AbstractDaoFactory getDaoFactory(int whichType) {
		if (whichType == DBTypes.XML.getType()) return new XmlDaoFactory();
		else if (whichType == DBTypes.RDB.getType()) return new RdbDaoFactory();
		else return null;
	}

}
