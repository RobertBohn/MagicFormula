package com.magicformula.main;

import com.magicformula.model.Company;
import com.magicformula.model.Response;
import com.magicformula.model.Rows;
import com.magicformula.util.ClassFactory;
import com.magicformula.util.WebReader;
import org.codehaus.jackson.map.ObjectMapper;

import java.util.Properties;

/**
 * Created by robert on 3/19/17.
 */
public class MagicFormula {

    public static Properties properties = new Properties();

    public static void main( final String[] args ) {

        ObjectMapper mapper = new ObjectMapper();

        try {
            properties.load(MagicFormula.class.getClassLoader().getResourceAsStream("Config.properties"));

            String key = properties.getProperty("edgarkey");
            String uri = "http://edgaronline.api.mashery.com/v2/companies.json?siccodes=2330&appkey=" + key;
            String results = WebReader.read(uri);
            Response response = mapper.readValue(results, Response.class);
            for (Rows row : response.getResult().getRows()) {
                Company company = (Company) ClassFactory.create(Company.class, row.getValues());
                System.out.println(company.getCompanyname());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
