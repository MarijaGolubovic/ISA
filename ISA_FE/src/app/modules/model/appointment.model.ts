import { QUestionnaireRespons } from "./questionnaire.model";

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

export class AppointmentUserDTO {
    iD: number = 0;
    date: Date = new Date();
    time: String = "";
    duration: number = 0;
    status: AppointmentStatus = AppointmentStatus.FREE;
    userID: number = 0;
    questionnaire : QUestionnaireRespons = new QUestionnaireRespons();
    
    public constructor(obj?: any) {
        if (obj) {
            this.iD = obj.iD;
            this.date = obj.date;
            this.time = obj.time;
            this.duration = obj.duration;
            this.status = obj.status;   
            this.userID = obj.userID;  
            this.questionnaire = obj.questionnaire;
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

export class FutureAppointmentBBDTO {
    name: string = '';
    surname: string = '';
    id: number = 0;
    useriD: number = 0;
    date: string = '';
    time: String = "";
    
    public constructor(obj?: any) {
        if (obj) {
            this.name = obj.name;
            this.date = obj.date;
            this.time = obj.time; 
            this.id = obj.id; 
            this.useriD = obj.useriD; 
            this.surname = obj.surname;   
        }
    }
}