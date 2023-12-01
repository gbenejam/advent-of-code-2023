import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class DayOne {

   public static void main(String[] args) throws IOException {
        // Your code goes here

        System.out.println("The result for Day 1 is: " + getCalibrationValue());
    }

    public static Integer getCalibrationValue() throws IOException {
        Integer calibrationValue = 0;
        
        // Reading the txt file and storing each line
        List<String> lines = Files.readAllLines(Paths.get("input.txt"));
        
        List<String> maskedCalibration = lines.stream()
        .map(item -> {
            String first = item.replaceAll(".*?(\\d).*", "$1"); // We go through the String until the first digit
            String last = new StringBuilder(item).reverse().toString().replaceAll(".*?(\\d).*", "$1"); // We reverse the string and then we go through the String until the first digit (which will be the last)
            
            return first + last;
        }) 
        .peek(System.out::println)
        .collect(Collectors.toList());

        //Summing all the values for the calibration value
        calibrationValue = maskedCalibration.stream().mapToInt(Integer::parseInt).sum();

        return calibrationValue;
    }

}
