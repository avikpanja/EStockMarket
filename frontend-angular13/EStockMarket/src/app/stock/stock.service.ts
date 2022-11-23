import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CommonConstants } from '../common-constants';
import { StockModel } from './stock.model';

const COMMON_STOCK_URL: string = CommonConstants.LOCAL_HOST + "api/v1.0/market/stock/";
const UPDATE_STOCK_PRICE_URL = COMMON_STOCK_URL+"add";
const GET_STOCK_HISTORY_URL = COMMON_STOCK_URL+"stockHistory";

@Injectable({
  providedIn: 'root'
})
export class StockService {

  constructor(private http:HttpClient) { }

  public updateStockPrice(stockObj: StockModel): Observable<any> {
    return this.http.post<any>(UPDATE_STOCK_PRICE_URL,stockObj);
  }

  public getStockHistory(companyCode: string): Observable<any> {
    return this.http.get(`${GET_STOCK_HISTORY_URL}/${companyCode}`);
  }
}
