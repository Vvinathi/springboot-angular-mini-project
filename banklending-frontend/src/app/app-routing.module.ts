import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateCustomerComponent } from './create-customer/create-customer.component';
import { UpdateCustomerComponent } from './update-customer/update-customer.component';
import { NewLoanApplicationComponent } from './new-loan-application/new-loan-application.component';
import { UpdateLoanApplicationComponent } from './update-loan-application/update-loan-application.component';
import { HomeComponent } from './home/home.component';
import { GetStartedComponent } from './get-started/get-started.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'get-started', component: GetStartedComponent },
  { path: 'create-customer', component: CreateCustomerComponent },
  { path: 'update-customer', component: UpdateCustomerComponent },
  { path: 'new-loan-application', component: NewLoanApplicationComponent },
  { path: 'loan-application', component: UpdateLoanApplicationComponent },
];


@NgModule({
  imports: [RouterModule.forRoot(routes, { enableTracing: true })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
