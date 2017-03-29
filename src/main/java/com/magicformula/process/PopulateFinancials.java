package com.magicformula.process;

import com.magicformula.dao.CompanyDao;
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
import java.util.Set;

public class PopulateFinancials {

    private ObjectMapper mapper;
    private CompanyDao companyDao;
    private FinancialsDao financialsDao;
    private String key;

    public static PopulateFinancials getInstance() throws Exception {
        return new PopulateFinancials();
    }

    public PopulateFinancials() throws Exception {
        mapper = new ObjectMapper();
        companyDao = new CompanyDao();
        financialsDao = new FinancialsDao();
        key = MagicFormula.properties.getProperty("edgarkey");
    }

    public void populate() throws Exception {
        Set<String> tickers = companyDao.getTickers();
        int count = 0;
        String list = "";
        for (String ticker : tickers) {
            if (count++ == 0) {
                list = ticker;
            } else {
                list += "," + ticker;
            }
            if (count == 10) {
                populateTickers(list);
                list = "";
                count = 0;
            }
        }
        if (list.length() > 0) {
            populateTickers(list);
        }
    }

    public void populateTickers(String list) throws Exception {
        String uri = String.format("http://edgaronline.api.mashery.com/v2/corefinancials/ttm.json?primarysymbols=%s&numperiods=1&appkey=%s", list, key);
        String results = WebReader.read(uri);
        Response response = mapper.readValue(results, Response.class);

        for (Rows row : response.getResult().getRows()) {
            Financials financials = (Financials) ClassFactory.create(Financials.class, row.getValues());
            System.out.println("  " + financials.getPrimarysymbol());
            financialsDao.insert(financials);
        }
    }
}
