package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.Instruction;
import sml.Machine;
import sml.Registers;
import sml.Labels;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static sml.Registers.Register.*;

class JnzInstructionTest {
    private Machine machine;
    private Registers registers;
    private Labels labels;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        machine = new Machine(new Registers());
        registers = machine.getRegisters();
        labels = machine.getLabels();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        machine = null;
        registers = null;
        labels = null;
        System.setOut(originalOut);
    }

    @Test
    void executeValid() {
        labels.addLabel("f1", 4);
        Instruction instruction1 = new MovInstruction(null, EAX, 3);
        Instruction instruction2 = new MovInstruction(null, EBX, 4);
        Instruction instruction3 = new MovInstruction(null, ECX, 2);
        Instruction instruction4 = new MovInstruction(null, EDX, 1);
        Instruction instruction5 = new AddInstruction("f1", EAX, EBX);
        Instruction instruction6 = new SubInstruction(null, ECX, EDX);
        Instruction instruction7 = new JnzInstruction(null, ECX, "f1");
        Instruction instruction8 = new OutInstruction(null, EAX);
        List<Instruction> program = machine.getProgram();
        program.add(instruction1);
        program.add(instruction2);
        program.add(instruction3);
        program.add(instruction4);
        program.add(instruction5);
        program.add(instruction6);
        program.add(instruction7);
        program.add(instruction8);
        machine.execute();
        Assertions.assertEquals("11", outContent.toString());
    }

    @Test
    void executeValidMultipleLoops() {
        labels.addLabel("f1", 4);
        Instruction instruction1 = new MovInstruction(null, EAX, 3);
        Instruction instruction2 = new MovInstruction(null, EBX, 4);
        Instruction instruction3 = new MovInstruction(null, ECX, 5);
        Instruction instruction4 = new MovInstruction(null, EDX, 1);
        Instruction instruction5 = new AddInstruction("f1", EAX, EBX);
        Instruction instruction6 = new SubInstruction(null, ECX, EDX);
        Instruction instruction7 = new JnzInstruction(null, ECX, "f1");
        Instruction instruction8 = new OutInstruction(null, EAX);
        List<Instruction> program = machine.getProgram();
        program.add(instruction1);
        program.add(instruction2);
        program.add(instruction3);
        program.add(instruction4);
        program.add(instruction5);
        program.add(instruction6);
        program.add(instruction7);
        program.add(instruction8);
        machine.execute();
        Assertions.assertEquals("23", outContent.toString());
    }

    @Test
    void executeValidZeroLoops() {
        labels.addLabel("f1", 4);
        Instruction instruction1 = new MovInstruction(null, EAX, 3);
        Instruction instruction2 = new MovInstruction(null, EBX, 4);
        Instruction instruction3 = new MovInstruction(null, ECX, 1);
        Instruction instruction4 = new MovInstruction(null, EDX, 1);
        Instruction instruction5 = new AddInstruction("f1", EAX, EBX);
        Instruction instruction6 = new SubInstruction(null, ECX, EDX);
        Instruction instruction7 = new JnzInstruction(null, ECX, "f1");
        Instruction instruction8 = new OutInstruction(null, EAX);
        List<Instruction> program = machine.getProgram();
        program.add(instruction1);
        program.add(instruction2);
        program.add(instruction3);
        program.add(instruction4);
        program.add(instruction5);
        program.add(instruction6);
        program.add(instruction7);
        program.add(instruction8);
        machine.execute();
        Assertions.assertEquals("7", outContent.toString());
    }

    @Test
    void toStringValid(){
        labels.addLabel("f1", 1);
        registers.set(EAX, 5);
        Instruction instruction = new JnzInstruction(null, EAX, "f1");
        String ActualString = instruction.toString();
        String ExpectedString = "jnz EAX f1";
        Assertions.assertEquals(ExpectedString, ActualString);
    }

    @Test
    void toStringValidLabel(){
        labels.addLabel("f1", 1);
        labels.addLabel("f2", 2);
        registers.set(EAX, 5);
        Instruction instruction = new JnzInstruction("f2", EAX, "f1");
        String ActualString = instruction.toString();
        String ExpectedString = "f2: jnz EAX f1";
        Assertions.assertEquals(ExpectedString, ActualString);
    }

    @Test
    void equalsValid(){
        labels.addLabel("f1", 1);
        labels.addLabel("f2", 1);
        registers.set(EAX, 5);
        registers.set(EBX, 5);
        Instruction instruction1 = new JnzInstruction(null, EAX, "f1");
        Instruction instruction2 = new JnzInstruction(null, EBX, "f2");
        Assertions.assertTrue(instruction1.equals(instruction2));
    }

    @Test
    void equalsInvalid(){
        labels.addLabel("f1", 1);
        registers.set(EAX, 5);
        registers.set(EBX, 6);
        registers.set(ECX, 4);
        Instruction instruction1 = new JnzInstruction(null, EAX, "f1");
        Instruction instruction2 = new SubInstruction(null, EBX, ECX);
        Assertions.assertFalse(instruction1.equals(instruction2));
    }

    @Test
    void hashCodeValid() {
        labels.addLabel("f1", 1);
        registers.set(EAX, 5);
        Instruction instruction = new JnzInstruction(null, EAX, "f1");
        Assertions.assertEquals(117, instruction.hashCode());
    }

    @Test
    void hashCodInvalid() {
        labels.addLabel("f1", 1);
        registers.set(EAX, 5);
        Instruction instruction = new JnzInstruction(null, EAX, "f1");
        Assertions.assertNotEquals(34875634, instruction.hashCode());
    }
}