export class QUestionnaireRespons{
    question1:any=null;
    question2:any=null;
    question3:any=null;
    question4:any=null;
    question5:any=null;
    question6:any=null;
    question7:any=null;
    question8:any=null;
    question9:any=null;
    question10:any=null;
    question11:any=null;
    question12:any=null;

    public constructor(obj?: any){
        if (obj) {
            this.question1=obj.question1;
            this.question2=obj.question2;
            this.question3=obj.question3;
            this.question4=obj.question4;
            this.question5=obj.question5;
            this.question6=obj.question6;
            this.question7=obj.question7;
            this.question8=obj.question8;
            this.question9=obj.question9;
            this.question10=obj.question10;
            this.question11=obj.question11;
            this.question12=obj.question12;
            }
    }
}