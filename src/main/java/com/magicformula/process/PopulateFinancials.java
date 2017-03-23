package com.magicformula.process;

import com.magicformula.dao.FinancialsDao;
import com.magicformula.main.MagicFormula;
import com.magicformula.model.Financials;
import com.magicformula.model.Response;
import com.magicformula.model.Rows;
import com.magicformula.util.ClassFactory;
import com.magicformula.util.WebReader;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class PopulateFinancials {

    public static void populate() throws IOException, InvocationTargetException, NoSuchMethodException, NoSuchFieldException, InstantiationException, IllegalAccessException, SQLException {
        ObjectMapper mapper = new ObjectMapper();
        FinancialsDao financialsDao = new FinancialsDao();
        String key = MagicFormula.properties.getProperty("edgarkey");



        String ticker = "AAPL";


        String uri = String.format("http://edgaronline.api.mashery.com/v2/corefinancials/ttm.json?primarysymbols=%s&appkey=%s", ticker, key);
        String results = WebReader.read(uri);
        Response response = mapper.readValue(results, Response.class);

        for (Rows row : response.getResult().getRows()) {
            if (row.getRownum().equals("1")) {
                Financials financials = (Financials) ClassFactory.create(Financials.class, row.getValues());
                financialsDao.insert(financials);
                System.out.println("  " + financials.getPrimarysymbol());
                break;
            }
        }
    }
}
