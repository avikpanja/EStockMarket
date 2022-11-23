import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CommonConstants } from '../common-constants';
import { CompanyModel } from './company.model';

const COMMON_COMPANY_URL: string = CommonConstants.LOCAL_HOST + "api/v1.0/market/company/";

const GET_ALL_COMPANY_DETAILS_URL: string = COMMON_COMPANY_URL+"getall";
const SEARCH_A_COMPANY_URL = COMMON_COMPANY_URL+"info";
const REGISTER_A_COMPANY_URL = COMMON_COMPANY_URL+"register";
const DELETE_A_COMPANY_URL = COMMON_COMPANY_URL+"delete";
const UPDATE_A_COMPANY_INFO_URL = COMMON_COMPANY_URL+"put";

@Injectable({
  providedIn: 'root'
})
export class CompanyService {

  constructor(private http:HttpClient) { }

  public addCompany(compObj: CompanyModel) : Observable<any> {
    return this.http.post<any>(REGISTER_A_COMPANY_URL,compObj);
  }

  public getAllCompanyDetails(): Observable<any> {
    return this.http.get<any>(GET_ALL_COMPANY_DETAILS_URL);
  }

  public deleteCompany (companyCode: string) : Observable<any>{
    return this.http.delete<any>(`${DELETE_A_COMPANY_URL}/${companyCode}`);
  }

  public updateCompany(compObj: CompanyModel): Observable<any> {
    return this.http.put<any>(UPDATE_A_COMPANY_INFO_URL, compObj)
  }

  public getDetailsOfACompany(companyCode: string) : Observable<any> {
    return this.http.get(`${SEARCH_A_COMPANY_URL}/${companyCode}`);
  }
}
