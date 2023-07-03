import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CustomerserviceService } from '../customerservice.service';
import { LoanApplicationModel } from '../LoanApplication';



@Component({
  selector: 'app-update-loan-application',
  templateUrl: './update-loan-application.component.html',
  styleUrls: ['./update-loan-application.component.css']
})

export class UpdateLoanApplicationComponent implements OnInit {
  loanAppId!: number;
  loanApplication: LoanApplicationModel = new LoanApplicationModel();
  updateStatus: string = ''; 

  constructor(
    private route: ActivatedRoute,
    private customerService: CustomerserviceService
  ) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const loanAppId = params.get('loanAppId');
      if (loanAppId) {
        this.loanAppId = +loanAppId;
        this.getLoanApplication();
      }
    });
  }

  getLoanApplication(): void {
    this.customerService.getLoanApplicationById(this.loanAppId)
      .subscribe(
        (response) => {
          this.loanApplication = response;
        },
        (error) => {
          console.error('Error retrieving loan application:', error);
         
        }
      );
  }

  updateLoanApplication(): void {
    this.customerService.updateLoanApplication(this.loanAppId, this.loanApplication)
      .subscribe(
        (response) => {
          console.log('Loan application updated successfully:', response);
          this.updateStatus = 'Loan application updated successfully.';
          
        },
        (error) => {
          console.error('Error updating loan application:', error);
          this.updateStatus = 'Error updating loan application.';
          
        }
      );
  }
}
