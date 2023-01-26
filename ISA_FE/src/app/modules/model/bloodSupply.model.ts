export class BloodSupply {
    id: number = 0;
    bloodType: BloodTypeDTO = 0;
    quantity: number = 0;
    bloodBankID: number = 0;


    public constructor(obj?: any) {
        if (obj) {
            this.id = obj.id;
            this.bloodType = obj.bloodType;
            this.quantity = obj.quantity;
            this.bloodBankID = obj.bloodBankID;     
        }
    }
}

export enum BloodTypeDTO{
    Aneg,Apos,Bneg,Bpos,ABpos,ABneg,Opos,Oneg
}