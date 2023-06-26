import {Component, OnInit} from '@angular/core';
import {Book, HttpClientService} from '../service/http-client.service';
import {ActivatedRoute} from '@angular/router';
import {BookResponse} from '../homepage/homepage.component';

@Component({
  selector: 'app-book-page',
  templateUrl: './book-page.component.html',
  styleUrls: ['./book-page.component.css']
})
export class BookPageComponent implements OnInit {

  id = 1;

  book: Book | null = null;

  constructor(
    private httpClientService: HttpClientService, private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id')!);
    this.httpClientService.getBook(this.id).subscribe(
      response => {
        this.handleResponse(response);
      }
    );
  }

  handleResponse(response: Book) {
    this.book = response;
  }

  getFilledStars(grade: number): number[] {
    const filledStars = Math.floor(grade);
    return Array(filledStars).fill(0);
  }

  getEmptyStars(grade: number): number[] {
    const emptyStars = 10 - Math.ceil(grade);
    return Array(emptyStars).fill(0);
  }
}
