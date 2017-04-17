package com.magicformula.util;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class StringUtilTest extends TestCase {

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public StringUtilTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(StringUtilTest.class);
    }

    public void testExtract() {
        String dataStart = "<div class=\"data_value\">";
        String dataEnd = "</div>";
        String goodInput = "AAA\n<div class=\"data_value\">$767,121 Mil (As of Today)</div>\nBBBBB";
        String goodResult = "$767,121 Mil (As of Today)";

        // test good result
        try {
            String result = StringUtil.extract(goodInput, dataStart, dataEnd);
            assertTrue(result.equals(goodResult));
        } catch (Exception e) {
            assertTrue(false);
        }

        // test not found exception
        try {
            String result = StringUtil.extract(goodInput, dataEnd, dataStart);
            assertTrue(false);
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    public void testParseFinancialValue() {
        try {
            assertTrue(StringUtil.parseFinancialValue("<span style=\"font-size: 10px\">USD</span> 767,121 Mil (As of Today)") == 767121000000.0);
            assertTrue(StringUtil.parseFinancialValue("$767,125 Mil (As of Today)") == 767125000000.0);
            assertTrue(StringUtil.parseFinancialValue("5.97 (As of Today)") == 5.97);
            assertTrue(StringUtil.parseFinancialValue("369.28% (As of Dec. 2016)") == 369.28);
            assertTrue(StringUtil.parseFinancialValue("9.85% (As of Dec. 2016)") == 9.85);
        } catch (Exception e) {
            assertTrue(false);
        }
    }
}
