export class Survey {
    bloodType: BloodType = 0;
    generalCondition: String = "";
    systolicBP: number = 0;
    diastolicBP: number = 0;
    pulse: number = 0;
    usedBags: number = 0;
    
    public constructor(obj?: any) {
        if (obj) {
            this.bloodType = obj.bloodType;
            this.generalCondition = obj.generalCondition;
            this.systolicBP = obj.systolicBP;
            this.diastolicBP = obj.diastolicBP;
            this.pulse = obj.pulse; 
            this.usedBags = obj.usedBags;    
        }
    }
}

export enum BloodType{
    Apos,Aneg,Bpos,Bneg,ABpos,ABneg,Opos,Oneg
}