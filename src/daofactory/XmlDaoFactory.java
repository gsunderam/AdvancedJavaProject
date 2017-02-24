package daofactory;

public class XmlDaoFactory extends AbstractDaoFactory {
	
	public ComponentDao getComponentDao() {
		return new XmlComponentDao();
	}
	
	public FeaturesDao getFeaturesDao() {
		return new XmlFeaturesDao();
	}

}
