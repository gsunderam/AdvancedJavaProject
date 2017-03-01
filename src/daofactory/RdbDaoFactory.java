package daofactory;

import java.sql.Connection;

public class RdbDaoFactory extends AbstractDaoFactory {
	public static final String DRIVER=
    "COM.oracle.jdbc.OracleDriver";
  public static final String DBURL=
    "jdbc:oracle:thin://somehost:3433/CoreJ2EEDB";

  // method to create Cloudscape connections
  public static Connection createConnection() {
    // Use DRIVER and DBURL to create a connection
    // Recommend connection pool implementation/usage
		return null;
  }

	public CustomerDao getCustomerDao() {
		return new RdbCustomerDao();
	}
	
	public OrderDao getOrderDao() {
		return new RdbOrderDao();
	}
}
