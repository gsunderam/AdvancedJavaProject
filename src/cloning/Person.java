package cloning;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jun 23, 2013
 */
public class Person implements Cloneable {

  private String name = "GS";
  private int age =  33;
  private double salary = 8000.25;
  private String company = "matson";

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public double getSalary() {
    return salary;
  }

  public void setSalary(double salary) {
    this.salary = salary;
  }

  public String getCompany() {
    return company;
  }

  public void setCompany(String company) {
    this.company = company;
  }

  /**
   * Calling super.clone works here as all the fields are primitive. So OBject's default implementation works
   *
   * @return cloned Object
   */
  public Object clone() {
    Person o = null;
    try {
      o = (Person) super.clone();
			o.age = 41;
			o.company = "Intra";
    } catch (CloneNotSupportedException e) {
      e.printStackTrace();
    }

    return o;
  }

	/**
	 * Java calls this after cloning. If cloned == orig, then the same instance is returned
	 * @param o
	 * @return
	 */
	public boolean equals(Object o) {
		 stdout("Equals called");
     Person p = null;

     /** Always this is step 1 */
     if (o == this) return true;

     //Step 2
     if (o instanceof Person) p = (Person) o;

     //Step 3: Do the actual comparison to determine the semantics of equals for this class
     return p.name.equals(this.name) && p.age == this.age && p.company.equals(this.company);
  }

  /**
   * This is a good way to implement Hashcode. Use the same fields that are used in equals.
   * The algorithm below makes the hascode dependent on the order of the fields so that anagrams
   * return different hascodes
   *
   * @return hascode of this object
   */
  public int hashCode() {
    int result = 19;
    result = 37 * result + name.length();
    result = 37 * result + age;
    result = 37 * result + company.length();

    return result;
  }
}
