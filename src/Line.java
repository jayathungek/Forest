import java.lang.Math;

public class Line{
    public double x1;
    public double y1;
    public double length;
    public double angle;



    public Line(double x1, double y1, double length, double angle){
        
        angle = angle*( Math.PI/180 );

    	this.x1 = x1;
	    this.y1 = y1;
	    this.length = length;
	    this.angle = angle;
    
    }

    
    public static double[] getEndCoords(Line line){


    	double opp = ( line.length )*( Math.sin(line.angle) );
    	double adj = ( line.length )*( Math.cos(line.angle) );

        double x2 = line.x1+adj;
    	double y2 = line.y1+opp;

    	double[] values = {x2,y2};

    	return values;     
    }

    public static void draw(Line line){
    	double[] coords = getEndCoords(line);
    	double x2 = coords[0];
    	double y2 = coords[1];
    	StdDraw.line(line.x1,line.y1,x2,y2);
    }



	public static void main(String[] args){
	}
}