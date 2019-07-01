import Action.CorrectValue;
import Action.EnteredValue;
import Action.ExitValue;
import Action.InCorrectValue;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;


public class ValueFromKeyboard {

    private static final String QUIT_SYMBOL = "q";
    Scanner scanner = new Scanner(System.in);
    String countId;

    public String getValue(String countId) throws IOException {
        this.countId = countId;

        EnteredValue weightValue =
                receiveValueFromConsole("Enter "+ countId +" or 'q' for quit", scanner);
        if (weightValue.getType() != EnteredValue.Types.EXIT) {
            CorrectValue correctValue = (CorrectValue) weightValue;
            return String.valueOf(correctValue.getValue());
        }
        return "Bye!";
    }

    private EnteredValue receiveValueFromConsole(String message, Scanner scanner) {
        EnteredValue enteredValue = new InCorrectValue();
        while (enteredValue.getType() == EnteredValue.Types.INCORRECT) {
            System.out.println(message);
            enteredValue = parseInputValue(scanner.nextLine());
        }
        return enteredValue;
    }

    private EnteredValue parseInputValue(String inputValue) {
        EnteredValue result = new ExitValue();
        if (!inputValue.trim().toLowerCase().equals(QUIT_SYMBOL)) {
            try {
                BigDecimal value = new BigDecimal(inputValue);
                if (value.compareTo(BigDecimal.ZERO) > 0) {
                    result = new CorrectValue(new BigDecimal(inputValue));
                } else {
                    throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException e) {
                System.out.format("Incorrect value: %s%n", inputValue);
                result = new InCorrectValue();
            }
        }
        return result;
    }
}