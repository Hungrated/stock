import edu.zju.cst.w3.service.IStockService;
import edu.zju.cst.w3.service.StockService;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.zju.cst.w3.common.EntitiesHelper;
import edu.zju.cst.w3.common.Utils;


import java.util.List;

public class StockServiceTest {
    private IStockService stockService;

    @Before
    public void setUp() throws Exception {
        stockService = new StockService();
    }

    @Test
    public void testGetStockClosingPrice() throws Exception {
        double expectedClosingPrice = EntitiesHelper.baseStock.getClosingPrice();
        double actualClosingPrice = stockService.getStockClosingPrice("000000",
                Utils.convertStringToDate("2018-01-01"));
        Assert.assertEquals(expectedClosingPrice, actualClosingPrice);
    }

    @Test
    public void testInsertStockClosingPrice() throws Exception {
        stockService.insertStockClosingPrice("000003",
                Utils.convertStringToDate("2018-01-04"), 75.0);
    }

    @Test
    public void testGetStockName() throws Exception {
        String expectedStockName = EntitiesHelper.baseStock.getName();
        String actualStockName = stockService.getStockName("000000");
        Assert.assertEquals(expectedStockName, actualStockName);
    }

    @Test
    public void testGetStockIdList() throws Exception {
        List<String> stockIdList = stockService.getStockIdList();
        System.out.println("Stock ids:");
        for (String stockId : stockIdList) {
            System.out.print(stockId + " ");
        }
        System.out.println();
    }

    @Test
    public void testGetBestStock() throws Exception {
        String expectedStockId = EntitiesHelper.baseStock.getId();
        String actualStockId = stockService.getBestStock(Utils.convertStringToDate("2018-01-01"),
                Utils.convertStringToDate("2018-01-03"));
        Assert.assertEquals(expectedStockId, actualStockId);
    }

    @After
    public void tearDown() throws Exception {
        // empty
    }

}
