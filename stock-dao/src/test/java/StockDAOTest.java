import edu.zju.cst.w3.common.Utils;
import edu.zju.cst.w3.model.Stock;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.zju.cst.w3.dao.IStockDAO;
import edu.zju.cst.w3.dao.StockDAO;

import java.util.List;

public class StockDAOTest {
    private IStockDAO stockDAO;
    private static Stock baseStock;
    private static Stock baseStock2;

    @Before
    public void setUp() throws Exception {
        stockDAO = new StockDAO();
        baseStock = new Stock("000000", "aaa",
                1.0, Utils.convertStringToDate("2018-01-01"));
        baseStock2 = new Stock("000005", "aaf",
                0.0, Utils.convertStringToDate("2018-01-01"));
    }

    @Test
    public void testGetStockClosingPrice() throws Exception {
        double expectedClosingPrice = baseStock.getClosingPrice();
        double actualClosingPrice = stockDAO.getStockClosingPrice("000000",
                Utils.convertStringToDate("2018-01-01"));
        Assert.assertEquals(expectedClosingPrice, actualClosingPrice);

        double expectedClosingPrice2 = baseStock2.getClosingPrice();
        double actualClosingPrice2 = stockDAO.getStockClosingPrice("000005",
                Utils.convertStringToDate("2018-01-01"));
        Assert.assertEquals(expectedClosingPrice2, actualClosingPrice2);
    }

    @Test
    public void testInsertStockClosingPrice() throws Exception {
        stockDAO.insertStockClosingPrice("000001",
                Utils.convertStringToDate("2018-01-03"), 50.0);
    }

    @Test
    public void testGetStockName() throws Exception {
        String expectedStockName = baseStock.getName();
        String actualStockName = stockDAO.getStockName("000000");
        Assert.assertEquals(expectedStockName, actualStockName);
    }

    @Test
    public void testGetStockIdList() throws Exception {
        List<String> stockIdList = stockDAO.getStockIdList();
        System.out.println("Stock ids:");
        for (String stockId : stockIdList) {
            System.out.print(stockId + " ");
        }
        System.out.println();
    }

    @After
    public void tearDown() throws Exception {
        // empty
    }
}
