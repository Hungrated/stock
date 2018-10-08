import edu.zju.cst.w3.common.StockDataBase;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.zju.cst.w3.dao.IStockDAO;
import edu.zju.cst.w3.dao.StockDAO;

public class StockDAOTest {
    private IStockDAO stockDAO;

    @Before
    public void setUp() throws Exception {
        stockDAO = new StockDAO();
    }

    @Test
    public void testGetStockClosingPrice() throws Exception {

    }

    @Test
    public void testInsertStockClosingPrice() throws Exception {


    }

    @Test
    public void testGetStockName() throws Exception {


    }

    @Test
    public void testGetStockIdList() throws Exception {
        StockDataBase db = new StockDataBase();
        db.print();

    }

    @After
    public void tearDown() throws Exception {

    }
}
