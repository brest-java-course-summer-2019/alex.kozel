package com.epam.brest2019.courses;

import com.epam.brest2019.courses.calc.Calculator;
import com.epam.brest2019.courses.files.FileReader;
import com.epam.brest2019.courses.menu.CorrectValue;
import com.epam.brest2019.courses.menu.EnteredValue;
import com.epam.brest2019.courses.menu.ExitValue;
import com.epam.brest2019.courses.menu.IncorrectValue;
import com.epam.brest2019.courses.selector.ValueSelector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Scanner;

@Component
public class Main {

    private static final String QUIT_SYMBOL = "q";

    private static final String FILE_KG_PRICES = "price_per_kg.csv";
    private static final String FILE_KM_PRICES = "price_per_km.csv";

    public static void main(String[] args) throws IOException {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("context.xml");
        Main main = applicationContext.getBean(Main.class);
        main.run();
    }

    @Value("${weight.message}")
    private String messageWeight;

    @Value("${distance.message}")
    private String messageDistance;

    private FileReader fileReader;

    private Calculator calculator;

    private ValueSelector selector;

    public Main(FileReader fileReader, Calculator calculator, ValueSelector selector) {
        this.fileReader = fileReader;
        this.calculator = calculator;
        this.selector = selector;
    }

    private void run() throws IOException {

        Scanner scanner = new Scanner(System.in);

        Map<Integer, BigDecimal> weightPrices = fileReader.readData(FILE_KG_PRICES);
        if (weightPrices == null || weightPrices.isEmpty()) {
            throw new FileNotFoundException("File with prices per kg not found.");
        }

        Map<Integer, BigDecimal> distancePrices = fileReader.readData(FILE_KM_PRICES);
        if (distancePrices == null || distancePrices.isEmpty()) {
            throw new FileNotFoundException("File with prices per km not found.");
        }

        BigDecimal weight;
        BigDecimal distance;
        EnteredValue enteredValue;
        do {
            System.out.println("==========================================================");
            enteredValue = receiveValueFromConsole(messageWeight, scanner);
            if (enteredValue.getType() != EnteredValue.Types.EXIT) {
                weight = ((CorrectValue) enteredValue).getValue();
                enteredValue = receiveValueFromConsole(messageDistance, scanner);
                if (enteredValue.getType() != EnteredValue.Types.EXIT) {
                    distance = ((CorrectValue) enteredValue).getValue();

                    BigDecimal deliveryCost = calculator.calc(weight, distance,
                            selector.selectValue(weightPrices, weight),
                            selector.selectValue(distancePrices, distance));

                    System.out.println("Delivery cost: " + deliveryCost);
                }
            }
        } while (enteredValue.getType() != EnteredValue.Types.EXIT);
        System.out.println("Bye!");
    }

    private EnteredValue receiveValueFromConsole(String message, Scanner scanner) {
        EnteredValue enteredValue = new IncorrectValue();
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
                result = new IncorrectValue();
            }
        }
        return result;
    }
}
