import file.CSVFileReader;
import file.FileReader;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

public class CountResult {



    int priceperkm;
    String countId;


    public String getValue(String countId) {
        this.countId = countId;

        FileReader fileReader = new CSVFileReader();
        Map<Integer, BigDecimal> loadedPrices = fileReader.readData("price_per_" + countId + ".csv");
        if (loadedPrices == null || loadedPrices.isEmpty()) {
            throw new FileNotFoundException("File with prices per " + countId + " not found.");
        }
    }

    public BigDecimal countPeriod(BigDecimal enteredValue) {


        private Map<Object, Object> loadedPrices;
        Set<BigDecimal> keys = loadedPrices.keySet();
        for (BigDecimal key : keys) {
            if (enteredValue < key)priceperkm = key;
        }
        System.out.println(priceperkm);
    }
}
