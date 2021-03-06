package se.kth.iv1350.seminar3.integration.data;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.seminar3.model.DTO.SaleDTO;

public class SaleLogDBTest {
    
    @Test
    public void testStoreSaleLog() {
        SaleDTO completedSaleDTO = null;
        SaleLogDB instanceToTest = new SaleLogDB();
        
        int sizeOfSaleLogBefore = instanceToTest.getSaleLog().size();
        instanceToTest.storeSaleLog(completedSaleDTO);
        int sizeOfSaleLogAfter = instanceToTest.getSaleLog().size();

        assertTrue((sizeOfSaleLogBefore < sizeOfSaleLogAfter), "SaleLogDB failed to log the completed sale.");
    }
}
