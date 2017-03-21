package com.magicformula.model;

/**
 * Created by robert on 3/19/17.
 */
public class Company {

    public String cik;
    public String companyname;
    public String entityid;
    public String primaryexchange;
    public String marketoperator;
    public String markettier;
    public String primarysymbol;
    public String siccode;
    public String sicdescription;

    public String getCik() {
        return cik;
    }

    public void setCik(String cik) {
        this.cik = cik;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getEntityid() {
        return entityid;
    }

    public void setEntityid(String entityid) {
        this.entityid = entityid;
    }

    public String getPrimaryexchange() {
        return primaryexchange;
    }

    public void setPrimaryexchange(String primaryexchange) {
        this.primaryexchange = primaryexchange;
    }

    public String getMarketoperator() {
        return marketoperator;
    }

    public void setMarketoperator(String marketoperator) {
        this.marketoperator = marketoperator;
    }

    public String getMarkettier() {
        return markettier;
    }

    public void setMarkettier(String markettier) {
        this.markettier = markettier;
    }

    public String getPrimarysymbol() {
        return primarysymbol;
    }

    public void setPrimarysymbol(String primarysymbol) {
        this.primarysymbol = primarysymbol;
    }

    public String getSiccode() {
        return siccode;
    }

    public void setSiccode(String siccode) {
        this.siccode = siccode;
    }

    public String getSicdescription() {
        return sicdescription;
    }

    public void setSicdescription(String sicdescription) {
        this.sicdescription = sicdescription;
    }
}
