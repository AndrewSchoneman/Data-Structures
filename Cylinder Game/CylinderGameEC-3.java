/*
I created this game so that you get a source cylinder with a randomly generated 
volume (by randomly generating a height and radius) and you also get a random number of 
cylinders to pour the liquid from the source cylinder into. The game will then ask you for
the heights and raidii for each of the game cylinders. You win if you get the source 
cylinder volume to zero and fill each of your game cylinders completely. 
*/

import java.util.*;

public class CylinderGameEC {
    Random rand = new Random(); 
    
    public final double sourceRadius = rand.nextInt(15)+1;
    public final double sourceHeight = rand.nextInt(15)+1; 
    private Cylinder[] cylinders;
    private int count; //number of cylinders saved in the array

    public CylinderGameEC(int maxSize) {
        cylinders = new Cylinder[maxSize];
        count = 0;
    }

    // add a new cylinder of the given radius and height to the array
    // 
    // But, if the array is already full then just print "ERROR addCylinder"
    // and don't add a new cylinder
    public void addCylinder(double r, double h) {
        if (count - 1 != cylinders.length && count + 1 <= cylinders.length) {
            cylinders[count] = new Cylinder(r, h);
            count++;
        } else {
            System.out.println("ERROR addCylinder");
        }
    }

    //*******************************************
    // An extra feature I added so you can check the contents of the game array
    
    public void content() {
        for (Cylinder a : cylinders ) {
            System.out.println(a);
        }
    }

    //*******************************************
    
    // if array contains at least one cylinder delete the last one in the array
    // otherwise do nothing
    public void deleteCylinder() {
        if (cylinders[0] != null && cylinders[count - 1] != null) {
            if (cylinders[count - 1] != null) {
                cylinders[count - 1] = null;
                System.out.println(count);
                count--;
            }
        } else if (cylinders[0] == null) {
            System.out.println("Cannot delete from empty list");
        }

    }

    // pour water from fromCylinder in order until
    // either all of the water is gone or all cylinders have been filled
    //
    // EXAMPLE #1
    // BEFORE
    // cylinders {Cylinder(volume=10,waterVolume=0), 
    //           Cylinder(volume=4,waterVolume=0), 
    //           Cylinder(volume=6,waterVolume=0)} 
    // fromCylinder is Cylinder(volume=13, waterVolume=13)
    // 
    // then AFTER
    // cylinders {Cylinder(volume=10,waterVolume=10), 
    //           Cylinder(volume=4,waterVolume=3), 
    //           Cylinder(volume=6,waterVolume=0)} 
    // from Cylinder is Cylinder(volume=13,waterVolume=0)
    //
    // EXAMPLE #2
    // BEFORE
    // cylinders {Cylinder(volume=10,waterVolume=0), 
    //           Cylinder(volume=4,waterVolume=0), 
    //           Cylinder(volume=6,waterVolume=0)} 
    // fromCylinder is Cylinder(volume=22, waterVolume=22)
    // 
    // then AFTER
    // cylinders {Cylinder(volume=10,waterVolume=10), 
    //           Cylinder(volume=4,waterVolume=4), 
    //           Cylinder(volume=6,waterVolume=6)} 
    // from Cylinder is Cylinder(volume=22,waterVolume=2)
    public void fillCupsInOrder(Cylinder fromCylinder) {
        if (cylinders[0] != null) {
            for (int i = 0; i < count; i++) {
                cylinders[i].pourWaterFrom(fromCylinder);
            }
        }

    }

    /*
	Helper function to check if two doubles are close enough to being equal.
	(In general, it is bad to compare doubles for exact equality with ==)
     */
    private static void check(double got, double expected) {
        final double EPSILON = 0.00001;
        if (Math.abs(got - expected) >= EPSILON) {
            throw new RuntimeException("failed test: got " + got + " but expected " + expected);
             
    
        }
    }
public static double total_volume(CylinderGameEC cg){
    double vol_sum = 0;
    for( int i = 0; i < cg.cylinders.length; i++){
       vol_sum += cg.cylinders[i].getWaterVolume();
      
   }
   return vol_sum; 
}

public static void score(CylinderGameEC cg, Cylinder source){
    for(int i = 0; i<cg.cylinders.length; i++){
        //if(cg.cylinders[i].getWaterVolume() != 0){
        if(source.getWaterVolume() == 0 && cg.cylinders[i].getWaterVolume() != cg.cylinders[i].getVolume()){
               System.out.println("Sorry all cylinders need to be completely filled");
               break; 
    }             
        else if(source.getWaterVolume() != 0){
                System.out.println("Sorry You Lose");
                System.out.println("You were off by " + Math.abs(source.getVolume() - total_volume(cg)));           
                break; 
                }
        else if(source.getWaterVolume() == 0 ){
        System.out.println("You win!");
        }}
    
}


public static void main (String[] args){
    Random rand2 = new Random(); 
    Scanner user_input = new Scanner(System.in); 
    int Num_Cylinders = rand2.nextInt(5)+1;
    CylinderGameEC cg = new CylinderGameEC(Num_Cylinders);
    Cylinder sourceCup = new Cylinder(cg.sourceHeight, cg.sourceRadius);
    sourceCup.fillToTop(); 
    System.out.println("Source Height: "+ cg.sourceHeight + " Source Raidus "+ cg.sourceRadius + 
            " Source Water Volume: " + sourceCup.getWaterVolume());
    System.out.println("Number of Cylinders to Fill: " +cg.cylinders.length );
    

    int count = 1; 
    for(int i = 0; i < Num_Cylinders; i++){
        System.out.println("Enter Radius " + count + ": ");
        double user_radius = user_input.nextDouble();
        System.out.println("Enter Height " + count + ": ");
        double user_height = user_input.nextDouble();
        cg.addCylinder(user_radius, user_height);
        count++;
    }
    cg.fillCupsInOrder(sourceCup);
    cg.content();
    System.out.println(sourceCup);
    score(cg, sourceCup);
    
}
}



    

