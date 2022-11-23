import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { StockService } from './stock.service';
import { StockStatModel } from './stock-stat.model';
import { StockModel } from './stock.model';
import { CompanyModel } from '../company/company.model';
import { CompanyService } from '../company/company.service';
import { DatePipe } from '@angular/common';

declare var $: any;

@Component({
  selector: 'app-stock',
  templateUrl: './stock.component.html',
  styleUrls: ['./stock.component.css']
})
export class StockComponent implements OnInit {

  selectedCompanyCode : string | any;
  stockStatObj = new StockStatModel;
  stockHistory : StockModel[] = [];
  filteredStockHistory: StockModel[] = [];
  selectedComp = new CompanyModel; 
  stockModalData = new StockModel;
  dateRange = new DateRange();

  constructor(
    private route: ActivatedRoute, 
    private stockService: StockService,
    private compService: CompanyService,
    private datePipe: DatePipe
    ) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.selectedCompanyCode = params['companyCode'];
      this.fetchStockHistory("INIT");
      this.fetchCompanyDetails();
    });
    
  }

  fetchStockHistory = (indicator?:string) => {
    this.stockService.getStockHistory(this.selectedCompanyCode).subscribe(
      data => {
        if(data!=null) { 
          this.stockHistory = data.result;
          this.showStockHistoryAndStatics(indicator);
        } else {
          alert('No stock record found!');
        }
      }, 
      error => {
        console.log("Error occured while fetching stock History\n" + JSON.stringify(error));
      }
    );
  }

  showStockHistoryAndStatics = (indicator?:string) => {

    if(indicator=="INIT") {

      let fromDate = this.stockHistory.map(stock=>stock.createdDateTime).sort()[0];
      this.filteredStockHistory = this.applyFilterOnStockHistory(fromDate);

      this.dateRange.from=this.datePipe.transform(fromDate, 'yyyy-MM-dd');
      this.dateRange.to=this.datePipe.transform(new Date(), 'yyyy-MM-dd');
      
    } else {
      let fromDate = this.dateRange.from;
      let toDate = this.dateRange.to;
      this.filteredStockHistory = this.applyFilterOnStockHistory(fromDate,toDate);
    }
    

    if(this.filteredStockHistory.length>0) {
      let ssm = this.calculateStatics();
      this.stockStatObj = ssm;
    } else {
      this.stockStatObj.clear();
    }
  }

  applyFilterOnStockHistory (from:Date, to?: Date) : StockModel[] {
    
    // On date filter change this will exccuted
    if(from && to) {
      return this.stockHistory.filter(stock=> {
        let temp =  new Date(stock.createdDateTime);
        from = new Date(from);

        // Set the hours, will take the result upto 'to' date
        to = new Date(new Date((<Date>to)).setHours(23,59,59));
        if(temp>=from && temp<=to) return true;
        return false;
      });
    } 
    
    // On page load this will be executed
    return this.stockHistory.filter(stock=>stock.createdDateTime>=from);
  }

  calculateStatics () : StockStatModel  {
    let stockPrices = this.filteredStockHistory.map(stock => stock.stockPrice);  
    let min = Math.min(...stockPrices);
    let max = Math.max(...stockPrices);
    let avg = stockPrices.reduce((a,b)=> a+b, 0)/stockPrices.length;
    return new StockStatModel(min, max, avg);
  }

  fetchCompanyDetails = () => {
    this.compService.getDetailsOfACompany(this.selectedCompanyCode).subscribe(
      data => {
        this.selectedComp = data.body.result;
      }, 
      error => {

      }
    );
  }

  openStockUpdateModal = () => {
    this.stockModalData = new StockModel(this.selectedComp?.companyCode, this.selectedComp?.stockPrice);
    $('#stockPriceUpdateModal').modal('show');
  }

  closeStockUpdateModal = () => {
    $('#stockPriceUpdateModal').modal('hide');
  }

  updateStockPrice = () => {

    this.stockService.updateStockPrice(this.stockModalData).subscribe(data=>{
      
      this.closeStockUpdateModal();
      alert(data.message);
      this.selectedComp.stockPrice = this.stockModalData.stockPrice;
      this.stockModalData = new StockModel();
      this.fetchStockHistory();
    }, error => {
      console.log("error occured " + JSON.stringify(error));
    });
  }

  onDateChange = () => {
    this.showStockHistoryAndStatics();
  }
}
class DateRange {
  from: Date|any;
  to: Date|any;
}