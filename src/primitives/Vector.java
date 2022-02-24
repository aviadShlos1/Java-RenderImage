package primitives;

public class Vector extends Point
{
    public Vector(double x, double y, double z) {
        super(x,y,z);
    }

    public Vector(Double3 dbl) {super(dbl);}

    public double lengthSquared() {
        return xyz.d1 * xyz.d1 + xyz.d2* xyz.d2 + xyz.d3* xyz.d3;
    }

    public double length() {

        return Math.sqrt(lengthSquared());
    }

    public double dotProduct(Vector v3)
    {
        return xyz.d1*v3.xyz.d1 +this.xyz.d2*v3.xyz.d2 +this.xyz.d3*v3.xyz.d3;
    }

    public Vector crossProduct(Vector v2)
    {
       return new Vector(this.xyz.d2*v2.xyz.d3-this.xyz.d3*v2.xyz.d2,
                        this.xyz.d3*v2.xyz.d1-this.xyz.d1*v2.xyz.d3,
                        this.xyz.d1*v2.xyz.d2-this.xyz.d2*v2.xyz.d1);

    }

    public Vector normalize()
    {
        Double3 p = xyz.scale(length());
        return new Vector(p.d1,p.d2,p.d3);
    }


    @Override
    public String toString() {
        return "Vector{}";
    }


}
