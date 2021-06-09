package se.kth.iv1350.seminar3.integration;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
/**
 * This class represents an observer and writes to file once a new amount has been paid.
 */
public class TotalRevenueFileOutput implements AmountPaidObserver{
    private static final String FILENAME = "amountPaid.txt";
    private float amountPaid;
    private PrintWriter amountPaidByCustomerFile;
    
    /**
     * Is a constructor of TotalRevenueFileOutput. Initiates amountPaid and creates a file.
     */
    public TotalRevenueFileOutput() {
        amountPaid = 0;
        try {
            amountPaidByCustomerFile = new PrintWriter(new FileWriter(FILENAME), true);
        } catch (IOException e) {
            System.out.println("Could not create revenue file");
        }
     }

    /**
     * Method is called once a new payment has gone through.
     * Updates amountPaid and writes to file.
     * @param amountPaid 
     */
    @Override
    public void update(float amountPaid) {
        this.amountPaid += amountPaid;
        printRevenue(amountPaid);
    }

    private void printRevenue(float amountPaid){
        System.out.println("Writing to file '" + FILENAME + "'\n");
        printToFile();
    }
    private void printToFile() {
        amountPaidByCustomerFile.println("Amount paid: " + amountPaid + " SEK");
    }
}
