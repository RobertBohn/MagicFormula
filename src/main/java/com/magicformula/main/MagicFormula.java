package com.magicformula.main;

import com.magicformula.process.PopulateCompanies;
import com.magicformula.process.PopulateFinancials;
import com.magicformula.process.PopulatePrice;

import java.util.Properties;

public class MagicFormula {

    public static Properties properties = new Properties();

    public static void main( final String[] args ) {
        try {
            properties.load(MagicFormula.class.getClassLoader().getResourceAsStream("config.properties"));
            // PopulateCompanies.populate();

            // PopulateFinancials populateFinancials = new PopulateFinancials();
            // populateFinancials.populate();


            PopulatePrice populatePrice = new PopulatePrice();
            populatePrice.populate();

            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
