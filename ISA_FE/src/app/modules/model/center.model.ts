export class CenterResponse {
    name: string = '';
    city: string = '';
    street: string = '';
    grade: number = 0;

    public constructor(obj?: any) {
        if (obj) {
            this.name = obj.name;
            this.city = obj.city;
            this.street = obj.street;
            this.grade = obj.grade;        
        }
    }
}

