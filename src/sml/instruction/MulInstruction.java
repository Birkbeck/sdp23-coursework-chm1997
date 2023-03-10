package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

/**
 * This class represents a machine instruction for multiplication.
 * It multiplies the contents of two registers given on instancing the object.
 * It stores the results of the operation in the first register.
 * @author Calyn Hughes McInnes (chm1997)
 */

public class MulInstruction extends Instruction {
    private final RegisterName result;
    private final RegisterName source;

    public static final String OP_CODE = "mul";

    public MulInstruction(String label, RegisterName result, RegisterName source) {
        super(label, OP_CODE);
        this.result = result;
        this.source = source;
    }

    @Override
    public int execute(Machine m) {
        int value1 = m.getRegisters().get(result);
        int value2 = m.getRegisters().get(source);
        m.getRegisters().set(result, value1 * value2);
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + result + " " + source;
    }

    @Override
    public boolean equals(Object object) {
        return this.hashCode() == object.hashCode();
    }

    @Override
    public int hashCode() {
        return 113;
    }
}
