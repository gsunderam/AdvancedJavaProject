package daofactory;

/**
 * User: gsunderam
 * Date: Feb 28, 2017
 */
public enum DBTypes {
	XML(2), RDB(1), NONE(-1);

	int type;

	DBTypes(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}
}
