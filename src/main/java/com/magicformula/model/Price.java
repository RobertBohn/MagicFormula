package com.magicformula.model;

import java.sql.Date;

public class Price {

    public String primarysymbol;
    public String companyname;
    public Date lasttradedate;
    public Double closingprice;
    public Double sharesoutstanding;
    public Double averagedailyvolume;
    public Double dividendpershare;

    public String getPrimarysymbol() {
        return primarysymbol;
    }

    public void setPrimarysymbol(String primarysymbol) {
        this.primarysymbol = primarysymbol;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public Date getLasttradedate() {
        return lasttradedate;
    }

    public void setLasttradedate(Date lasttradedate) {
        this.lasttradedate = lasttradedate;
    }

    public Double getClosingprice() {
        return closingprice;
    }

    public void setClosingprice(Double closingprice) {
        this.closingprice = closingprice;
    }

    public Double getSharesoutstanding() {
        return sharesoutstanding;
    }

    public void setSharesoutstanding(Double sharesoutstanding) {
        this.sharesoutstanding = sharesoutstanding;
    }

    public Double getAveragedailyvolume() {
        return averagedailyvolume;
    }

    public void setAveragedailyvolume(Double averagedailyvolume) {
        this.averagedailyvolume = averagedailyvolume;
    }

    public Double getDividendpershare() {
        return dividendpershare;
    }

    public void setDividendpershare(Double dividendpershare) {
        this.dividendpershare = dividendpershare;
    }
}
