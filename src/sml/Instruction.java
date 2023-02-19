package sml;

/**
 * Represents an abstract instruction.
 *
 * @author Birkbeck University modified by Calyn Hughes McInnes
 */
public abstract class Instruction {
	protected final String label;
	protected final String opcode;

	protected final int hashCode;

	/**
	 * Constructor: an instruction with a label and an opcode
	 * (opcode must be an operation of the language)
	 *
	 * @param label optional label (can be null)
	 * @param opcode operation name
	 */
	public Instruction(String label, String opcode) {
		this.label = label;
		this.opcode = opcode;
		this.hashCode = this.hashCode();
	}

	public String getLabel() {
		return label;
	}

	public String getOpcode() { return opcode; }

	public static int NORMAL_PROGRAM_COUNTER_UPDATE = -1;

	/**
	 * Executes the instruction in the given machine.
	 *
	 * @param machine the machine the instruction runs on
	 * @return the new program counter (for jump instructions)
	 *          or NORMAL_PROGRAM_COUNTER_UPDATE to indicate that
	 *          the instruction with the next address is to be executed
	 */

	public abstract int execute(Machine machine);

	protected String getLabelString() { return (getLabel() == null) ? "" : getLabel() + ": "; }

	// The abstract keyword in the following method declaration specifies that all subclasses of this
	// class must implement the following method (and as a result, override the standard toString method + others).
	@Override
	public abstract String toString();

	@Override
	public abstract boolean equals(Object object);
	@Override
	public abstract int hashCode();

}
