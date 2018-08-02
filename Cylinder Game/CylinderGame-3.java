
public class CylinderGame {

    private Cylinder[] cylinders;
    private int count; //number of cylinders saved in the array

    public CylinderGame(int maxSize) {
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
    
    // fills the cups and ensures that there is at least one cylinder in the array
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

    /*
	Helper function to run a test case

	cg: the game to run
	source: the cup that will be poured from
	expectedVols: expected volumes of cg's Cylinders after the pour
	expectedWaterVols: expected water volumes of cg's Cylinders after the pour
	expectedSrcVols: expected volume of the source after the pour
	expectedSrcWaterVol: expected water volume of the source after the pour
     */
    private static void testCase(CylinderGame cg, Cylinder source,
            double[] expectedVols, double[] expectedWaterVols,
            double expectedSrcVol, double expectedSrcWaterVol) {
        source.fillToTop();
        cg.fillCupsInOrder(source);
        for (int i = 0; i < cg.count; i++) {
            System.out.println(cg.cylinders[i].toString());
            check(cg.cylinders[i].getVolume(), expectedVols[i]);
            check(cg.cylinders[i].getWaterVolume(), expectedWaterVols[i]);
        }
        System.out.println("source: " + source.toString());
        check(source.getVolume(), expectedSrcVol);
        check(source.getWaterVolume(), expectedSrcWaterVol);
    }

    public static void main(String[] args) {
        CylinderGame cg = new CylinderGame(3);
        cg.addCylinder(2, 2);
        cg.addCylinder(3, 2);
        cg.addCylinder(4, 1);
        cg.addCylinder(1, 2);  // will not get added because no room

        System.out.println("First cup...");

        Cylinder sourceCup = new Cylinder(2, 3);

        double[] expectedVols = {8, 18, 16};
        double[] expectedWaterVols = {8, 4, 0};
        testCase(cg, sourceCup, expectedVols, expectedWaterVols, 12, 0);
        System.out.println("...First test passed\n");

        System.out.println("Second cup...");
        Cylinder sourceCup2 = new Cylinder(6, 6);
        double[] expectedVols2 = {8, 18, 16};
        double[] expectedWaterVols2 = {8, 18, 16};
        testCase(cg, sourceCup2, expectedVols2, expectedWaterVols2, 216, 186);
        System.out.println("...Second test passed\n");

        
        System.out.println("Third cup...");
        cg.deleteCylinder(); // delete 3rd Cylinder
        cg.deleteCylinder(); // delete 2nd Cylinder
        cg.addCylinder(5, 1);
        Cylinder sourceCup3 = new Cylinder(1, 2);
        double[] expectedVols3 = {8, 25};
        double[] expectedWaterVols3 = {8, 2};
        testCase(cg, sourceCup3, expectedVols3, expectedWaterVols3, 2, 0);
        System.out.println("...Third test passed\n");

        

        
	System.out.println("Fourth cup...");

       
        Cylinder sourceCup4 = new Cylinder(5,2);
;
        cg.deleteCylinder();
        cg.deleteCylinder();
        cg.addCylinder(8,1);
        cg.addCylinder(2,2);
        cg.addCylinder(3,3);
        sourceCup4.fillToTop();
        cg.fillCupsInOrder(sourceCup4);
        Cylinder sourceCup5 = new Cylinder(2,2);
        sourceCup5.fillToTop();
        cg.content();
        System.out.println(sourceCup4); 
        cg.fillCupsInOrder(sourceCup5);
        cg.content();
        System.out.println(sourceCup5); 
        Cylinder sourceCup6 = new Cylinder(5,5); 
        sourceCup6.fillToTop();
        System.out.println(sourceCup5);
        cg.fillCupsInOrder(sourceCup6);
        cg.content(); 
        

        
    

         
    }

}
