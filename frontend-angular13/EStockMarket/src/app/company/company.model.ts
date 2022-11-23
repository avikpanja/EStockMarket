export class CompanyModel {
    companyCode: string | any;
    companyName: string | any;
    ceo: string | any;
    turnover: number | any;
    website: string | any;
    stockExchange: string | any;
    stockPrice: number | any;

    constructor(
        companyCode?: string, 
        companyName?: string, 
        ceo?: string,
        turnover?: number,
        website?: string,
        stockExchange?: string ,
        stockPrice?: number
    ) { 
        this.companyCode = companyCode;
        this.companyName = companyName;
        this.ceo = ceo;
        this.turnover = turnover;
        this.website = website;
        this.stockExchange = stockExchange;
        this.stockPrice = stockPrice;
    }
}