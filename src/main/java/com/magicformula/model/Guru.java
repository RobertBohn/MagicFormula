package com.magicformula.model;

public class Guru {

    private String primarysymbol;
    private Double returnoncapital;
    private Double earningsyield;
    private Double pricetobook;
    private Double freecashflow;
    private Double enterprisevalue;


    public static enum VARIABLES {
        ROC("ROC_JOEL", "ROC-Joel-Greenblatt-"),
        EY("earning_yield_greenblatt", "Earnings-Yield-Joel-Greenblatt-"),
        P2B("p2tangible_book", "Price-to-Tangible-Book"),
        FCF("total_freecashflow", "Free-Cash-Flow"),
        EV("ev", "Enterprise-Value");

        private String term1;
        private String term2;

        VARIABLES(String term1, String term2) {
            this.term1 = term1;
            this.term2 = term2;
        }

        public String term1() {
            return term1;
        }

        public String term2() {
            return term2;
        }
    };



    public String getPrimarysymbol() {
        return primarysymbol;
    }

    public void setPrimarysymbol(String primarysymbol) {
        this.primarysymbol = primarysymbol;
    }

    public Double getReturnoncapital() {
        return returnoncapital;
    }

    public void setReturnoncapital(Double returnoncapital) {
        this.returnoncapital = returnoncapital;
    }

    public Double getEarningsyield() {
        return earningsyield;
    }

    public void setEarningsyield(Double earningsyield) {
        this.earningsyield = earningsyield;
    }

    public Double getPricetobook() {
        return pricetobook;
    }

    public void setPricetobook(Double pricetobook) {
        this.pricetobook = pricetobook;
    }

    public Double getFreecashflow() {
        return freecashflow;
    }

    public void setFreecashflow(Double freecashflow) {
        this.freecashflow = freecashflow;
    }

    public Double getEnterprisevalue() {
        return enterprisevalue;
    }

    public void setEnterprisevalue(Double enterprisevalue) {
        this.enterprisevalue = enterprisevalue;
    }
}
