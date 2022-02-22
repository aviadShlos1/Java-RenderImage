package primitives;
import primitives.Double3;
import java.util.Objects;

public class Point
{
    protected final Double3 xyz;

    public Point(double x, double y, double z)
    {
        xyz= new Double3(x,y,z);
        if (xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("Vector 0");
    }

    public Point add(Vector v)
    {
        return new Point(this.xyz.d1+v.xyz.d1,this.xyz.d2+v.xyz.d2,this.xyz.d3+v.xyz.d3) ;
    }

    public Vector subtract(Point p1)
    {
        return new Vector(this.xyz.d1-p1.xyz.d1,this.xyz.d2-p1.xyz.d2,this.xyz.d3-p1.xyz.d3 );
    }

    public double distanceSquared(Point p1)
    {
        return Math.abs( (this.xyz.d1-p1.xyz.d1)*(this.xyz.d1-p1.xyz.d1) +
                         (this.xyz.d2-p1.xyz.d2)*(this.xyz.d2-p1.xyz.d2) +
                         (this.xyz.d3-p1.xyz.d3)*(this.xyz.d3-p1.xyz.d3) );
    }

    public double distance(Point p1)
    {
      return Math.sqrt(distanceSquared(p1));
    }





    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Point)) return false;
        Point other = (Point) obj;
        return Objects.equals(xyz, other.xyz);
    }

    @Override
    public String toString() {
        return "Point{" +
                "xyz=" + xyz +
                '}';
    }
}
