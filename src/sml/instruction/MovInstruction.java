package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

/**
 * This class represents a machine instruction for outputting a register's value.
 * It prints out the contents of a register (given on instancing the object) to system output.
 * @author Calyn Hughes McInnes (chm1997)
 */

public class MovInstruction extends Instruction {
    private final RegisterName result;

    private final int value;
    public static final String OP_CODE = "mov";

    public MovInstruction(String label, RegisterName result, int value) {
        super(label, OP_CODE);
        this.result = result;
        this.value = value;
    }

    @Override
    public int execute(Machine m) {
        m.getRegisters().set(result, value);
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + result + " " + value;
    }

    @Override
    public boolean equals(Object object) {
        return this.hashCode() == object.hashCode();
    }

    @Override
    public int hashCode() {
        return 116;
    }
}
