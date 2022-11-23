import { Component, OnInit, ViewChild } from '@angular/core';
import { CompanyModel } from './company.model';
import { CompanyService } from './company.service';
import { StockService } from '../stock/stock.service';
import { StockModel } from '../stock/stock.model';
import { MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';
declare var $: any;

@Component({
  selector: 'app-company',
  templateUrl: './company.component.html',
  styleUrls: ['./company.component.css']
})
export class CompanyComponent implements OnInit {

   stockExchangeList = ['BSE', 'NSE'];
  // newCompany = new CompanyModel();

  stockModalData = new StockModel;
  companyModalData = new CompanyModel;
  listOfCompany : CompanyModel[] = [];
  //searchedCompanyCode: string = "";
  searchedKey: string = "";
  selectedCompanyCode: string="";
  deleteConfirmation: string="";


  companyTableHeaders = [
    'companyName', 
    'companyCode', 
    'ceo', 
    'turnover', 
    'website', 
    'stockExchange', 
    'stockPrice',
    'actions'
  ];

  @ViewChild(MatSort) sort = new MatSort();
  @ViewChild(MatPaginator) paginator: MatPaginator | any;

  matDataSource = new MatTableDataSource();
  constructor(
    private companyService: CompanyService, 
    //private stockService: StockService
  ) { }

  ngOnInit(): void {  
    this.viewAllCompany();
  }

  // registerNewCompany = () => {
  //   this.companyService.addCompany(this.newCompany).subscribe(data=> {
  //     alert(data.body.message);
  //     if(data!=null)
  //       this.listOfCompany.push(data.body.result);
  //   },
  //   error => {
  //     console.log("Registration unsuccessful "+ error);
  //   });
  // };

  searchAComapny = ()=> {
    this.companyService.getDetailsOfACompany(this.searchedKey).subscribe(
      data => {
        if(data!=null) {
          this.listOfCompany = [data.body.result];
          this.matDataSource.data = this.listOfCompany;
        }
        else {
          alert("No company found");
        }
      },
      error => {

      }
    );
  }

  viewAllCompany = () => {
    this.companyService.getAllCompanyDetails().subscribe(data=>{
      if(data!=null)
        this.listOfCompany = data.body.result;
        this.matDataSource.data = this.listOfCompany;
        this.matDataSource.sort = this.sort;
        this.matDataSource.paginator = this.paginator;
    }, error => {
      console.log("error occured " + JSON.stringify(error) );
    });
  }

  openCompanyDeleteModal(companyCode: string) {
    this.selectedCompanyCode = companyCode;
    this.deleteConfirmation = "";
    $('#companyDeleteModal').modal('show');
  }

  closeCompanyDeleteModal = () => {
    $('#companyDeleteModal').modal('hide');
  }

  deleteCompany = () => {
    if(this.deleteConfirmation.trim().toLocaleLowerCase()=="confirm delete") {
      this.companyService.deleteCompany(this.selectedCompanyCode).subscribe(data=>{
        let index = this.listOfCompany.findIndex(item => item.companyCode==this.selectedCompanyCode);
        this.listOfCompany.splice(index,1);
        this.matDataSource.data = this.listOfCompany;
        this.closeCompanyDeleteModal();
        alert(data.message);
      }, error => {
        console.log("error occured " + error);
      });
    }
  }

  // openStockUpdateModal = (code: string) => {
  //   let selectedComp = this.listOfCompany.find(item => item.companyCode==code);
  //   this.stockModalData = new StockModel(selectedComp?.companyCode, selectedComp?.stockPrice);
  //   $('#stockPriceUpdateModal').modal('show');
  // }

  openCompanyUpdateModal = (code: string) => {
    let selectedComp = this.listOfCompany.find(item => item.companyCode==code);
    this.companyModalData = new CompanyModel (
      selectedComp?.companyCode,
      selectedComp?.companyName,
      selectedComp?.ceo,
      selectedComp?.turnover,
      selectedComp?.website,
      selectedComp?.stockExchange,
      selectedComp?.stockPrice
    );

    $('#companyInfoUpdateModal').modal('show');
  }

  // closeStockUpdateModal = () => {
  //   $('#stockPriceUpdateModal').modal('hide');
  // }

  closeCompanyUpdateModal = () => {
    $('#companyInfoUpdateModal').modal('hide');
  }

  // updateStockPrice = () => {

  //   this.stockService.updateStockPrice(this.stockModalData).subscribe(data=>{
      
  //     let index = this.listOfCompany.findIndex(item => item.companyCode==this.stockModalData.companyCode);
  //     let seletedObj = this.listOfCompany[index];
  //     this.closeStockUpdateModal();
  //     alert(data.message);
  //     seletedObj.stockPrice = this.stockModalData.stockPrice;
  //     this.stockModalData = new StockModel();
  //   }, error => {
  //     console.log("error occured " + JSON.stringify(error));
  //   });
  // }

  updateCompanyInfo = () => {
    this.companyService.updateCompany(this.companyModalData).subscribe(data=>{
      let index = this.listOfCompany.findIndex(item => item.companyCode==this.companyModalData.companyCode);
      this.closeCompanyUpdateModal();
      alert(data.message);

      this.listOfCompany[index] = this.companyModalData;
      this.matDataSource.data = this.listOfCompany;
      this.companyModalData = new CompanyModel();
    }, error => {
      console.log("error occured " + JSON.stringify(error));
    });
  }

  applyFilter = () => {
    this.matDataSource.filter = this.searchedKey.trim();
  }

  clearFilter = () => {
    this.searchedKey = "";
    this.applyFilter();
  }
}
