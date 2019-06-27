

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Set;


public class Main {


    public static void main(String[] args) throws IOException{

        Count count = new Count();
        BigDecimal enteredValue = new BigDecimal(count.getValue("kg"));
        System.out.println(enteredValue);
        BigDecimal benteredValue = new BigDecimal(count.getValue("km"));
        System.out.println(benteredValue);






    }
}
