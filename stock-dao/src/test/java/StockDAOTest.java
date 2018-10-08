import edu.zju.cst.w3.common.EntitiesHelper;
import edu.zju.cst.w3.common.Utils;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.zju.cst.w3.dao.IStockDAO;
import edu.zju.cst.w3.dao.StockDAO;

import java.util.List;

public class StockDAOTest {
    private IStockDAO stockDAO;

    @Before
    public void setUp() throws Exception {
        stockDAO = new StockDAO();
    }

    @Test
    public void testGetStockClosingPrice() throws Exception {
        double expectedClosingPrice = EntitiesHelper.baseStock.getClosingPrice();
        double actualClosingPrice = stockDAO.getStockClosingPrice("000000",
                Utils.convertStringToDate("2018-01-01"));
        Assert.assertEquals(expectedClosingPrice, actualClosingPrice);
    }

    @Test
    public void testInsertStockClosingPrice() throws Exception {
        stockDAO.insertStockClosingPrice("000001",
                Utils.convertStringToDate("2018-01-03"), 50.0);
    }

    @Test
    public void testGetStockName() throws Exception {
        String expectedStockName = EntitiesHelper.baseStock.getName();
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
