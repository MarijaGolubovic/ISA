export class CreateAppointmentDTO {
    bloodBankID: number = 0;
    date: Date = new Date();
    time: String = "";
    duration: number = 0;
    status: AppointmentStatus = AppointmentStatus.FREE;
    
    public constructor(obj?: any) {
        if (obj) {
            this.bloodBankID = obj.bloodBankID;
            this.date = obj.date;
            this.time = obj.time;
            this.duration = obj.duration;
            this.status = obj.status;    
        }
    }
}

export class AppointmentDTO {
    bloodBankID: number = 0;
    date: Date = new Date();
    time: String = "";
    duration: number = 0;
    status: AppointmentStatus = AppointmentStatus.FREE;
    
    public constructor(obj?: any) {
        if (obj) {
            this.bloodBankID = obj.bloodBankID;
            this.date = obj.date;
            this.time = obj.time;
            this.duration = obj.duration;
            this.status = obj.status;    
        }
    }
}

export enum AppointmentStatus{
    FREE,BUSY
}

export class FutureAppointmentDTO {
    bloodBankName: string = '';
    date: string = '';
    time: String = "";
    
    public constructor(obj?: any) {
        if (obj) {
            this.bloodBankName = obj.bloodBankID;
            this.date = obj.date;
            this.time = obj.time;   
        }
    }
}