import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Inject, Injectable, Optional } from "@angular/core";
import { Observable } from "rxjs";
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

export interface IAppointmentResponse {
    startTime?: Date;
    duration?: number;
    patientName?: string;
    patientSurname?: string;
  }

export class AppointmentResponse implements IAppointmentResponse{
    startTime: Date = new Date();
    duration: number = 30;
    patientName?: string;
    patientSurname?: string;
  
    constructor(data?: IAppointmentResponse) {
      if (data) {
        for (var property in data) {
          if (data.hasOwnProperty(property))
            (<any>this)[property] = (<any>data)[property];
        }
      }
    }
}

