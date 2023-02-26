package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.Instruction;
import sml.Machine;
import sml.Registers;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static sml.Registers.Register.*;

class OutInstructionTest {
    private Machine machine;
    private Registers registers;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        machine = new Machine(new Registers());
        registers = machine.getRegisters();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        machine = null;
        registers = null;
        System.setOut(originalOut);
    }

    @Test
    void executeValid() {
        registers.set(EAX, 5);
        Instruction instruction = new OutInstruction(null, EAX);
        instruction.execute(machine);
        Assertions.assertEquals("5", outContent.toString());
    }
    @Test
    void executeValidAfterOperation() {
        registers.set(EAX, 5);
        registers.set(EBX, 6);
        Instruction instruction = new AddInstruction(null, EAX, EBX);
        instruction.execute(machine);
        Instruction instruction2 = new OutInstruction(null, EAX);
        instruction2.execute(machine);
        Assertions.assertEquals("11", outContent.toString());
    }
    @Test
    void toStringValid(){
        registers.set(EAX, 5);
        Instruction instruction = new OutInstruction(null, EAX);
        String ActualString = instruction.toString();
        String ExpectedString = "out EAX";
        Assertions.assertEquals(ExpectedString, ActualString);
    }

    @Test
    void toStringValidLabel(){
        registers.set(EAX, 5);
        Instruction instruction = new OutInstruction("f1", EAX);
        String ActualString = instruction.toString();
        String ExpectedString = "f1: out EAX";
        Assertions.assertEquals(ExpectedString, ActualString);
    }

    @Test
    void equalsValid(){
        registers.set(EAX, 5);
        registers.set(EBX, 6);
        Instruction instruction1 = new OutInstruction(null, EAX);
        Instruction instruction2 = new OutInstruction(null, EBX);
        Assertions.assertTrue(instruction1.equals(instruction2));
    }

    @Test
    void equalsInvalid(){
        registers.set(EAX, 5);
        registers.set(EBX, 6);
        registers.set(ECX, 7);
        Instruction instruction1 = new OutInstruction(null, EAX);
        Instruction instruction2 = new SubInstruction(null, EBX, ECX);
        Assertions.assertFalse(instruction1.equals(instruction2));
    }

    @Test
    void hashCodeValid() {
        registers.set(EAX, 5);
        registers.set(EBX, 6);
        Instruction instruction1 = new OutInstruction(null, EAX);
        Assertions.assertEquals(115, instruction1.hashCode());
    }

    @Test
    void hashCodInvalid() {
        registers.set(EAX, 5);
        Instruction instruction1 = new OutInstruction(null, EAX);
        Assertions.assertNotEquals(45324534, instruction1.hashCode());
    }
}