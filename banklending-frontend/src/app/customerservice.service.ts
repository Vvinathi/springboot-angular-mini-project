import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CustomerMaster } from './CustomerMaster';
import { LoanApplicationModel } from './LoanApplication';
@Injectable({
  providedIn: 'root'
})
export class CustomerserviceService {
  public baseURL = 'http://localhost:8081/api';

  constructor(private httpClient: HttpClient) { }

  addCustomer(customer: CustomerMaster): Observable<CustomerMaster> {
    return this.httpClient.post<CustomerMaster>(`${this.baseURL}/customers`, customer);
  }

  getCustomerById(custId: number): Observable<CustomerMaster> {
    const url = `${this.baseURL}/customers/${custId}`;
    return this.httpClient.get<CustomerMaster>(url);
  }

  updateCustomer(updatedData: any): Observable<any> {
    const url = `${this.baseURL}/customers/${updatedData.custId}/update`;
    return this.httpClient.put<any>(url, updatedData);
  }
  getLoanApplicationById(loanAppId: number): Observable<LoanApplicationModel> {
    const url = `${this.baseURL}/loanapplications/loan/${loanAppId}`;
    return this.httpClient.get<LoanApplicationModel>(url);
  }

  updateLoanApplication(loanAppId: number, loanApplication: LoanApplicationModel): Observable<any> {
    const url = `${this.baseURL}/loanapplications/loan/${loanAppId}/update`;
    return this.httpClient.put<any>(url, loanApplication);
  }
}
