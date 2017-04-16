

http://www.gurufocus.com/term/ev/AAPL/Enterprise-Value/Apple-Inc


http://www.jarloo.com/yahoo_finance/
http://finance.yahoo.com/d/quotes.csv?s=AAPL,GOOG,MSFT&f=snpa2d1j2d

"AAPL","Apple Inc.",140.92,27163800,"3/24/2017",5246540000,2.28
"GOOG","Alphabet Inc.",817.58,1465660,"3/24/2017",691420000,N/A
"MSFT","Microsoft Corporation",64.87,22897800,"3/24/2017",7727529000,1.56

s  = symbol                  AAPL
n  = name                    Apple Inc.
p  = close                   64.87
a2 = Average Daily Volume    22897800
d1 = tradedate               3/24/2017
j2 = Shares Outstanding      5246540000
d  = Dividend per Share      2.28   N/A

===============================================

Return on Capital

   EBIT / Tangible Capital Employed

   Tangible Capital Employed = Net Working Capital + Net Fixed Assets
   Net Working Capital =  totalcurrentassets - totalcurrentliabilities
   Net Fixed Assets =  totalassets - totalcurrentassets - intangibleassets

   ebit / ((totalcurrentassets - totalcurrentliabilities) + (totalassets - totalcurrentassets - intangibleassets))



Earnings Yield

   EBIT / Enterprise Value

   Enterprise Value = Market Value of Equity + Net Interest Bearing Debt
   Market Value of Equity = Shares Outstanding * Price
   Net Debt = (totalshorttermdebt + totallongtermdebt) - cashandcashequivalents

   ebit / ((Shares Outstanding * Price) + (totalshorttermdebt + totallongtermdebt - cashandcashequivalents))

=============================================

http://developer.edgar-online.com/docs/core_financials


NOTE: add .json to change format

Meta Data
http://edgaronline.api.mashery.com/v2/companies?primarysymbols=CHKE&appkey={key}

Core Financials TTM
http://edgaronline.api.mashery.com/v2/corefinancials/ttm?primarysymbols=CHKE&appkey={key}

List of companies by siccodes  (3 or 4 digits)
http://edgaronline.api.mashery.com/v2/companies?siccodes=2330&appkey={key}

http://sic-codes.findthedata.com/


=============================================


primarysymbol             - Edgar Meta
siccode                   - Edgar Meta

periodenddate             - Edgar Core Financial
ebit                      - Edgar Core Financial
totalcurrentassets        - Edgar Core Financial
totalcurrentliabilities   - Edgar Core Financial
totalassets               - Edgar Core Financial
intangibleassets          - Edgar Core Financial
totalshorttermdebt        - Edgar Core Financial
totallongtermdebt         - Edgar Core Financial
cashandcashequivalents    - Edgar Core Financial

dividendspaid             - Yahoo
closingprice              - Yahoo
sharesoutstanding         - Yahoo
averagedailyvolume        - Yahoo



