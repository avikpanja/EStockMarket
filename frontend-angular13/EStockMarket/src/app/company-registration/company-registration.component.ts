import { Component, OnInit } from '@angular/core';
import { CompanyModel } from '../company/company.model';
import { CompanyService } from '../company/company.service';

@Component({
  selector: 'app-company-registration',
  templateUrl: './company-registration.component.html',
  styleUrls: ['./company-registration.component.css']
})
export class CompanyRegistrationComponent implements OnInit {

  constructor(
    private companyService: CompanyService,
  ) { }

  stockExchangeList = ['BSE', 'NSE'];
  newCompany = new CompanyModel();
  //alertMessage: string = "";

  // alertElement = `<div class="alert alert-success fade show" role="alert">
  // {{alertMessage}}
  // <button type="button" class="close" data-dismiss="alert" aria-label="Close">
  //     <span aria-hidden="true">&times;</span>
  // </button>`;

  ngOnInit(): void {
    //$('.alert').alert('close'); 
  }

  registerNewCompany = (regForm:any) => {
    console.log(regForm);
    this.companyService.addCompany(this.newCompany).subscribe(data=> {
      alert(data.body.message);
      window.location.reload();
    },
    error => {
      console.log("Registration unsuccessful \n"+JSON.stringify(error));
      alert(error.error.message);
    });
  };
}
