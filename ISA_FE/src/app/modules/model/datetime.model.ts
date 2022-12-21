export class DateTimeDTO {
    date: Date = new Date();
    startTime: String = "";
    
    public constructor(obj?: any) {
        if (obj) {
            this.date = obj.date;
            this.startTime = obj.startTime;  
        }
    }
}