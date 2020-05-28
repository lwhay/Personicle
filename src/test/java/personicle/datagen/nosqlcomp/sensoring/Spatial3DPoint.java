package personicle.datagen.nosqlcomp.sensoring;

public class Spatial3DPoint {
    private Double x;
    private Double y;
    private Double z;

    public Spatial3DPoint(Double x, Double y, Double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Spatial3DPoint() {
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getZ() {
        return z;
    }

    public void setZ(Double z) {
        this.z = z;
    }
}
