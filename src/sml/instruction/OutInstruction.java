package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

/**
 * This class represents a machine instruction for addition.
 * It adds the contents of two registers given on instancing the object.
 * It stores the results of the operation in the first register.
 * @author Calyn Hughes McInnes (chm1997)
 */

public class OutInstruction extends Instruction {
    private final RegisterName result;

    public static final String OP_CODE = "add";

    public OutInstruction(String label, RegisterName result) {
        super(label, OP_CODE);
        this.result = result;
    }

    @Override
    public int execute(Machine m) {
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + result;
    }

    @Override
    public boolean equals(Object object) {
        return this.hashCode() == object.hashCode();
    }

    @Override
    public int hashCode() {
        return 111;
    }
}
