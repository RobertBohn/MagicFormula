package com.magicformula.model;

public class Rank {

    public String primarysymbol;
    public Double returnoncapital;
    public Double earningsyield;
    public Integer returnoncapitalrank;
    public Integer earningsyieldrank;
    public Integer combinedrank;

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

    public Integer getReturnoncapitalrank() {
        return returnoncapitalrank;
    }

    public void setReturnoncapitalrank(Integer returnoncapitalrank) {
        this.returnoncapitalrank = returnoncapitalrank;
    }

    public Integer getEarningsyieldrank() {
        return earningsyieldrank;
    }

    public void setEarningsyieldrank(Integer earningsyieldrank) {
        this.earningsyieldrank = earningsyieldrank;
    }

    public Integer getCombinedrank() {
        return combinedrank;
    }

    public void setCombinedrank(Integer combinedrank) {
        this.combinedrank = combinedrank;
    }
}
