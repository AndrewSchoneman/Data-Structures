
import java.util.*;

public class Cylinder {
   private final double radius;    // doesn't change after Cylinder is constructed   
   private final double height; // doesn't change after Cylinder is constructed

   private double waterHeight;  
   
    /*
     * Constructor 
     * Initially waterHeight = 0
     * 
     * If any value is less than or equal to 0, 
     * set radius and height to 1 and print "ERROR Cylinder"
     */
    public Cylinder(double radius, double height){
        if (radius <= 0){
            System.out.println("ERROR Cylinder"); 
            radius = 1;
        }
        if (height <= 0){
            height = 1; 
            System.out.println("ERROR Cylinder"); 
            }
        this.height = height;
        this.radius = radius; 
        waterHeight = 0;
        
        
    }
    // Constructor
	// set waterHeight to 0
	// set radius and height to 1
    public Cylinder(){
        waterHeight = 0; 
        this.radius = 1;
        this.height = 1; 

    }
    // getRadius: returns the radius of the Cylinder
    public double GetRadius(){
        return radius;
}

    // getHeight: returns the height of the Cylinder
    public double getHeight(){
        return height; 
    }
    
    // getVolume: returns the total volume of the cylinder (divided by PI)
    public double getVolume(){
        return Math.pow(radius, 2) * height; 
                
    }
    // getWaterVolume: returns the volume of water in the cylinder (divided by PI)
        public double getWaterVolume(){
        return   waterHeight * Math.pow(radius, 2);
    }
    
	// toString: returns a String describing the cylinder
	//   for example, for the Cylinder with radius=10, height=12, waterHeight=4
	//   the String will be "Cylinder(volume=1200pi, waterVolume=400pi)",
	// that is the total volume and current water volume
    public String toString() {
        final String pi = "\u03c0";
        return "Cylinder(volume=" + getVolume() + pi + ", waterVolume=" + getWaterVolume() + pi + ")"; 
        
    }

    // pourWaterFrom
	// Move water from other into this Cylinder.
	// If you would overflow this Cylinder, *only* move the volume
	// of water that would fill this Cylinder to the top.
    public void pourWaterFrom(Cylinder other){
        // this conditional is set so that it ensures that the remaining water volume of the source cylinder will be greater than 0
        // and it checks to make sure the source cylinder isn't already full
        if(other.getWaterVolume() - getWaterVolume() > 0 && other.getWaterVolume() - getVolume() > 0 && waterHeight < height){
            // if there is water present in the cylinder then I store it as remainder so I can add it to the source waterHeight calculation
            double remainder = 0;
            // this is the check to see if the cyldiner being poured into  has water in it
            if(this.waterHeight>0){
                remainder = this.getWaterVolume() / Math.pow(other.radius, 2);
            }
            // this is the calculation of the waterheight for the cylinder to be poured into it uses a modifcation
            // of the cylinder volume formula waterheight += difference in the volume of water vs the total volume of the 
            // cylinder waterheight a similar formula for the water height of the source cylinder
            this.waterHeight += ((this.getVolume() -  this.getWaterVolume()) / Math.pow(this.radius, 2)); 
            other.waterHeight = ((other.getWaterVolume() - this.getWaterVolume()) / Math.pow(other.radius,2)) + remainder; 
        }
        // checks to see if the displacement of water from the source cylinder will be less than zero
        else if(other.getWaterVolume() - this.getWaterVolume() <=0 || other.getWaterVolume() - getVolume()  <=0  ){
           // this makes sure that the water height isn't full
            if(waterHeight < height){
                this.waterHeight = (((this.getVolume() + this.getWaterVolume()) - (this.getVolume() - other.getWaterVolume()))/ Math.pow(this.radius, 2));
            other.waterHeight = 0;
            }
        }

        }
    

	// completely fills this Cylinder with water
	public void fillToTop() {
		waterHeight = height;	
	}
}
