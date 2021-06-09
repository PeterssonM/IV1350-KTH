package se.kth.iv1350.seminar3.view;

import se.kth.iv1350.seminar3.integration.Subject;
import se.kth.iv1350.seminar3.integration.AmountPaidObserver;

/**
 * This class represents an observer and print to screen once a new amount has been paid.
 */
public class TotalRevenueView implements AmountPaidObserver{

    private float amountPaid;
    private  Subject subject;

    /**
     * Constructor to TotalRevenueView.
     * Initializes amountPaid.
     */
    public TotalRevenueView(){
        amountPaid = 0;
    }
    
    /**
     * Method is called once a new payment has gone through.
     * Updates amountPaid and prints to screen.
     * @param amountPaid 
     */
    @Override
    public void update(float amountPaid) {
        this.amountPaid += amountPaid;
        printRevenue(amountPaid);
    }
    private void printRevenue(float amountPaid){
        System.out.println("The most recent customer paid: " + amountPaid + " SEK" +
                           "\nTotal revenue: " + this.amountPaid + " SEK");
    }
}
