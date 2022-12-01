package Labs.Lab_2;

public class Point3d extends Point2d {

    private double zCoord;

    public Point3d (double x, double y, double z) {
        super(x,y);
        zCoord = z;
    }

    public Point3d () {
        this(0.0, 0.0, 0.0);
    }

    public double getZ() {
        return zCoord;
    }

    public void setZ(double val) {
        zCoord = val;
    }

    public boolean equals(Point3d point3d) {

        if (Double.compare(point3d.getX(), getX()) != 0) return false;
        if (Double.compare(point3d.getY(), getY()) != 0) return false;
        return Double.compare(point3d.getZ(), getZ()) == 0;
    }

    public double distanceTo(Point3d point3d) {
        return Math.sqrt(Math.pow(point3d.getX() - this.getX(), 2) + Math.pow(point3d.getY() - this.getY(), 2)
                + Math.pow(point3d.getZ() - this.getZ(), 2));
    }
}

