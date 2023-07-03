import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CustomerMaster } from './CustomerMaster';

@Injectable({
  providedIn: 'root'
})
export class CustomerserviceService {
  public baseURL = 'http://localhost:8081/api/customers'; // Replace with your actual backend API URL

  constructor(private httpClient: HttpClient) { }

  getCustomerById(custId: number): Observable<CustomerMaster> {
    return this.httpClient.get<CustomerMaster>(`${this.baseURL}/${custId}`);
  }

  updateCustomer(custId: number, customer: CustomerMaster): Observable<CustomerMaster> {
  return this.httpClient.put<CustomerMaster>(`${this.baseURL}/${custId}/update`, customer);
}

}
