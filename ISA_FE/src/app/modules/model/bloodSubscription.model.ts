export class BloodSubscriptionDTO {
    bloodBankName: string = '';
    dateAndTimeOfSubscription: Date = new Date();
    amountOfBloodTypes: AmountOfBloodTypeDTO[] = [];

    public constructor(obj?: any) {
        if (obj) {
            this.bloodBankName = obj.bloodBankName;
            this.dateAndTimeOfSubscription = obj.dateAndTimeOfSubscription;
            this.amountOfBloodTypes = obj.amountOfBloodTypes;       
        }
    }
}

export class AmountOfBloodTypeDTO{
    bloodType: BloodTypeDTO = BloodTypeDTO.ABneg;
    amount: number = 0;

    public constructor(obj?: any) {
        if (obj) {
            this.bloodType = obj.bloodType;
            this.amount = obj.amount;        
        }
    }
}

export enum BloodTypeDTO{
    Aneg,
	Apos,
	Bneg,
	Bpos,
	ABpos,
	ABneg,
	Opos,
	Oneg
}