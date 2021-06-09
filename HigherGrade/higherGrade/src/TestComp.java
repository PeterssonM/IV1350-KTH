public class TestComp {
    private String name;
    private DateOfBirth birthday;

    public TestComp(String theName, DateOfBirth theDate){
        name = theName;
        birthday = theDate;
    }

    public String toString(){
        return String.format("My name is %s, my birthday is %s", name, birthday);
    }
}
