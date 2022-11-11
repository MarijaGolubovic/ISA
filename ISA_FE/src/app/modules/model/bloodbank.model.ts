import { Address } from "./address.model";

export class BloodBankRequest {
    name: string = '';
    description: string = '';
    address: Address = new Address();

    public constructor(obj?: any) {
        if (obj) {
            this.name = obj.name;
            this.description = obj.description;
            this.address = obj.address;       
        }
    }
}