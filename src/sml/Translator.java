package sml;

import sml.instruction.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.*;
import java.nio.charset.StandardCharsets;
import java.rmi.AccessException;
import java.util.List;
import java.util.Scanner;

import static sml.Registers.Register;

/**
 * This class ....
 * <p>
 * The translator of a <b>S</b><b>M</b>al<b>L</b> program.
 *
 * @author ...
 */
public final class Translator {

    private final String fileName; // source file of SML code

    // line contains the characters in the current line that's not been processed yet
    private String line = "";

    public Translator(String fileName) {
        this.fileName =  fileName;
    }

    // translate the small program in the file into lab (the labels) and
    // prog (the program)
    // return "no errors were detected"

    public void readAndTranslate(Labels labels, List<Instruction> program) throws IOException {
        try (var sc = new Scanner(new File(fileName), StandardCharsets.UTF_8)) {
            labels.reset();
            program.clear();

            // Each iteration processes line and reads the next input line into "line"
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                String label = getLabel();

                Instruction instruction = getInstruction(label);
                if (instruction != null) {
                    if (label != null)
                        labels.addLabel(label, program.size());
                    program.add(instruction);
                }
            }
        }
    }

    /**
     * Translates the current line into an instruction with the given label
     *
     * @param label the instruction label
     * @return the new instruction
     * <p>
     * The input line should consist of a single SML instruction,
     * with its label already removed.
     */
    private Instruction getInstruction(String label) {
        if (line.isEmpty())
            return null;



        String opcode = scan();
        String r = scan();
        String s = scan();
        String newOpcode = opcode.substring(0, 1).toUpperCase() + opcode.substring(1);
        String instructionString = "sml.instruction." + newOpcode + "Instruction";

        try {
                Class<?> clazz = Class.forName(instructionString);
                Constructor<?> constructor;
                Object instructionObject;
                Instruction instruction;
                try {
                    constructor = clazz.getConstructor(String.class, RegisterName.class);
                    instructionObject = constructor.newInstance(label, Register.valueOf(r));
                    instruction = Instruction.class.cast(instructionObject);
                    return instruction;
                } catch (NoSuchMethodException a) {
                    try {
                        constructor = clazz.getConstructor(String.class, RegisterName.class, RegisterName.class);
                        instructionObject = constructor.newInstance(label, Register.valueOf(r), Register.valueOf(s));
                        instruction = Instruction.class.cast(instructionObject);
                        return instruction;
                    } catch (NoSuchMethodException b) {
                        try {
                            constructor = clazz.getConstructor(String.class, RegisterName.class, int.class);
                            instructionObject = constructor.newInstance(label, Register.valueOf(r), Integer.parseInt(s));
                            instruction = Instruction.class.cast(instructionObject);
                            return instruction;
                        } catch (NoSuchMethodException c) {
                            }try {
                                constructor = clazz.getConstructor(String.class, RegisterName.class, String.class);
                                instructionObject = constructor.newInstance(label, Register.valueOf(r), s);
                                instruction = Instruction.class.cast(instructionObject);
                                return instruction;
                            } catch (NoSuchMethodException d) {
                        }
                    }
                }
            }
            catch (ClassNotFoundException e) {
                System.out.println(e);
            }
            catch (InstantiationException e) {
                System.out.println(e);
            }
            catch (IllegalAccessException e) {
                System.out.println(e);
            }
            catch (InvocationTargetException e) {
                System.out.println(e);
            }
            // TODO: Next, use dependency injection to allow this machine class
            //       to work with different sets of opcodes (different CPUs)
        return null;
    }


    private String getLabel() {
        String word = scan();
        if (word.endsWith(":"))
            return word.substring(0, word.length() - 1);

        // undo scanning the word
        line = word + " " + line;
        return null;
    }

    /*
     * Return the first word of line and remove it from line.
     * If there is no word, return "".
     */
    private String scan() {
        line = line.trim();

        for (int i = 0; i < line.length(); i++)
            if (Character.isWhitespace(line.charAt(i))) {
                String word = line.substring(0, i);
                line = line.substring(i);
                return word;
            }

        return line;
    }
}