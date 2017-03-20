package com.magicformula.main;

import com.magicformula.model.Company;
import com.magicformula.model.Response;
import com.magicformula.model.Rows;
import com.magicformula.util.ClassFactory;
import com.magicformula.util.WebReader;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

/**
 * Created by robert on 3/19/17.
 */
public class MagicFormula {

    public static void main( final String[] args ) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            String uri = "http://edgaronline.api.mashery.com/v2/companies.json?siccodes=2330&appkey=j7ezdsmr6yhnx7r8u36hrc8k";
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
