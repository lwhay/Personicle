package medicine;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class ImageIngestion {
    private static String default_image_root = "E:/Tools/ADNI/";
    private static int idx = 0;

    public static void main(String[] args) {
        if (args.length > 0)
            default_image_root = args[0];
        try (Stream<Path> paths = Files.walk(Paths.get(default_image_root))) {
            paths.forEach((path) -> {
                if (path.getFileName().endsWith("png")) {
                    File[] files = path.toFile().listFiles();
                    String list = "[";
                    String meta = "{";
                    String raw = "";
                    for (File file : files) {
                        if (file.getPath().endsWith("png")) {
                            list += "\"";
                            list += file.getPath().replaceFirst("E:\\\\Tools\\\\ADNI", "");
                            list += "\",";
                        } else if (file.getPath().endsWith("txt")) {
                            try {
                                BufferedReader reader = new BufferedReader(new FileReader(file));
                                String line;
                                while ((line = reader.readLine()) != null) {
                                    line = line.trim();
                                    raw += line;
                                    raw += "\n";
                                    if (line.startsWith("data shape")) {
                                        meta += "\"datashape\":" + "[" + line
                                                .substring(line.indexOf('(') + 1, line.indexOf(')')) + "]";
                                    } else if (line.startsWith("affine")) {
                                        meta += ",\"affine\":[";
                                        for (int i = 0; i < 4; i++) {
                                            meta += "[";
                                            line = reader.readLine();
                                            line = line.replaceAll("\\[", "").replaceAll("]", "");
                                            String[] fields = line.split(" ");
                                            for (int j = 0; j < fields.length; j++) {
                                                if (!fields[j].equals(null) && !fields[j].equals("") && !fields[j]
                                                        .equals(" ")) {
                                                    meta += fields[j] + ",";
                                                }
                                            }
                                            meta = meta.substring(0, meta.length() - 1);
                                            meta += "],";
                                        }
                                        meta = meta.substring(0, meta.length() - 1);
                                        meta += "],";
                                    } else if (line.contains(" : ")) {
                                        String[] fields = line.trim().split(":");
                                        String tr = fields[1].replaceAll(" ", "");
                                        if (tr.startsWith("a") || tr.startsWith("b") || tr.startsWith("u") || tr
                                                .startsWith("n") || tr.startsWith("f")) {
                                            meta += "\"" + fields[0].trim() + "\":\"" + fields[1].trim() + "\",";
                                        } else if (tr.startsWith("[")) {
                                            meta += "\"" + fields[0].trim() + "\":";// + fields[1].trim() + "\",";
                                            String[] nums =
                                                    fields[1].replaceAll("\\[", "").replaceAll("]", "").split(" ");
                                            meta += "[";
                                            for (int i = 0; i < nums.length; i++) {
                                                if (!nums[i].equals(" ") && !nums[i].equals("")) {
                                                    meta += nums[i] + "0,";
                                                }
                                            }
                                            meta = meta.substring(0, meta.length() - 1);
                                            meta += "],";
                                        } else {
                                            meta += "\"" + fields[0].trim() + "\":" + fields[1].trim() + ",";
                                        }
                                    }
                                }
                                //meta = meta.substring(0, meta.length() - 1);
                                meta += "\"metadata:\":\"" + file.getPath() + "\"";
                                meta += ",";
                                //System.out.println(idx++ + " " + file.getPath());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    list = list.trim().substring(0, list.trim().length() - 1);
                    list += "]";
                    meta += "\"images\":" + list + "}";
                    meta = meta.replaceAll("\\\\", "\\\\\\\\");

                    //System.out.println("*****" + list);
                    System.out.println(meta);
                    //System.out.println("*****" + raw);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        String test = "E:\\test";
        String prefix = "E:\\\\";
        //System.out.println(test.replaceFirst(prefix, ""));
    }
}
