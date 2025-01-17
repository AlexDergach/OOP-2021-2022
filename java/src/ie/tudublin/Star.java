package ie.tudublin;

import processing.core.PApplet;
import processing.data.TableRow;

public class Star
{
    //All elements of the cvs file
    private boolean hab;
    private String displayName;
    private float distance;
    private float xG;
    private float yG;
    private float zG;
    private float absMag;

    

    //When output of a string is asked for this class this is printed i.e Sys.out.println()
    @Override
    public String toString() {
        return "Star [absMag=" + absMag + ", displayName=" + displayName + ", distance=" + distance + ", hab=" + hab
                + ", xG=" + xG + ", yG=" + yG + ", zG=" + zG + "]";
    }

    //Getting the rows of each element and inputting it in the table thats imported up above
    public Star(TableRow tr)
    {
        this(
            tr.getInt("Hab?") == 1, 
            tr.getString("Display Name"), 
            tr.getFloat("Distance"),
            tr.getFloat("Xg"),
            tr.getFloat("Yg"),
            tr.getFloat("Zg"),
            tr.getFloat("AbsMag")
        );
    }
    
    //Class constructor
    public Star(boolean hab, String displayName, float distance, float xG, float yG, float zG, float absMag) {
        this.hab = hab;
        this.displayName = displayName;
        this.distance = distance;
        this.xG = xG;
        this.yG = yG;
        this.zG = zG;
        this.absMag = absMag;
    }

    //Getters and Setters
    public boolean isHab() {
        return hab;
    }
    public void setHab(boolean hab) {
        this.hab = hab;
    }
    public String getDisplayName() {
        return displayName;
    }
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    public float getDistance() {
        return distance;
    }
    public void setDistance(float distance) {
        this.distance = distance;
    }
    public float getxG() {
        return xG;
    }
    public void setxG(float xG) {
        this.xG = xG;
    }
    public float getyG() {
        return yG;
    }
    public void setyG(float yG) {
        this.yG = yG;
    }
    public float getzG() {
        return zG;
    }
    public void setzG(float zG) {
        this.zG = zG;
    }
    public float getAbsMag() {
        return absMag;
    }
    public void setAbsMag(float absMag) {
        this.absMag = absMag;
    }

    //Render
    //Parameter is an instance of the StarMap class pa
    public void render(StarMap pa)
    {
        //Map(value, range of value 1, second range, starting from left, ends on right)
        float x = PApplet.map(xG, -5, 5, pa.border, pa.width - pa.border);
        float y = PApplet.map(yG, -5, 5, pa.border, pa.height - pa.border);

        //colour of line
        pa.stroke(255, 255, 0);
        //line(x1,y1,x2,y2)
        pa.line(x, y -5, x, y + 5);
        pa.line(x-5, y, x + 5, y);
        //Circle colour
        pa.stroke(255, 0, 0);
        pa.noFill();
        //x, y, radius
        pa.circle(x, y, absMag);
        pa.fill(255);
        //Text
        pa.textSize(16);
        pa.textAlign(PApplet.LEFT, PApplet.CENTER);
        pa.text(displayName, x + 20, y);
    }
}