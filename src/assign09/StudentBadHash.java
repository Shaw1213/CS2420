package assign09;

import java.text.DecimalFormat;

/**
 * This class provides a simple representation for a University of Utah student.
 * Object's hashCode method is overridden with a correct hash function for this
 * object, but one that does a poor job of distributing students in a hash
 * table.
 * 
 * @author Erin Parker, Janne Wald, and Shawn Zhang
 * @version April 1, 2024.
 */
public class StudentBadHash {
	// Member Variables
	private int uid;
	private String firstName;
	private String lastName;

	/**
	 * Creates a new student with the specified uid, firstName, and lastName.
	 * 
	 * @param uid The 8 digit id number for the student
	 * @param firstName First name of the student
	 * @param lastName Last name of the student
	 */
	public StudentBadHash(int uid, String firstName, String lastName) {
		this.uid = uid;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	/**
	 * @return the UID for this student object
	 */
	public int getUid() {
		return this.uid;
	}

	/**
	 * @return the first name for this student object
	 */
	
	public String getFirstName() {
		return this.firstName;
	}
 
	/**
	 * @return the last name for this student object
	 */
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * @return true if this student and 'other' have the same UID, first name, and last name; false otherwise
	 */
	public boolean equals(Object other) {
		// change to StudentMediumHash and StudentGoodHash for two new classes
		if(!(other instanceof StudentBadHash))
			return false;

		StudentBadHash rhs = (StudentBadHash) other;

		return this.uid == rhs.uid && this.firstName.equals(rhs.firstName) && this.lastName.equals(rhs.lastName);
	}
	
	/**
	 * @return a textual representation of this student
	 */
	public String toString() {
		DecimalFormat formatter = new DecimalFormat("0000000");
		return firstName + " " + lastName + " (u" + formatter.format(uid) + ")";
	}

	/**
	 * @return a bad hashcode, the diversity will depend on the length of the first name. On average will have around 7
	 * possible hashcodes.
	 */
	public int hashCode() {
		return firstName.length();
	}
}