import Action.CorrectValue;
import Action.EnteredValue;
import Action.ExitValue;
import Action.InCorrectValue;
import file.FileReader;
import file.CSVFileReader;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.Set;


public class Count {



    private static final String QUIT_SYMBOL = "q";
    Scanner scanner = new Scanner(System.in);
    String countId;


    public String getValue(String countId) throws IOException {

        this.countId = countId;

        FileReader fileReader = new CSVFileReader();
        Map<Integer, BigDecimal> loadedPrices = fileReader.readData("price_per_"+countId+".csv");
        if (loadedPrices == null || loadedPrices.isEmpty()) {
            throw new FileNotFoundException("File with prices per "+countId+" not found.");
        }
        System.out.println(loadedPrices);

        EnteredValue weightValue =
                receiveValueFromConsole("Enter distance in km or 'q' for quit", scanner);
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

