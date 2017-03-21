package com.magicformula.process;

import com.magicformula.main.MagicFormula;
import com.magicformula.model.Company;
import com.magicformula.model.Response;
import com.magicformula.model.Rows;
import com.magicformula.util.ClassFactory;
import com.magicformula.util.WebReader;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

public class PopulateCompanies {

    private static Logger logger = Logger.getLogger(WebReader.class);

    public static void populate() {

        try {
            ObjectMapper mapper = new ObjectMapper();
            String key = MagicFormula.properties.getProperty("edgarkey");

            for (int i=0; i<=5; i++) {
                String uri = String.format("http://edgaronline.api.mashery.com/v2/companies.json?siccodes=%04d&appkey=%s", i, key);
                String results = WebReader.read(uri);
                Response response = mapper.readValue(results, Response.class);

                logger.info(String.format("sic: %04d, %s companies.", i, response.getResult().getTotalrows()));

                for (Rows row : response.getResult().getRows()) {
                    Company company = (Company) ClassFactory.create(Company.class, row.getValues());
                    logger.info("  " + company.getCompanyname());
                }

                Thread.sleep(2500);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
