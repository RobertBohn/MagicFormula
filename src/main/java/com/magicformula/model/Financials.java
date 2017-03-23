package com.magicformula.model;

import java.sql.Date;

/**
 * Created by robert on 3/21/17.
 */
public class Financials {

    public String primarysymbol;
    public Date periodenddate;
    public Double ebit;
    public Double totalcurrentassets;
    public Double totalcurrentliabilities;
    public Double totalassets;
    public Double intangibleassets;
    public Double totalshorttermdebt;
    public Double totallongtermdebt;
    public Double cashandcashequivalents;

    public String getPrimarysymbol() {
        return primarysymbol;
    }

    public void setPrimarysymbol(String primarysymbol) {
        this.primarysymbol = primarysymbol;
    }

    public Date getPeriodenddate() {
        return periodenddate;
    }

    public void setPeriodenddate(Date periodenddate) {
        this.periodenddate = periodenddate;
    }

    public Double getEbit() {
        return ebit;
    }

    public void setEbit(Double ebit) {
        this.ebit = ebit;
    }

    public Double getTotalcurrentassets() {
        return totalcurrentassets;
    }

    public void setTotalcurrentassets(Double totalcurrentassets) {
        this.totalcurrentassets = totalcurrentassets;
    }

    public Double getTotalcurrentliabilities() {
        return totalcurrentliabilities;
    }

    public void setTotalcurrentliabilities(Double totalcurrentliabilities) {
        this.totalcurrentliabilities = totalcurrentliabilities;
    }

    public Double getTotalassets() {
        return totalassets;
    }

    public void setTotalassets(Double totalassets) {
        this.totalassets = totalassets;
    }

    public Double getIntangibleassets() {
        return intangibleassets;
    }

    public void setIntangibleassets(Double intangibleassets) {
        this.intangibleassets = intangibleassets;
    }

    public Double getTotalshorttermdebt() {
        return totalshorttermdebt;
    }

    public void setTotalshorttermdebt(Double totalshorttermdebt) {
        this.totalshorttermdebt = totalshorttermdebt;
    }

    public Double getTotallongtermdebt() {
        return totallongtermdebt;
    }

    public void setTotallongtermdebt(Double totallongtermdebt) {
        this.totallongtermdebt = totallongtermdebt;
    }

    public Double getCashandcashequivalents() {
        return cashandcashequivalents;
    }

    public void setCashandcashequivalents(Double cashandcashequivalents) {
        this.cashandcashequivalents = cashandcashequivalents;
    }
}
