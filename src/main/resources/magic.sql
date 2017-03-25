
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
