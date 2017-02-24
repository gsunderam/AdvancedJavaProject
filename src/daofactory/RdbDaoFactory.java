package daofactory;

public class RdbDaoFactory extends AbstractDaoFactory {

	public ComponentDao getComponentDao() {
		return new RdbComponentDao();
	}
	
	public FeaturesDao getFeaturesDao() {
		return new RdbFeaturesDao();
	}
}
