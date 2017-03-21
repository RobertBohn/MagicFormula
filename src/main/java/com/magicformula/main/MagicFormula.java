package com.magicformula.main;

import com.magicformula.process.PopulateFinancials;

import java.util.Properties;

public class MagicFormula {

    public static Properties properties = new Properties();

    public static void main( final String[] args ) {
        try {
            properties.load(MagicFormula.class.getClassLoader().getResourceAsStream("config.properties"));


            PopulateFinancials.populate();


            // PopulateCompanies.populate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
