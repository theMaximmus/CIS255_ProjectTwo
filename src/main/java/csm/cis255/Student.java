package csm.cis255;

public class Student {
	
	/* DO NOT MODIFY THIS CLASS! */

	private String name, id;
	private boolean tuitionPaid;

	public Student(String name, String id, boolean tuitionPaid) {
		this.name = name;
		this.id = id;
		this.tuitionPaid = tuitionPaid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getID() {
		return id;
	}

	public void setID(String id) {
		this.id = id;
	}

	public boolean isTuitionPaid() {
		return tuitionPaid;
	}

	public void setTuitionPaid(boolean tuitionPaid) {
		this.tuitionPaid = tuitionPaid;
	}

	@Override
	public String toString() {
		return name + " (" + id + ")";
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Student) {
			Student other = (Student) obj;
			return this.id.equalsIgnoreCase(other.id) &&
					this.name.equalsIgnoreCase(other.name) &&
					this.tuitionPaid==other.tuitionPaid;
		} else {
			return false;
		}
	}
	

	
}
