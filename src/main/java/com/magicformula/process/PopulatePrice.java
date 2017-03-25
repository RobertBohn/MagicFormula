package com.magicformula.process;

import au.com.bytecode.opencsv.CSVParser;
import com.magicformula.dao.CompanyDao;
import com.magicformula.dao.PriceDao;
import com.magicformula.model.Price;
import com.magicformula.util.WebReader;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Set;

public class PopulatePrice {

    private CompanyDao companyDao;
    private PriceDao priceDao;
    private CSVParser csvParser;
    private static final String YOHOO = "http://finance.yahoo.com/d/quotes.csv?s=%s&f=snpa2d1j2d";
    private static DateTimeFormatter inputDateFormat = DateTimeFormat.forPattern("MM/dd/yyyy");
    private static final int PRIMARY_SYMBOL = 0;
    private static final int COMPANY_NAME = 1;
    private static final int CLOSING_PRICE = 2;
    private static final int AVERAGE_DAILY_VOLUME = 3;
    private static final int LAST_TRADE_DATE = 4;
    private static final int SHARES_OUTSTANDING = 5;
    private static final int DIVIDEND_PER_SHARE = 6;

    public PopulatePrice() throws SQLException {
        companyDao = new CompanyDao();
        priceDao = new PriceDao();
        csvParser = new CSVParser();
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
            if (count == 20) {
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
        String uri = String.format(YOHOO, list);
        String results = WebReader.read(uri);
        String[] lines = results.split("\n");

        for (String line : lines) {
           String[] fields = csvParser.parseLine(line);

            if (fields.length != 7) {
                throw new Exception("Incorrect number of fields: " + line);
            }

            System.out.println(fields[PRIMARY_SYMBOL]);

            Price price = new Price();
            price.setPrimarysymbol(fields[PRIMARY_SYMBOL]);
            price.setCompanyname(fields[COMPANY_NAME]);
            price.setClosingprice(getDouble(fields[CLOSING_PRICE]));
            price.setAveragedailyvolume(getDouble(fields[AVERAGE_DAILY_VOLUME]));
            price.setLasttradedate(getDate(fields[LAST_TRADE_DATE]));
            price.setSharesoutstanding(getDouble(fields[SHARES_OUTSTANDING]));
            price.setDividendpershare(getDouble(fields[DIVIDEND_PER_SHARE]));

            priceDao.insert(price);
        }

        Thread.sleep(1000);
    }

    public static Double getDouble(String value) {
        return value.equals("N/A") ? null : Double.parseDouble(value);
    }

    public Date getDate(String value) {
        if (value.equals("N/A")) {
            return null;
        }
        DateTime datetime = new DateTime(inputDateFormat.parseMillis(value));
        return new Date(datetime.toDate().getTime());
    }
}
