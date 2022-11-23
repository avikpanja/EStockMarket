export class StockStatModel {
    min: number | any;
    max: number | any;
    avg: number | any;

    constructor(min?:number, max?:number, avg?: number) {
        this.min = min;
        this.max = max;
        this.avg = avg;
    }

    clear() {
        this.avg=undefined;
        this.min=undefined;
        this.max=undefined;
    } 
}