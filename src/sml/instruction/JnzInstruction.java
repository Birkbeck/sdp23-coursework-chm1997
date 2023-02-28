package sml.instruction;

import sml.Instruction;
import sml.Labels;
import sml.Machine;
import sml.RegisterName;

/**
 * This class represents a machine instruction for moving the program counter of a machine to a new position.
 * It sets the program counter to the position of a label given on instancing the object.
 * It only does so if the value of the register given on instancing the object is zero.
 * @author Calyn Hughes McInnes (chm1997)
 */

public class JnzInstruction extends Instruction {
    private final RegisterName result;
    private final String labelToUse;

    public static final String OP_CODE = "jnz";

    public JnzInstruction(String label, RegisterName result, String labelToUse) {
        super(label, OP_CODE);
        this.result = result;
        this.labelToUse = labelToUse;
    }

    @Override
    public int execute(Machine m) {
        int value1 = m.getRegisters().get(result);
        if (value1 != 0) {
            Labels machineLabels = m.getLabels();
            return machineLabels.getAddress(labelToUse);
        }
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + result + " " + labelToUse;
    }

    @Override
    public boolean equals(Object object) {
        return this.hashCode() == object.hashCode();
    }

    @Override
    public int hashCode() {
        return 117;
    }
}
