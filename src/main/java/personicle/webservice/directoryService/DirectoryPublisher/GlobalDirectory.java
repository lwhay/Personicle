package personicle.webservice.directoryService.DirectoryPublisher;

import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.nio.file.Paths;

public class GlobalDirectory {
    public String getRootPath() {
        return rootPath;
    }

    public void setRootPath(String directory) {
        this.rootPath = directory;
    }

    public enum DirectoryRequestType {
        Global, User, Causality, Event, Attribute, Observation
    }

    private String rootPath;

    private JSONObject directory;

    private String maxSubPrefix(File[] listOfFiles) {
        int maxPathLen = 0;
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].getName().length() > maxPathLen) {
                maxPathLen = listOfFiles[i].getName().length();
            }
        }
        String append = "+";
        for (int i = 1; i < maxPathLen; i++)
            append += " ";
        return append;
    }

    private void nicePrinter(String path, String prefix) {
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();

        String append = maxSubPrefix(listOfFiles);
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                System.out.println(prefix + listOfFiles[i].getName());
            } else if (listOfFiles[i].isDirectory()) {
                System.out.println(prefix + listOfFiles[i].getName());
                String subpref = prefix + append;
                nicePrinter(listOfFiles[i].getAbsolutePath(), subpref);
            }
        }
    }

    private String information(String path, String prefix) {
        String currentInfo = "";
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        String subpref = prefix + maxSubPrefix(listOfFiles);

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                currentInfo += (prefix + listOfFiles[i].getName() + "\n");
            } else if (listOfFiles[i].isDirectory()) {
                currentInfo += (prefix + listOfFiles[i].getName() + "\n");
                currentInfo += information(listOfFiles[i].getAbsolutePath(), subpref);
            }
        }
        return currentInfo;
    }

    public String getInformation() {
        return information(rootPath, "");
    }

    public GlobalDirectory() {
        rootPath = Paths.get("schema-stage2").toAbsolutePath().toString();
        //nicePrinter(rootPath, "");
    }

    public static void main(String[] args) {
        GlobalDirectory gd = new GlobalDirectory();
        System.out.println(gd.getInformation());
    }
}
