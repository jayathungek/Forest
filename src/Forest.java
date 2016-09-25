import java.lang.Math;

public class Forest{

    public Line line;
    private static final double max_thickness = 0.05;
    private static final double min_thickness = 0.0008;
    private int num_trees;
    private static float shade_factor;
    private static int[] THORN_COLOUR = {120,20,60};        // some kinda red {220,20,60}
    private static final int[] start_colour = {173,225,47}; //{255,255,255};// light green
    private static final int[] end_colour = {0,30,0};       // dark green (almost black)
    public static int[] current_colour ;


    public Forest(Line line, int num_trees){
        this.line = line;
        this.num_trees = num_trees;
        shade_factor = 1f/num_trees+ 0.01f;
        current_colour = start_colour;
        StdDraw.setPenColor(start_colour[0], start_colour[1], start_colour[2]);
    }


    public static void makeDarker(){
        int newR = Math.round(current_colour[0] * (1 - shade_factor));
        int newG = Math.round(current_colour[1] * (1 - shade_factor));
        int newB = Math.round(current_colour[2] * (1 - shade_factor));
        StdDraw.setPenColor(newR , newG , newB );
        current_colour = new int[]{newR,newG,newB};
    }


    public boolean darkestTreeDrawn(){
        if(this.current_colour[0]<=end_colour[0] || this.current_colour[1]<=end_colour[1] || this.current_colour[2]<=end_colour[2]) return true;
        return false;
    }


    public boolean drawTree(int speed){
        for(double thicc = max_thickness ; thicc > min_thickness; thicc-= min_thickness){

            double direction_change = Math.random()*40*Math.PI/180;
            double thorn_chance = Math.random();
            double sign = Math.random();
            if (sign>0.5) direction_change*=-1;

            double[] next_start = Line.getEndCoords(this.line);
            double x = next_start[0];
            double y = next_start[1];
            if (x>1) x = x-1;
            if (x<0) x = x+1;
            if (y>1) y = y-1;
            if (y<0) y = y+1;
            this.line.angle += direction_change;
            this.line.x1 = x;
            this.line.y1 = y;

            StdDraw.setPenRadius(thicc);
            Line.draw(this.line);

            if(thorn_chance<0.03 && thicc < 0.025){
                StdDraw.setPenRadius(0.005);
                StdDraw.setPenColor(THORN_COLOUR[0],THORN_COLOUR[1],THORN_COLOUR[2]);
                Thorn.drawThorn(x,y,this.line.angle);
                StdDraw.setPenRadius(thicc);
                StdDraw.setPenColor(this.current_colour[0],this.current_colour[1],this.current_colour[2]);
            }
            StdDraw.show(speed);
        }
        //StdDraw.show(speed);
        makeDarker();

        double next_tree = Math.random();
        if(next_tree<0.25)                  this.line.x1 = 0; this.line.y1 = 0;
        if(next_tree>0.25 && next_tree<0.5) this.line.x1 = 1; this.line.y1 = 1;
        if(next_tree>0.5 && next_tree<0.75) this.line.x1 = 1; this.line.y1 = 0;
        if(next_tree>0.75 && next_tree<1)   this.line.x1 = 0; this.line.y1 = 1;

        if (darkestTreeDrawn()) return true;
        return false;
    }


    public static void main(String[] args){
        int speed = Integer.parseInt(args[0]);
        int num_trees = Integer.parseInt(args[1]);
        Line line = new Line(0,0,0.025,90);
        Forest f = new Forest(line,num_trees);
        for (int i = 0; i<num_trees; i++){
            if(f.drawTree(speed)) break;
            f.drawTree(speed);
        }
        StdDraw.setPenColor(0,0,0);
        f.drawTree(speed);
        StdDraw.setPenColor(0,0,0);
        f.drawTree(speed);
    }
}
