import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DayTwo {
    
   public static void main(String[] args) throws IOException {
    // Your code goes here

    System.out.println("The result for Day 2 is: " + getSumValidGames());
    }

    public static Integer getSumValidGames() throws IOException {
        Integer sumValue = 0;
        HashMap<Integer, Boolean> gameResults = new HashMap<>();

        final Integer redLimit = 12;
        final Integer blueLimit = 14;
        final Integer greenLimit = 13;

        // Reading the txt file and storing each line
        List<String> lines = Files.readAllLines(Paths.get("input.txt"));

        lines.forEach(line -> {
            Pattern gamePattern = Pattern.compile("Game (\\d+):");
            Matcher gameMatcher = gamePattern.matcher(line);
        
            int gameId = 0;
            if (gameMatcher.find()) {
                gameId = Integer.parseInt(gameMatcher.group(1));
            }

            Boolean cubeValidity = Boolean.TRUE;
            String[] reveals = line.substring(line.indexOf(':') + 1).trim().split(";");

            for(String reveal : reveals) {
        
                HashMap<String, Integer> cubeCounts = new HashMap<>();
                cubeCounts.put("red", 0);
                cubeCounts.put("green", 0);
                cubeCounts.put("blue", 0);
            
                Pattern cubePattern = Pattern.compile("(\\d+) (red|green|blue)");
                Matcher cubeMatcher = cubePattern.matcher(reveal);
            
                while (cubeMatcher.find()) {
                    int count = Integer.parseInt(cubeMatcher.group(1));
                    String color = cubeMatcher.group(2);
                    cubeCounts.put(color, count);
                }

                if (cubeCounts.getOrDefault("red", 0) > redLimit
                    || cubeCounts.getOrDefault("blue", 0) > blueLimit
                    || cubeCounts.getOrDefault("green", 0) > greenLimit) {
                    cubeValidity = false;
                    break;
                }
            }
        
            gameResults.put(gameId, cubeValidity);
            System.out.println("Game " + gameId + ": " + cubeValidity);
        });

        sumValue = gameResults.entrySet().stream()
                                        .filter(Map.Entry::getValue)
                                        .mapToInt(Map.Entry::getKey) 
                                        .peek(System.out::println)
                                        .sum();

        return sumValue;
    }
}
