package se.kth.iv1350.seminar3.integration;

/**
 * Interface is implemented in Payment.
 */
public interface Subject {
    public void registerObserver(AmountPaidObserver amountPaidObserver);
    public void removeObserver(AmountPaidObserver amountPaidObserver);
    public void notifyObservers();
}
