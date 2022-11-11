import { UserResponse } from "./user.model";

export class BloodBankResponse {
    name: string = '';
    descripton: string = '';
    averageRate: number = 0;
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
            this.descripton = obj.descripton;
            this.averageRate = obj.averageRate;
            this.address = obj.address;  
            this.administrators = obj.administrators;  
            this.workTime = obj.workTime;        
        }
    }
}