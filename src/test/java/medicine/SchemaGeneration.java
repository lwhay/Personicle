package medicine;

import net.duguying.pinyin.Pinyin;
import net.duguying.pinyin.PinyinException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SchemaGeneration {

    public static void main(String[] args) throws IOException {
        Pinyin engine = new Pinyin();
        String angry = "（中国NB1）";
        String string = "（中国NB1）".replace("（", "").replace("）", "");
        System.out.println(angry + "<->" + engine.translateNoMark(angry));
        System.out.println(string + "<->" + engine.translateNoMark(string));

        BufferedReader br = new BufferedReader(new FileReader("./example/huanghua.txt"));
        String line;
        System.out.println("create type HuanghuaSchema as closed {");
        while ((line = br.readLine()) != null) {
            String[] fields = line.split("\t");
            if (fields[1].trim().equals("字符串") || fields[1].trim().equals("日期")) {
                System.out.println(engine.translateNoMark(fields[0].replace(".", "").replace("、", "").replace("（", "").replace("）", "")).replace("ü", "u").replace("��", "o").replace("，", "") + ": string?,");
            } else if (fields[1].trim().equals("数字")) {
                if (!fields[3].trim().equals("0")) {
                    System.out.println(engine.translateNoMark(fields[0].replace(".", "").replace("、", "").replace("（", "").replace("）", "")).replace("ü", "u").replace("��", "o").replace("，", "") + ": float?,");
                } else {
                    System.out.println(engine.translateNoMark(fields[0].replace(".", "").replace("、", "").replace("（", "").replace("）", "")).replace("ü", "u").replace("��", "o").replace("，", "") + ": int?,");
                }
            }
        }
        System.out.println("};");
        br.close();
    }
}
