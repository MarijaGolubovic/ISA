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
    time?: Date;
    duration?: number;
    userName?: string;
    userSurname?: string;
  }

export class AppointmentResponse implements IAppointmentResponse{
    time: Date = new Date();
    duration: number = 30;
    userName?: string;
    userSurname?: string;
  
    constructor(data?: IAppointmentResponse) {
      if (data) {
        for (var property in data) {
          if (data.hasOwnProperty(property))
            (<any>this)[property] = (<any>data)[property];
        }
      }
    }

    
  init(_data?: any) {
    if (_data) {
      this.time = _data["time"];
      this.duration = _data["duration"];
      this.userName = _data["userName"];
      this.userSurname = _data["userSurname"];
    }
  }

  static fromJS(data: any): AppointmentResponse {
    data = typeof data === 'object' ? data : {};
    let result = new AppointmentResponse();
    result.init(data);
    return result;
  }

  toJSON(data?: any) {
    data = typeof data === 'object' ? data : {};
    data["time"] = this.time;
    data["duration"] = this.duration;
    data["userName"] = this.time;
    data["userSurname"] = this.duration;
    return data;
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

