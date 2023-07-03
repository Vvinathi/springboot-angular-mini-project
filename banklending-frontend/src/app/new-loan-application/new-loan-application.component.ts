import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { LoanApplicationModel } from '../LoanApplication'; 

@Component({
  selector: 'app-new-loan-application',
  templateUrl: './new-loan-application.component.html',
  styleUrls: ['./new-loan-application.component.css']
})
export class NewLoanApplicationComponent {
  public loanApplication: LoanApplicationModel = new LoanApplicationModel();
  backendAPI = 'http://localhost:8081/api/loanapplications/loan/new';
  creationStatus: string = ''; 

  constructor(private http: HttpClient) { }

  createLoanApplication(): void {
    this.http.post<LoanApplicationModel>(this.backendAPI, this.loanApplication)
      .subscribe(
        (response) => {
          console.log('Loan application created successfully:', response);
          this.creationStatus = 'Loan application created successfully.';
          
        },
        (error) => {
          console.error('Error creating loan application:', error);
          this.creationStatus = 'Error creating loan application.';
          
        }
      );
  }
}
