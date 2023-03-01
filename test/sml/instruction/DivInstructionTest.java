package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.Instruction;
import sml.Machine;
import sml.Registers;

import static sml.Registers.Register.*;

class DivInstructionTest {
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
        registers.set(EAX, 30);
        registers.set(EBX, 6);
        Instruction instruction = new DivInstruction(null, EAX, EBX);
        instruction.execute(machine);
        Assertions.assertEquals(5, machine.getRegisters().get(EAX));
    }

    @Test
    void executeValidTwo() {
        registers.set(EAX, -30);
        registers.set(EBX, 5);
        Instruction instruction = new DivInstruction(null, EAX, EBX);
        instruction.execute(machine);
        Assertions.assertEquals(-6, machine.getRegisters().get(EAX));
    }

    @Test
    void executeValidThree() {
        registers.set(EAX, 5);
        registers.set(EBX, 2);
        Instruction instruction = new DivInstruction(null, EAX, EBX);
        instruction.execute(machine);
        Assertions.assertEquals(2, machine.getRegisters().get(EAX));
    }

    @Test
    void executeValidFour() {
        registers.set(EAX, 5);
        registers.set(EBX, 6);
        Instruction instruction = new DivInstruction(null, EAX, EBX);
        instruction.execute(machine);
        Assertions.assertEquals(0, machine.getRegisters().get(EAX));
    }

    @Test
    void executeValidFive() {
        registers.set(EAX, 0);
        registers.set(EBX, 5);
        Instruction instruction = new DivInstruction(null, EAX, EBX);
        instruction.execute(machine);
        Assertions.assertEquals(0, machine.getRegisters().get(EAX));
    }



    @Test
    void executeValidSix() {
        registers.set(EAX, 5);
        registers.set(EBX, 0);
        Instruction instruction = new DivInstruction(null, EAX, EBX);
        instruction.execute(machine);
        Assertions.assertEquals(5, machine.getRegisters().get(EAX));
    }

    @Test
    void toStringValid(){
        Instruction instruction = new DivInstruction(null, EAX, EBX);
        String ActualString = instruction.toString();
        String ExpectedString = "div EAX EBX";
        Assertions.assertEquals(ExpectedString, ActualString);
    }

    @Test
    void toStringValidLabel(){
        Instruction instruction = new DivInstruction("f1", EAX, EBX);
        String ActualString = instruction.toString();
        String ExpectedString = "f1: div EAX EBX";
        Assertions.assertEquals(ExpectedString, ActualString);
    }

    @Test
    void equalsValid(){
        registers.set(EAX, 5);
        registers.set(EBX, 6);
        registers.set(ECX, 4);
        registers.set(EDX, 7);
        Instruction instruction1 = new DivInstruction(null, EAX, EBX);
        Instruction instruction2 = new DivInstruction(null, ECX, EDX);
        Assertions.assertTrue(instruction1.equals(instruction2));
    }

    @Test
    void equalsInvalid(){
        registers.set(EAX, 5);
        registers.set(EBX, 6);
        registers.set(ECX, 4);
        registers.set(EDX, 7);
        Instruction instruction1 = new DivInstruction(null, EAX, EBX);
        Instruction instruction2 = new MulInstruction(null, ECX, EDX);
        Assertions.assertFalse(instruction1.equals(instruction2));
    }

    @Test
    void hashCodeValid() {
        registers.set(EAX, 5);
        registers.set(EBX, 6);
        Instruction instruction = new DivInstruction(null, EAX, EBX);
        Assertions.assertEquals(114, instruction.hashCode());
    }

    @Test
    void hashCodInvalid() {
        registers.set(EAX, 5);
        registers.set(EBX, 6);
        Instruction instruction = new DivInstruction(null, EAX, EBX);
        Assertions.assertNotEquals(1112321312, instruction.hashCode());
    }
}