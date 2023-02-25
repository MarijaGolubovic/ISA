import { UserResponse } from "./user.model";

export class BloodBankResponse {
    name: string = '';
    description: string = '';
    averageRate: number = 0;
    id: number = 0;
    address = {
        country: "",
        city: "",
        street: "",
        number: ""
    };
    administrators: UserResponse[] = [];
    workTime = {
        startTime: new Date(),
        endTime: new Date()
    }
    


    public constructor(obj?: any) {
        if (obj) {
            this.name = obj.name;
            this.description = obj.descripton;
            this.averageRate = obj.averageRate;
            this.address = obj.address;  
            this.administrators = obj.administrators;  
            this.workTime = obj.workTime; 
            this.id = obj.id;       
        }
    }
}