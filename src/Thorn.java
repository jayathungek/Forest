import java.lang.Math;

public class Thorn{
    private static double BASE_SIZE = 0.025;

    public Thorn(){};

    private static double[] rotate(double[] point,double[] origin_point, double theta){
        double px = point[0];
        double py = point[1];
        double ox = origin_point[0];
        double oy = origin_point[1];
        double rx = (Math.cos(theta)*(px-ox)) - (Math.sin(theta)*(py-oy)) + ox;  // Amazing magical  ||  DON'T
        double ry = (Math.sin(theta)*(px-ox)) + (Math.cos(theta)*(py-oy)) + oy;  // maths stuff      ||  TOUCH!
        double[] result = {rx,ry};
        return result;
    }


    public static void drawThorn(double x, double y, double angle){
        double[] point1 = {x,  y};
        double[] point2 = {x+BASE_SIZE, y};
        double[] point3 = {x+(BASE_SIZE/2),y+(BASE_SIZE/2)};
        double[] new_point2 = rotate(point2, point1,angle);
        double[] new_point3 = rotate(point3, point1,angle);
        double[] thorn_x = {x, new_point2[0], new_point3[0]};
        double[] thorn_y = {y, new_point2[1], new_point3[1]};
        StdDraw.filledPolygon(thorn_x,thorn_y);
    }


    public static void main(String[] args){
        //drawThorn(0.45,0.675,45);
    }
}

/*
If you rotate point (px, py) around point (ox, oy) by angle theta you'll get:
p'x = cos(theta) * (px-ox) - sin(theta) * (py-oy) + ox
p'y = sin(theta) * (px-ox) + cos(theta) * (py-oy) + oy
*/