import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CustomerserviceService } from '../customerservice.service';

@Component({
  selector: 'app-update-customer',
  templateUrl: './update-customer.component.html',
  styleUrls: ['./update-customer.component.css']
})
export class UpdateCustomerComponent implements OnInit {
  custId!: number;
  customerDetails: any = {};
  updatedData: any = {};
  updateStatus: string = ''; 

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private customerService: CustomerserviceService
  ) {}

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      const custId = params.get('custId');
      if (custId !== null) {
        this.custId = +custId;
        this.fetchCustomerDetails();
      } else {
      
        console.error('No custId parameter provided');
      }
    });
  }

  fetchCustomerDetails() {
    this.customerService.getCustomerById(this.custId).subscribe(
      (response: any) => {
        this.customerDetails = response;
        this.updatedData = { ...this.customerDetails };
        this.updatedData.birthDate = new Date(this.customerDetails.birthDate); 
      },
      (error: any) => {
        console.error(error);
      }
    );
  }

  updateCustomer(): void {
    this.customerService.updateCustomer(this.updatedData).subscribe(
      (response: any) => {
        console.log('Customer updated successfully:', response);
        this.updateStatus = 'Customer updated successfully.';
        
      },
      (error: any) => {
        console.error('Failed to update customer:', error);
        this.updateStatus = 'Failed to update customer.';
      }
    );
  }
}
