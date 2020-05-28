package personicle.datagen.nosqlcomp.commonFileMeasurement;

import personicle.datagen.nosqlcomp.GeneralMeasurement;

public class CommonFileMeasurement extends GeneralMeasurement {
    private String file_category;
    private String filepath;
    private String comments;


    public String getFile_category() {
        return file_category;
    }

    public void setFile_category(String file_category) {
        this.file_category = file_category;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

}
