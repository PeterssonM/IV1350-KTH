
public class Main {

    public static void main(String[] args) {


        System.out.println("Test Composition");
        System.out.println();

        DateOfBirth potObject = new DateOfBirth(2,8,1999);
        TestComp testCompObject = new TestComp("Daniel", potObject);

        System.out.println(testCompObject);


        DateOfBirth potObject3 = new DateOfBirth(5,10,2001);
        TestComp testCompObject3 = new TestComp("Maximilian", potObject3);

        System.out.println(testCompObject3);

        System.out.println();
        System.out.println("Test Inheritance");
        System.out.println();

        exampleClass testObject = new exampleClass();
        AnotherExampleClass anotherTestObject = new AnotherExampleClass();

        testObject.eat();
        System.out.println(" ,from the test object");

        anotherTestObject.eat();
        System.out.print(" ,from another test object");

    }
}


