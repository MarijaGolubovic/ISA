import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, Subscriber } from 'rxjs';
import { NewsForHospital } from '../model/newsForHospital.model';
import { NewsService } from '../services/news.service';

@Component({
  selector: 'app-send-news-to-hospital',
  templateUrl: './send-news-to-hospital.component.html',
  styleUrls: ['./send-news-to-hospital.component.css']
})
export class SendNewsToHospitalComponent implements OnInit {

  public news: NewsForHospital = new NewsForHospital()

  constructor(private newsService: NewsService, private router: Router) { }

  ngOnInit(): void {
  }

  public sendNews(){
    this.newsService.sendNews(this.news).subscribe(res => {
      alert("Successfull!")
    })
  }

  fileChangeEvent = ($event: Event) => {
    const target = $event.target as HTMLInputElement;

    const file: File = (target.files as FileList)[0];

    this.convetToBase64(file);
  }

  convetToBase64(file: File){
    const observable = new Observable((subscriber: Subscriber<any>) => {
      this.readFile(file, subscriber);
    })
    observable.subscribe((d) => {
      this.news.base64image = d;
    })
  }

  readFile(file: File, subscriber: Subscriber<any>){
    const fileReader = new FileReader();

    fileReader.readAsDataURL(file);

    fileReader.onload = () => {
      subscriber.next(fileReader.result);

      subscriber.complete()
    }

    fileReader.onerror = () => {
      subscriber.error()
      subscriber.complete()
    }
  }
}
