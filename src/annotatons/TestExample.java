package annotatons;

@TesterInfo(
	priority = TesterInfo.Priority.HIGH,
	createdBy = "mkyong.com",
	tags = {"sales","test" })
public class TestExample {

	@Test
	void testA() {
	  if (true) System.out.println("Test A Run");
		  //throw new RuntimeException("This test always failed");
	}

	@Test(enabled = false)
	void testB() {
	  if (false)
		System.out.println("Test B didn't Run");
	}

	@Test(enabled = true)
	void testC() {
	  if (10 > 1) {
		System.out.println("Test C Run");
	  }
	}

}
