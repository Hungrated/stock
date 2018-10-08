import edu.zju.cst.w3.common.StockDataBase;
import edu.zju.cst.w3.common.Utils;
import edu.zju.cst.w3.model.Stock;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.zju.cst.w3.dao.IStockDAO;
import edu.zju.cst.w3.dao.StockDAO;

import java.util.Date;
import java.util.List;

public class StockDAOTest {
    private IStockDAO stockDAO;

    @Before
    public void setUp() throws Exception {
        stockDAO = new StockDAO();
    }

    @Test
    public void testGetStockClosingPrice() throws Exception {
        stockDAO.getStockClosingPrice("000000", Utils.convertStringToDate("2018-01-01"));
    }

    @Test
    public void testInsertStockClosingPrice() throws Exception {

    }

    @Test
    public void testGetStockName() throws Exception {
        stockDAO.getStockName("000001");
    }

    @Test
    public void testGetStockIdList() throws Exception {
        List<String> stockIdList = stockDAO.getStockIdList();
        for (String stockId : stockIdList) {
            System.out.println(stockId);
        }
    }

    @After
    public void tearDown() throws Exception {
        // empty
    }
}
