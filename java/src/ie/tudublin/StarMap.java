package ie.tudublin;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;

public class StarMap extends PApplet {

    //Arraylist of Stars
    ArrayList<Star> stars = new ArrayList<Star>();
    
    public float border;

    //Drawing grid
    void drawGrid()
    {
        //grid colour
        stroke(255, 0, 255);
        textAlign(CENTER, CENTER);
        textSize(20);
        //Loop from -5 to 5
        for(int i = -5; i <=5; i ++)
        {
            float x = map(i, -5, 5, border, width - border);
            //x axis
            line(x, border, x, height - border);
            //y axis
            line(border, x, width - border, x);
            fill(255);
            //x axis text
            text(i, x, border * 0.5f);
            //y axis text
            text(i, border * 0.5f, x);
        }
    }

    void printStars()
    {
        //For each loop of the array list to print from the toString method of the class
        for(Star s:stars)
        {
            System.out.println(s);
        }
    }

    void loadStars()
    {
        //Loading the csv into the rows of the star class and then populating the array list with objects
        Table table = loadTable("HabHYG15ly.csv", "header");
        for(TableRow r:table.rows())
        {
            Star s = new Star(r);
            stars.add(s);
        }
    }

    //Size of window
    public void settings() {
        size(800, 800);
    }

    //Parameters for the first and second mouse press
    Star first = null;
    Star second = null;



    public void mouseClicked()
    {
        //each star in the array list
        for(Star s:stars)
        {
            //mapping the (x of star)
            float x = map(s.getxG(), -5, 5, border, width - border);
            //y of star
            float y = map(s.getyG(), -5, 5, border, height - border);

            //if distance of mouse click and (x-y so the start ) is less then 20 and 20 is the diamter of the circle
            if (dist(mouseX, mouseY, x, y) < 20)
            {
                if (first == null)
                {
                    //Set first start varibale as the current star
                    first = s;
                    break;
                }
                else if (second == null)
                {
                    //second star
                    second = s;
                    break;
                } 
                else
                {
                    first = s;
                    second = null;
                    break;
                }
            }
        }
    }

    //Colour mode and loading mehtods
    public void setup() {
        colorMode(RGB);
        loadStars();
        printStars();

        //Border for printing 10% of the width of the screen
        border = width * 0.1f;
    }

    public void drawStars()
    {
        //classing the render method in the star class for each start passing in this instance of this class
        for(Star s:stars)
        {
            s.render(this);
        }
    }

    public void draw() 
    {

        //Calling methods and creating the background as black
        background(0);
        drawGrid();
        drawStars();

        if (first != null)
        {

            //mapping the x and y
            float x = map(first.getxG(), -5, 5, border, width - border);
            float y = map(first.getyG(), -5, 5, border, height - border);

            if (second != null)
            {
                //second click mapping
                float x2 = map(second.getxG(), -5, 5, border, width - border);
                float y2 = map(second.getyG(), -5, 5, border, height - border);

                //Colour of line
                stroke(255, 255, 0);
                //mapping from first click to second
                line(x, y, x2, y2);

                //calculating distance
                float dist = dist(first.getxG(), first.getyG(), first.getzG(), second.getxG(), second.getyG(), second.getzG());

                fill(255);
                textAlign(CENTER, CENTER);
                text("Distance between: " + first.getDisplayName() + " and " + second.getDisplayName() + " is " + dist + " parsecs", width / 2, height - (border * 0.5f));
            }
            else
            {
                //if second isnt pressed map to mouse
                stroke(255, 255, 0);
                line(x, y, mouseX, mouseY);
            }
        }
    }
}