package sml;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * This class represents a collection of labels that refer to specific instructions within the program.
 * @author Calyn Hughes McInnes (chm1997)
 */
public final class Labels {
	private final Map<String, Integer> labels = new HashMap<>();

	/**
	 * Adds a label with the associated address to the map.
	 *
	 * @param label the label
	 * @param address the address the label refers to
	 */
	public void addLabel(String label, int address) {
		Objects.requireNonNull(label);

		if (labels.containsKey(label) == true) {
			String dupKeyMessage = "Duplicate Key: " + label + " ignored.";
			System.out.println(dupKeyMessage);
		}
		else {
			labels.put(label, address);
		}
	}

	/**
	 * Returns the address associated with the label.
	 *
	 * @param label the label
	 * @return the address the label refers to
	 */
	public int getAddress(String label) {
		// A NullPointerException can be thrown here if the method .get(v) is called on a value of v that does not
		// have a label assigned to it. This exception will be thrown within the .get() method of the hashmap.
		int returnValue = -1;
		try {
			returnValue = labels.get(label);
		}
		catch (NullPointerException e){
			String missingKeyMessage = "Key: " + label + " not found.";
			System.out.println(missingKeyMessage);
		}
		return returnValue;
	}

	/**
	 * representation of this instance,
	 * in the form "[label -> address, label -> address, ..., label -> address]"
	 *
	 * @return the string representation of the labels map
	 */
	@Override
	public String toString() {
		// TODO: Implement the method using the Stream API (see also class Registers).
		return "";
	}
	// TODO: Implement equals and hashCode (needed in class Machine).
	@Override
	public boolean equals(Object object) {
		return this.hashCode() == object.hashCode();
	}

	@Override
	public int hashCode() {
		return 110;
	}
	/**
	 * Removes the labels
	 */
	public void reset() {
		labels.clear();
	}
}
