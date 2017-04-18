package com.magicformula.process;

import com.magicformula.dao.GuruDao;
import com.magicformula.model.Guru;
import com.magicformula.util.StringUtil;
import com.magicformula.util.WebReader;

public class PopulateGuru {

    private static final String API_FORMAT = "%s/%s/%s:%s/%s/%s";
    private static final String API_URL = "http://www.gurufocus.com/term/";
    private static final String DATA_START = "<div class=\"data_value\">";
    private static final String DATA_END = "</div>";

    private GuruDao guruDao;

    public static PopulateGuru getInstance() {
        return new PopulateGuru();
    }

    public void populate() throws Exception {
        guruDao = new GuruDao();


        Guru guru = new Guru();

        guru.setPrimarysymbol("AAPL");
        guru.setPrimaryexchange("NAS");
        guru.setCompanyname("Apple-Inc");

        guru.setPricetobook(getVariable(guru, Guru.VARIABLES.P2B));
        guru.setReturnoncapital(getVariable(guru, Guru.VARIABLES.ROC));
        guru.setEarningsyield(getVariable(guru, Guru.VARIABLES.EY));
        guru.setEnterprisevalue(getVariable(guru, Guru.VARIABLES.EV));
        guru.setFreecashflow(getVariable(guru, Guru.VARIABLES.FCF));

        guruDao.insert(guru);
    }

    private Double getVariable(Guru guru, Guru.VARIABLES var) throws Exception {
        String uri = String.format(API_FORMAT, API_URL, var.term1(), guru.getPrimaryexchange(), guru.getPrimarysymbol(), var.term2(), guru.getCompanyname());

        String results = WebReader.read(uri);
        String data = StringUtil.extract(results, DATA_START, DATA_END);
        return StringUtil.parseFinancialValue(data);
    }
}
