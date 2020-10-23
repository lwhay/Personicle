package medicine;

import org.apache.avro.generic.GenericData;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DecisionVerification {
    private static List<List<String>> data = new ArrayList<>();
    private static Map<String, Integer> schema = new HashMap<>();
    private static List<Integer> level = new ArrayList<>();

    private static String verify(String[] fields) {
        String ret;
        if (fields[level.get(0)].trim().equals("否")) {
            if (fields[level.get(1)].trim().equals("至少一次"))
                ret = "1";
            else
                ret = "0";
        } else {
            ret = "0";
        }
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("./example/medicine.csv"));
        String line;
        int count = 0;
        while ((line = br.readLine()) != null) {
            String[] fields = line.split(",");
            if (count++ == 0) {
                for (int i = 0; i < fields.length; i++) {
                    schema.put(fields[i].trim(), i);
                }
                level.add(schema.get("每周是否进行高强度体力活动"));
                level.add(schema.get("近12个月内饮酒频率"));
                level.add(schema.get("饮酒频率4（月）"));
                level.add(schema.get("肿瘤"));
                continue;
            }
            List<String> row = new ArrayList<>();

            for (int i = 0; i < fields.length; i++) {
                //System.out.print(fields[i] + " ");
                row.add(fields[i].trim());
            }
            data.add(row);
            System.out.print(verify(fields) + "," + fields[level.get(3)]);
            System.out.println();
        }
        br.close();
    }
}
