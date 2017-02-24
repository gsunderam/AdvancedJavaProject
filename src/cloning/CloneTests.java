package cloning;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jun 23, 2013
 */
public class CloneTests {

  public static void main(String[] args) {
     Person p = new Person();
     Person clonedPerson = (Person) p.clone();
     stdout("Person p is " + p.getCompany() + " Cloned Person is " + clonedPerson.getCompany());

     stdout("p.equals(cloned) ? " + p.equals(clonedPerson));
     stdout("p.getClass() == clone's ? " + p.getClass() + ":" + clonedPerson.getClass());

     SpecialPerson specialPerson = new SpecialPerson();
     SpecialPerson clonedSpecialPerson = (SpecialPerson) specialPerson.clone();
		 stdout("Person p is " + specialPerson + " Cloned Person is " + clonedSpecialPerson);
     stdout("sp.getClass() == clone's ? " + specialPerson.getClass() + ":" + clonedSpecialPerson.getClass());
     stdout("Categories : " + specialPerson.getCategory().equals(clonedSpecialPerson.getCategory()));
	}
}
