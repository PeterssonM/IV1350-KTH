package se.kth.iv1350.seminar3.model;

import java.util.ArrayList;
import java.util.List;
//import java.util.Observer;
import se.kth.iv1350.seminar3.controller.ConnectionTimedOut;
import se.kth.iv1350.seminar3.integration.Subject;
import se.kth.iv1350.seminar3.integration.data.AccountingSystemDB;
import se.kth.iv1350.seminar3.view.TotalRevenueView;
import se.kth.iv1350.seminar3.integration.AmountPaidObserver;
import se.kth.iv1350.seminar3.integration.TotalRevenueFileOutput;

/**
 * Payment represents a transaction and is used to handle a payment. 
 */
public class Payment implements Subject{
    private float amountPaid;
    private AccountingSystemDB accSys;
    private float totalCost;
    private float change;
    private List<AmountPaidObserver> listOfAmountPaidObserver;

    /**
     * Creates an instance of Payment by storing amount paid 
     * and updating the store's accounting system.
     * @param amountPaid - Amount paid by the customer.
     * @param accSys - A referens to the store's accounting systsem.
     */
    public Payment(float amountPaid, AccountingSystemDB accSys){
        this.amountPaid = amountPaid;
        this.accSys = accSys;
        listOfAmountPaidObserver = new ArrayList<AmountPaidObserver>();
        registerObserver(new TotalRevenueView());
        registerObserver(new TotalRevenueFileOutput());
    }
        
    /**
     * Method Checks that the payed amount is correct and return change if needed (otherwice return 0).
     * @param totalCost - Of the sale.
     * @param discountAmount - Eligable discount amount.
     * @return - Change.
     * @throws ConnectionTimedOut if AccountingSystemDB is not responding.
     * @throws InsufficientFundsException is thrown if the customer has not paid the full price of the sale.
     */
    public float getChange(float totalCost, double discountAmount) throws ConnectionTimedOut, InsufficientFundsException {
        float newTotalCost = totalCost;
        if (discountAmount > 0){
            newTotalCost = totalCost * (float)(1-discountAmount);
        }
        
        if(this.amountPaid >= newTotalCost){
            this.change = this.amountPaid - newTotalCost;
            accSys.updateAccountingDB(totalCost);
            notifyObservers();
            return this.change; 
        }
        else{
            throw new InsufficientFundsException(this.amountPaid, totalCost);
        }
    }
    /**
     * Reads the amount paid.
     * @return the paid amount by the customer.
     */
    public float getAmountPaid(){
        return this.amountPaid;
    }
    /**
     * Reads the list of all observers.
     * @return the read list.
     */
    public List<AmountPaidObserver> getListOfAmountPaidObserver() {
        return listOfAmountPaidObserver;
    }
    /**
     * Sets the list with a list consisitng of observers.
     * @param listOfAmountPaidObserver the list that will be stored.
     */
    public void setListOfAmountPaidObserver(List<AmountPaidObserver> listOfAmountPaidObserver) {
        this.listOfAmountPaidObserver = listOfAmountPaidObserver;
    }
    /**
     * Takes one observer and adds it to the observer list.
     * @param amountPaidObserver the observer object to be added.
     */
    @Override
    public void registerObserver(AmountPaidObserver amountPaidObserver) {
        listOfAmountPaidObserver.add(amountPaidObserver);
    }
    /**
     * Removes a specific observer object from the observer list.
     * @param amountPaidObserver the object to remove.
     */
    @Override
    public void removeObserver(AmountPaidObserver amountPaidObserver) {
        listOfAmountPaidObserver.remove(amountPaidObserver);
    }
    /**
     * Sends a notification to all subscribed observers that a new payment has gone through.
     */
    @Override
    public void notifyObservers() {
        System.out.println("\n[Notifying that a new customer has paid]");
        for(AmountPaidObserver amountPaidObserver : listOfAmountPaidObserver){
            amountPaidObserver.update(amountPaid);
        }
    }
    
    /**
     * Exception is used to inform the cashier and customer that the purchase has not gone
     * through because the customer has not paid the full price of the sale.
     */
    public class InsufficientFundsException extends Exception{
        private float amountPaid, totalCost;
        public InsufficientFundsException(float amountPaid, float totalCost){
            this.amountPaid = amountPaid;
            this.totalCost = totalCost;
        }
        /**
         * Prints to screen whenever an object of this class is created.
         * @return Prints to screen.
         */
        @Override
        public String toString(){ 
            return "Insufficient funds. Total cost is: " + totalCost + 
                   " Paid amount is: " + amountPaid;
        }
    }
    
}
