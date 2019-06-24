import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Properties;
import java.util.Scanner;

public class Count {

    private Scanner scanner;
    private Properties properties;

    public Count() throws IOException {
        this.scanner = new Scanner(System.in);
        this.properties = loadProperties();
    }

    private Properties loadProperties() throws IOException {
        InputStream input = Main.class.getClassLoader().getResourceAsStream("price.properties");
        Properties property = new Properties();
        property.load(input);
        return property;
    }

    public String getSum() {
        System.out.println("Enter the weight in kilograms or 'q' to exit");
        BigDecimal weight = getValue();

        System.out.println("Enter the distance in kilometers or 'q' to exit");
        BigDecimal distance = getValue();
        return sum( weight, distance);
    }


    private BigDecimal getValue() {
        BigDecimal value = null;
        String inputStream;
        while (value == null) {
            inputStream = scanner.nextLine();
            if (!inputStream.toLowerCase().equals("q")) {
                try {
                    value = new BigDecimal(inputStream);
                } catch (NumberFormatException e) {
                    System.out.println("You entered wrong value, try again or enter 'q' to exit");
                }
            } else {
                System.out.println("Buy");
                break;
            }
        }
        return value;
    }

    private String sum(BigDecimal weight, BigDecimal distance) {
        BigDecimal pricePerKm;
        BigDecimal pricePerKg = new BigDecimal(properties.getProperty("price.per.kg"));
        if (distance.compareTo(new BigDecimal(100)) == 1) {
            pricePerKm = new BigDecimal(properties.getProperty("lessthan100"));
        } else {
            pricePerKm = new BigDecimal(properties.getProperty("morethan100"));
        }
        return weight.multiply(pricePerKg).add(distance.multiply(pricePerKm)).toString();
    }
}

