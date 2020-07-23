package medicine;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class ImageIngestion {
    private static String default_image_root = "E:/Tools/ADNI/";

    public static void main(String[] args) {
        if (args.length > 0)
            default_image_root = args[0];
        try (Stream<Path> paths = Files.walk(Paths.get(default_image_root))) {
            paths.forEach((path) -> {
                if (path.getFileName().endsWith("png")) {
                    File[] files = path.toFile().listFiles();
                    String list = "[";
                    String meta = "";
                    for (File file : files) {
                        if (file.getPath().endsWith("png")) {
                            list += "\"";
                            list += file.getPath().replaceFirst("E:\\\\Tools\\\\ADNI", "");
                            list += "\",";
                        } else if (file.getPath().endsWith("txt")) {
                            try {
                                BufferedReader reader = new BufferedReader(new FileReader(file));
                                String line;
                                boolean unit = false;
                                while ((line = reader.readLine()) != null) {
                                    line = line.trim();
                                    meta += line;
                                    meta += "\n";
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    list = list.trim().substring(0, list.trim().length() - 1);
                    list += "]";

                    System.out.println(list);
                    System.out.println(meta);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        String test = "E:\\test";
        String prefix = "E:\\\\";
        System.out.println(test.replaceFirst(prefix, ""));
    }
}
