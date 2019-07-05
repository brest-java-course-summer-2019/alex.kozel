import file.CSVFileReader;
import file.FileReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ValueFromFile {
    BigDecimal price = null;
    String countId;
    BigDecimal number;


    public BigDecimal getValue(String countId, BigDecimal number) throws IOException {
        this.countId = countId;
        this.number = number;

        FileReader fileReader = new CSVFileReader();
        Map<Integer, BigDecimal> loadedPrices = fileReader.readData("price_per_" + countId + ".csv");
        if (loadedPrices == null || loadedPrices.isEmpty()) {
            throw new FileNotFoundException("File with prices per " + countId + " not found.");
        }
        List<Integer> list = new ArrayList<>(loadedPrices.keySet());
        System.out.println(countId + list);
        for (Integer key : list) {
            if (number.compareTo(BigDecimal.valueOf(key)) < 0) {
                price = loadedPrices.get(key);
                break;
            } else price = loadedPrices.get(list.get(list.size() - 1));
        }
        return price;
    }
}
