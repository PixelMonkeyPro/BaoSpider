package la.bao27.pages;

public enum Status {
	SUCCESS("SUCCESS"), FAIL("FAIL"), AGAIN("AGAIN");
	private String value;

	Status(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value;
	}
}
