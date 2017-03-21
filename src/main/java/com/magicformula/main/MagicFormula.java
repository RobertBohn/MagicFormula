package com.magicformula.main;

import com.magicformula.process.PopulateCompanies;

import java.util.Properties;

/**
 * Created by robert on 3/19/17.
 */
public class MagicFormula {

    public static Properties properties = new Properties();

    public static void main( final String[] args ) {
        try {
            properties.load(MagicFormula.class.getClassLoader().getResourceAsStream("config.properties"));
            PopulateCompanies.populate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
