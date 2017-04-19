package com.magicformula.process;

import com.magicformula.dao.GuruDao;
import com.magicformula.model.Guru;
import com.magicformula.util.StringUtil;
import com.magicformula.util.WebReader;

import java.util.List;

public class PopulateGuru {

    private static final String API_FORMAT = "%s/%s/%s/%s/%s";
    private static final String API_URL = "http://www.gurufocus.com/term/";
    private static final String DATA_START = "<div class=\"data_value\">";
    private static final String DATA_END = "</div>";

    private GuruDao guruDao;

    public static PopulateGuru getInstance() {
        return new PopulateGuru();
    }

    public void populate() throws Exception {
        guruDao = new GuruDao();

        System.out.println();


        List<Guru> gurus = guruDao.getGurus();
        for (Guru guru : gurus) {
            System.out.println(guru.getPrimarysymbol() + ": " + guru.getCompanyname());

            /*
            guru.setPricetobook(getVariable(guru, Guru.VARIABLES.P2B));
            guru.setReturnoncapital(getVariable(guru, Guru.VARIABLES.ROC));
            guru.setEarningsyield(getVariable(guru, Guru.VARIABLES.EY));
            guru.setEnterprisevalue(getVariable(guru, Guru.VARIABLES.EV));
            guru.setFreecashflow(getVariable(guru, Guru.VARIABLES.FCF));

            guruDao.insert(guru);
            */
        }
    }

    private Double getVariable(Guru guru, Guru.VARIABLES var) throws Exception {
        String uri = String.format(API_FORMAT, API_URL, var.term1(), guru.getPrimarysymbol(), var.term2(), guru.getCompanyname());
        System.out.println(uri);
        String results = WebReader.read(uri);
        String data = StringUtil.extract(results, DATA_START, DATA_END);
        return StringUtil.parseFinancialValue(data);
    }
}
