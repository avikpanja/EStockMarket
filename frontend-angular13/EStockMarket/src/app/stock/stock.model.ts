export class StockModel {
    companyCode: string | any;
    stockPrice: number | any;
    createdDateTime: Date = new Date();

    constructor(code?: string, stockPrice?: number) {
        this.companyCode = code;
        this.stockPrice = stockPrice;
    }
    
}