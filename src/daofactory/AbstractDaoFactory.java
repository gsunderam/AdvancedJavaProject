package daofactory;

/**
 * This is also the "Factory Method" pattern. The subclasses determine which object to instantitiate.
 * There are two heirarchies. Heirarchy of factories and those of dao's. Sweet!
 */
public abstract class AbstractDaoFactory {
	
	public abstract ComponentDao getComponentDao();
	public abstract FeaturesDao getFeaturesDao();
	
	public AbstractDaoFactory getDaoFactory(int whichType) {
		if (whichType == 1) return new XmlDaoFactory();
		else if (whichType == 2) return new RdbDaoFactory();
		else return null;
	}

}
