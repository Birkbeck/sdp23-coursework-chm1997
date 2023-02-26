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

    public static final String OP_CODE = "out";

    public MovInstruction(String label, RegisterName result) {
        super(label, OP_CODE);
        this.result = result;
    }

    @Override
    public int execute(Machine m) {
        int value1 = m.getRegisters().get(result);
        String outString = Integer.toString(value1);
        System.out.print(outString);
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
        return 115;
    }
}
