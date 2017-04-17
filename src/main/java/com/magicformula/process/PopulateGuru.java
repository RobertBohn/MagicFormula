package com.magicformula.process;

import com.magicformula.model.Guru;
import com.magicformula.util.StringUtil;
import com.magicformula.util.WebReader;

public class PopulateGuru {

    public static PopulateGuru getInstance() {
        return new PopulateGuru();
    }

    public void populate() throws Exception {

        // TODO should be able to create these strings given "company" data & variable desired
        String apiFormat = "%s/%s/%s:%s/%s/%s";  // http://www.gurufocus.com/term/ev/NAS:AAPL/Enterprise-Value/Apple-Inc
        String url = "http://www.gurufocus.com/term/";
        String exchange = "NAS";
        String ticker = "AAPL";
        String company = "Apple-Inc";
        String dataStart = "<div class=\"data_value\">";
        String dataEnd = "</div>";


        String term1 = Guru.VARIABLES.EY.term1();
        String term2 = Guru.VARIABLES.EY.term2();


        String uri = String.format(apiFormat, url, term1, exchange, ticker, term2, company);
        String results = WebReader.read(uri);
        String data = StringUtil.extract(results, dataStart, dataEnd);
        Double value = StringUtil.parseFinancialValue(data);

        System.out.println("Earnings Yield " + value);
    }
}
