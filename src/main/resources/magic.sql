
create table company (
cik               varchar(50) default null,
companyname       varchar(100) default null,
entityid          varchar(50) default null,
primaryexchange   varchar(50) default null,
marketoperator    varchar(50) default null,
markettier        varchar(50) default null,
primarysymbol     varchar(10) not null,
siccode           varchar(4) default null,
sicdescription    varchar(200) default null,
unique key primarysymbol (primarysymbol)
) ENGINE=InnoDB DEFAULT CHARSET=latin1


create table financials (
primarysymbol            varchar(10) not null,
periodenddate            date default null,
ebit                     BIGINT default null,
totalcurrentassets       BIGINT default null,
totalcurrentliabilities  BIGINT default null,
totalassets              BIGINT default null,
intangibleassets         BIGINT default null,
totalshorttermdebt       BIGINT default null,
totallongtermdebt        BIGINT default null,
cashandcashequivalents   BIGINT default null,
cashfromoperatingactivities  BIGINT default null,
capitalexpenditures      BIGINT default null,
 unique key primarysymbol (primarysymbol)
) ENGINE=InnoDB DEFAULT CHARSET=latin1


create table price (
primarysymbol         varchar(10) not null,
companyname           varchar(100) default null,
lasttradedate         date default null,
sharesoutstanding     BIGINT default null,
averagedailyvolume    BIGINT default null,
closingprice          double(10,2) default null,
dividendpershare      double(10,4) default null,
unique key primarysymbol (primarysymbol)
) ENGINE=InnoDB DEFAULT CHARSET=latin1


create table rank (
  primarysymbol            varchar(10) not null,
  returnoncapitalrank      integer not null,
  earningsyieldrank        integer not null,
  combinedrank             integer not null,
  unique key primarysymbol (primarysymbol)
) ENGINE=InnoDB DEFAULT CHARSET=latin1


/********************************************************************************/

create table performance as

  select
    p.primarysymbol,
    ebit / ((totalcurrentassets - totalcurrentliabilities) + (totalassets - totalcurrentassets - ifnull(intangibleassets,0))) returnoncapital,
    ebit / ((sharesoutstanding * closingprice) + (ifnull(totalshorttermdebt,0) + ifnull(totallongtermdebt,0) - cashandcashequivalents)) earningsyield

  from financials f, price p

  where
    f.primarysymbol = p.primarysymbol
    and ebit
    and totalcurrentassets
    and totalcurrentliabilities
    and totalassets
    and sharesoutstanding
    and closingprice
    and cashandcashequivalents

/*
 *     1661 - all not null
 *     3628 - ifnull on intangibleassets, totalshorttermdebt
 *     6044 - isnull on totallongtermdebt
 */

/********************************************************************************/


select
  price.companyname,
  rank.primarysymbol,
  closingprice * sharesoutstanding/1000000 "MktCap M",
  lasttradedate,
  periodenddate,
  dividendpershare,
  averagedailyvolume,
  combinedrank,
  earningsyieldrank,
  returnoncapitalrank

from rank, price, financials

where
  rank.primarysymbol = price.primarysymbol
  and rank.primarysymbol = financials.primarysymbol

order by combinedrank

limit 100
