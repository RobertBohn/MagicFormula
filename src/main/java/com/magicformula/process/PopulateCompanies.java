package com.magicformula.process;

import com.magicformula.dao.CompanyDao;
import com.magicformula.main.MagicFormula;
import com.magicformula.model.Company;
import com.magicformula.model.Response;
import com.magicformula.model.Rows;
import com.magicformula.util.ClassFactory;
import com.magicformula.util.WebReader;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.sql.SQLException;

public class PopulateCompanies {

    public static void populate() throws Exception {
        // PopulateCompanies.populate(0,2830,10);
        // PopulateCompanies.populate(2830,2840, 1);
        // PopulateCompanies.populate(2840,7370,10);
        // PopulateCompanies.populate(7370,7380,1);
        // PopulateCompanies.populate(7380,10000,10);
    }

    private static void populate(int start, int stop, int range) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        CompanyDao companyDao = new CompanyDao();
        String key = MagicFormula.properties.getProperty("edgarkey");

        for (int i = start; i < stop; i += range) {

            String sicCodes = String.format("%04d", i);
            for (int z = 1; z < range; z++) sicCodes += String.format(",%04d", i + z);
            System.out.println("Processing sics: " + sicCodes);

            String uri = String.format("http://edgaronline.api.mashery.com/v2/companies.json?siccodes=%s&limit=999&appkey=%s", sicCodes, key);
            String results = WebReader.read(uri);
            Response response = mapper.readValue(results, Response.class);

            if (Integer.parseInt(response.getResult().getTotalrows()) > 999) {
                throw new Exception("Too many rows: " + response.getResult().getTotalrows());
            }

            for (Rows row : response.getResult().getRows()) {
                Company company = (Company) ClassFactory.create(Company.class, row.getValues());
                if (company.getPrimarysymbol() != null && company.getPrimarysymbol().length() > 0) {
                    companyDao.insert(company);
                    System.out.println(String.format("   %s: %s", company.getSiccode(), company.getCompanyname()));
                }
            }

            Thread.sleep(1500);
        }
    }
}
