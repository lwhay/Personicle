package personicle.datagen.nosqlcomp.food;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AMDuplicateVerification {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("./example/FoodAttribute.adm"));
        Map<String, Integer> lineToNum = new HashMap<>();
        String line;
        int ln = 0;
        while ((line = br.readLine()) != null) {
            if (lineToNum.containsKey(line)) {
                System.out.println(ln + " == " + lineToNum.get(line));
                break;
            } else {
                lineToNum.put(line, ln);
            }
            ln++;
        }
        br.close();
    }
}
