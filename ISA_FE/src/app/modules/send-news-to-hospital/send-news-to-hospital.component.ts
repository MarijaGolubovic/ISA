import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NewsService } from '../services/news.service';

@Component({
  selector: 'app-send-news-to-hospital',
  templateUrl: './send-news-to-hospital.component.html',
  styleUrls: ['./send-news-to-hospital.component.css']
})
export class SendNewsToHospitalComponent implements OnInit {

  public news: string = ''

  constructor(private newsService: NewsService, private router: Router) { }

  ngOnInit(): void {
  }

  public sendNews(){
    this.newsService.sendNews(this.news).subscribe(res => {
      alert("Successfull!")
    })
  }

}
