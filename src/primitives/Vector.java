package primitives;

public class Vector extends Point
{
    public Vector(double x, double y, double z) throws IllegalArgumentException{
        super(x,y,z);
        xyz=new Double3(x,y,z);
    }

    public Vector(Double3 dbl) throws IllegalArgumentException {
        super(dbl);
    }

    public double lengthSquared() {
        return xyz.d1 * xyz.d1 + xyz.d2* xyz.d2 + xyz.d3* xyz.d3;
    }

    public double length() {
        return Math.sqrt(lengthSquared());
    }

    public Vector add(Vector myVec) {
        return new Vector(this.xyz.add(myVec.xyz));
    }

    public Vector scale(double scalar)
    {
        return new Vector(this.xyz.scale(scalar));
    }

    public double dotProduct(Vector myVec) {
        return this.xyz.d1*myVec.xyz.d1 +this.xyz.d2*myVec.xyz.d2 +this.xyz.d3*myVec.xyz.d3;
    }

    public Vector crossProduct(Vector myVec) {
       return new Vector(this.xyz.d2*myVec.xyz.d3-this.xyz.d3*myVec.xyz.d2,
                        this.xyz.d3*myVec.xyz.d1-this.xyz.d1*myVec.xyz.d3,
                        this.xyz.d1*myVec.xyz.d2-this.xyz.d2*myVec.xyz.d1);
    }

    public Vector normalize() {
        Double3 p = xyz.scale(length());
        return new Vector(p.d1,p.d2,p.d3);
    }

    @Override
    public String toString() {
        return "Vector{}";
    }

}
