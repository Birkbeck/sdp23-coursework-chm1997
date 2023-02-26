package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.Instruction;
import sml.Machine;
import sml.Registers;

import static sml.Registers.Register.*;

class MovInstructionTest {
    private Machine machine;
    private Registers registers;

    @BeforeEach
    void setUp() {
        machine = new Machine(new Registers());
        registers = machine.getRegisters();
    }

    @AfterEach
    void tearDown() {
        machine = null;
        registers = null;
    }

    @Test
    void executeValid() {
        int val1 = 2;
        Instruction instruction = new MovInstruction(null, EAX, val1);
        instruction.execute(machine);
        Assertions.assertEquals(2, machine.getRegisters().get(EAX));
    }

    @Test
    void executeValidPreviouslySet() {
        registers.set(EAX, 5);
        int val1 = 6;
        Instruction instruction = new MovInstruction(null, EAX, val1);
        instruction.execute(machine);
        Assertions.assertEquals(6, machine.getRegisters().get(EAX));
    }

    @Test
    void toStringValid(){
        int val1 = 5;
        Instruction instruction = new MovInstruction(null, EAX, val1);
        String ActualString = instruction.toString();
        String ExpectedString = "mov EAX 5";
        Assertions.assertEquals(ExpectedString, ActualString);
    }

    @Test
    void toStringValidLabel(){
        int val1 = 7;
        Instruction instruction = new MovInstruction("f1", EAX, val1);
        String ActualString = instruction.toString();
        String ExpectedString = "f1: mov EAX 7";
        Assertions.assertEquals(ExpectedString, ActualString);
    }

    @Test
    void equalsValid(){
        int val1 = 7;
        int val2 = 10;
        Instruction instruction1 = new MovInstruction(null, EAX, val1);
        Instruction instruction2 = new MovInstruction(null, EBX, val2);
        Assertions.assertTrue(instruction1.equals(instruction2));
    }

    @Test
    void equalsInvalid(){
        registers.set(EAX, 5);
        int val1 = 40;
        registers.set(EBX, 6);
        registers.set(ECX, 4);
        Instruction instruction1 = new MovInstruction(null, EAX, val1);
        Instruction instruction2 = new SubInstruction(null, EBX, ECX);
        Assertions.assertFalse(instruction1.equals(instruction2));
    }

    @Test
    void hashCodeValid() {
        int val1 = 5;
        Instruction instruction = new MovInstruction(null, EAX, val1);
        Assertions.assertEquals(116, instruction.hashCode());
    }

    @Test
    void hashCodInvalid() {
        int val1 = 5;
        Instruction instruction = new MovInstruction(null, EAX, val1);
        Assertions.assertNotEquals(1112321312, instruction.hashCode());
    }
}