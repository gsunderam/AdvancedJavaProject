package cloning;

/**
 * User: gsunderam
 * Date: Jun 23, 2013
 */
public class SpecialPerson extends Person {
  private String category = "Exec";

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  /**
   *
   * @return Clone of this
   */
  public Object clone() {
    return super.clone();
  }
}
