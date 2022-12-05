export class NewsForHospital {
    title: string = '';
    content: string = '';
    newsStatus: NewsForHopsitalStatus = NewsForHopsitalStatus.ON_HOLD;
    apiKey: string = '';
    bloodBankName: string = '';
    base64image: string = '';

    public constructor(obj?: any) {
        if (obj) {
            this.title = obj.country;
            this.content = obj.city;
            this.newsStatus = obj.street;
            this.apiKey = obj.number;
            this.base64image = obj.base64code;   
            this.bloodBankName = obj.bloodBankName; 
        }
    }
}

export enum NewsForHopsitalStatus{
    ON_HOLD,
	ACTIVE,
	REFUSED
}