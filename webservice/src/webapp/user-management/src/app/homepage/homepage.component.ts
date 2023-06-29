import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {Book, HttpClientService, User} from "../service/http-client.service";
import {ActivatedRoute} from "@angular/router";

export class BookResponse {
  constructor(
    public count: number,
    public bookDtos: Book[]
) {
  }
}

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {

  bookCount: number = 12;

  books: Book[] = [];
  page: string | null = '1';
  searchQuery: string | null = null;

  searchInput : string | null = null;
  loading: boolean = false; // Flag to track loading state

  constructor(
    private httpClientService: HttpClientService, private route: ActivatedRoute, private cdr: ChangeDetectorRef
  ) { }

  ngOnInit(): void {
    this.loading = true;
    this.route.queryParams.subscribe(params => {
      this.searchQuery = params.search;
    });
    this.page = this.route.snapshot.paramMap.get('page')!;
    this.httpClientService.getBooksPaginated(this.page, 9, this.searchQuery, 'id', true).subscribe(
        response => {
          this.loading = false;
          this.handleResponse(response);
        },
        error => {
        this.loading = false;
      }
    );
  }

  handleResponse(response: BookResponse) {
    this.books = response.bookDtos;
    this.bookCount = response.count;
  }

  getGroupElements(array: any[], groupIndex: number): Book[] {
    const startIndex = groupIndex * 3;
    const endIndex = startIndex + 3;
    return array.slice(startIndex, endIndex);
  }

  getPageCount(): number {
    return Math.ceil(this.bookCount / 9);
  }

  getCurrentPage(): number {
    return Number(this.page);
  }

  applySearch() {
    this.searchQuery = this.searchInput;
  }

  applySort(field: string, asc: boolean) {
    this.httpClientService.getBooksPaginated(this.page!, 9, this.searchQuery, field, asc).subscribe(
      response => {
        this.loading = false;
        this.handleResponse(response);
        this.cdr.detectChanges();
      },
      error => {
        this.loading = false;
      }
    );
  }
}
