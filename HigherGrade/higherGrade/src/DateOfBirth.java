public class DateOfBirth {
    private int month;
    private int day;
    private int year;

    public DateOfBirth(int d, int m, int y){
        day = d;
        month = m;
        year = y;

        System.out.printf("The constructor for this new is object is %s\n", this);

    }

    public String toString(){
        return String.format("%d/%d/%d", month, day, year);
    }
}
