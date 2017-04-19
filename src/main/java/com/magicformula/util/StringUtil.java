package com.magicformula.util;

public class StringUtil {

    public static final String[] FINANCIAL_REGEX = {
        "Mil",         // millions multiplier
        "USD",         // US dollars
        "<.+>",        // tags
        "\\(.+\\)",    // parentheses
        "\\%|\\$|,| "  // expected characters
    };

    public static String extract(final String theString, final String startingPhrase, final String endingPhrase) throws Exception {

        int start = theString.indexOf(startingPhrase);
        if (start < 0) {
            throw new Exception("Starting phrase not found");
        }

        int end = theString.substring(start).indexOf(endingPhrase);
        if (end < 0) {
            throw new Exception("Ending phrase not found");
        }

        return theString.substring(start + startingPhrase.length(), start + end);
    }

    public static double parseFinancialValue(String value) throws Exception {

        Double multiplier = 1.0;

        if (value.contains(" Mil ")) {
            multiplier = 1000000.0;
        }

        for (String regEx : FINANCIAL_REGEX) {
            value = value.replaceAll(regEx, "");
        }

        return Double.parseDouble(value)  * multiplier;
    }

    public static String formatCompanyName(String company) {
        company = company.replaceAll(   "\\,", "" );  // handle dots and commas

        company = company.replaceAll(" ", "-");  // replace blanks with dashes

        return company;
    }
}
