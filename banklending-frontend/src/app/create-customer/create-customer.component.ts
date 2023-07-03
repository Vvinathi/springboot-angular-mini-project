import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CustomerMaster } from '../CustomerMaster';
import { CustomerserviceService } from '../customerservice.service';

@Component({
  selector: 'app-create-customer',
  templateUrl: './create-customer.component.html',
  styleUrls: ['./create-customer.component.css']
})
export class CreateCustomerComponent {
  loanTypes: string[] = ['Personal Loan', 'Home Loan', 'Car Loan'];
  customer: CustomerMaster = new CustomerMaster();
  backendAPI = 'http://localhost:8081/api/customers/new'; 
  submissionStatus: string = ''; 

  constructor(private http: HttpClient, private customerService: CustomerserviceService) {}

  submitForm(customerForm: any) {
    console.log('submitForm function called.');
    if (customerForm.valid) {
     
      this.http.post<CustomerMaster>(this.backendAPI, this.customer).subscribe(
        (response) => {
         
          console.log('Form submitted successfully!');
          console.log('Response:', response);
          this.submissionStatus = 'Form submitted successfully!';
        
          customerForm.reset();
        },
        (error) => {
        
          console.error('Form submission failed:', error);
          this.submissionStatus = 'Form submission failed.';
        }
      );
    }
  }
}
