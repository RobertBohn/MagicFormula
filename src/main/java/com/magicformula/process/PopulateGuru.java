package com.magicformula.process;

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
        String term1 = "total_freecashflow";
        String term2 = "Free-Cash-Flow";
        String exchange = "NAS";
        String ticker = "AAPL";
        String company = "Apple-Inc";
        String dataStart = "<div class=\"data_value\">";
        String dataEnd = "</div>";

        String uri = String.format(apiFormat, url, term1, exchange, ticker, term2, company);
        String results = WebReader.read(uri);
        String data = StringUtil.extract(results, dataStart, dataEnd);
        Double value = StringUtil.parseFinancialValue(data);
    }
}
