export class LoanApplicationModel {
  public loanAppId: number;
  public customer: {
    custId: number;
  };
  public loanAmt: number;
  public noOfYears: number;
  public purpose: string;
  public appStatus: string;
  public typeOfLoan: string;
  public loanAppDate: Date;
  public status: string;

  constructor() {
    this.loanAppId = 0;
    this.customer = {
      custId: 0
    };
    this.loanAmt = 0;
    this.noOfYears = 0;
    this.purpose = '';
    this.appStatus = '';
    this.typeOfLoan = '';
    this.loanAppDate = new Date();
    this.status = '';
  }
}
