import { BloodBankResponse } from "./bloodBank.model";
import { WholeUserResponse } from "./user.model";

export class Complaint {
    id: string = "";
    date: Date = new Date();
    description: string = "";
    reply: string = "";
    user: WholeUserResponse = new WholeUserResponse();
    bloodBank: BloodBankResponse = new BloodBankResponse();
    status: ComplaintStatus = ComplaintStatus.PENDING;
    
    public constructor(obj?: any) {
        if (obj) {
            this.id = obj.id;
            this.date = obj.date;
            this.description = obj.description;
            this.reply = obj.reply;
            this.user = obj.user;
            this.bloodBank = obj.bloodBank;
            this.status = obj.status;    
        }
    }
}

export enum ComplaintStatus{
    PENDING, REPLIED
}