package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.Instruction;
import sml.Machine;
import sml.Registers;

import static sml.Registers.Register.*;

class AddInstructionTest {
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
    registers.set(EAX, 5);
    registers.set(EBX, 6);
    Instruction instruction = new AddInstruction(null, EAX, EBX);
    instruction.execute(machine);
    Assertions.assertEquals(11, machine.getRegisters().get(EAX));
}

  @Test
  void executeValidTwo() {
    registers.set(EAX, -5);
    registers.set(EBX, 6);
    Instruction instruction = new AddInstruction(null, EAX, EBX);
    instruction.execute(machine);
    Assertions.assertEquals(1, machine.getRegisters().get(EAX));
  }

  @Test
  void executeValidThree(){
    registers.set(EAX, 0);
    registers.set(EBX, 0);
    Instruction instruction = new AddInstruction(null, EAX, EBX);
    instruction.execute(machine);
    Assertions.assertEquals(0, machine.getRegisters().get(EAX));
  }

  @Test
  void toStringValid(){
    registers.set(EAX, 5);
    registers.set(EBX, 6);
    Instruction instruction = new AddInstruction(null, EAX, EBX);
    String ActualString = instruction.toString();
    String ExpectedString = "add EAX EBX";
    Assertions.assertEquals(ExpectedString, ActualString);
  }

  @Test
  void toStringValidLabel(){
    registers.set(EAX, 5);
    registers.set(EBX, 6);
    Instruction instruction = new AddInstruction("f1", EAX, EBX);
    String ActualString = instruction.toString();
    String ExpectedString = "f1: add EAX EBX";
    Assertions.assertEquals(ExpectedString, ActualString);
  }

  @Test
  void equalsValid(){
    registers.set(EAX, 5);
    registers.set(EBX, 6);
    registers.set(ECX, 4);
    registers.set(EDX, 7);
    Instruction instruction1 = new AddInstruction(null, EAX, EBX);
    Instruction instruction2 = new AddInstruction(null, ECX, EDX);
    Assertions.assertTrue(instruction1.equals(instruction2));
  }
  @Test
  void hashCodeValid() {
    registers.set(EAX, 5);
    registers.set(EBX, 6);
    Instruction instruction1 = new AddInstruction(null, EAX, EBX);
    Assertions.assertEquals(111, instruction1.hashCode());
  }

  @Test
  void hashCodInvalid() {
    registers.set(EAX, 5);
    registers.set(EBX, 6);
    Instruction instruction1 = new AddInstruction(null, EAX, EBX);
    Assertions.assertNotEquals(112, instruction1.hashCode());
  }
}