package medicine;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SZSimMedicine {
    private static String[] names = {
            "新安街道办事处",
            "松岗街道办事处",
            "沙井街道办事处",
            "石岩街道办事处",
            "福永街道办事处",
            "西乡街道办事处"
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("./example/medicine.csv"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("./example/szsimmedicine.csv"));
        String line;
        // System.out.println("Randomizing");
        Map<String, Integer> mappingcode = new HashMap<>();
        Random rand = new Random();
        int count = 0;
        int columns = 0;
        while ((line = br.readLine()) != null) {
            String[] fields = line.split(",");
            if (!mappingcode.containsKey(fields[0])) {
                mappingcode.put(fields[0], (int) (Math.abs((fields[0].hashCode() % names.length) * rand.nextGaussian())) % names.length);
            }
            line = "";
            System.out.print(fields.length + "\t");
            if (fields.length > columns) columns = fields.length;
            for (int i = 0; i < fields.length; i++) {
                if (i == 0)
                    line += names[mappingcode.get(fields[i])];
                else
                    line += fields[i];
                line += ",";
                if (i == fields.length - 1) {
                    while (i++ < 287) line += ",";
                    double year = rand.nextGaussian();
                    while (year < 0) year = rand.nextGaussian();
                    line += Math.abs(2020 - ((int) (Math.abs(year * 10)) % 20));
                    count++;
                    line += "\n";
                }
                // System.out.println(rand.nextGaussian());
            }
            fields = line.split(",");
            System.out.print(fields.length + "\t" + line);
            // System.out.print(line);
            bw.write(line);
            // System.out.println(rand.nextGaussian() + "," + names[0].hashCode() + "," + names[1].hashCode() + "," + "新安街道办事处".hashCode() + "," + names[mappingcode.get(fields[1])]);
        }
        System.out.println(count + "\t" + columns);
        br.close();
        bw.close();
    }
}
