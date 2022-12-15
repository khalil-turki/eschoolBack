import { Component, OnInit } from '@angular/core';
import { CoreSidebarService } from '@core/components/core-sidebar/core-sidebar.service';
import {User} from "../../../../../auth/models";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../../../../environments/environment";

@Component({
  selector: 'app-new-user-sidebar',
  templateUrl: './new-user-sidebar.component.html'
})
export class NewUserSidebarComponent implements OnInit {
  public user: User;

  constructor(private _coreSidebarService: CoreSidebarService, private _httpClient: HttpClient) {
    this.user = new User();
  }

  toggleSidebar(name): void {
    this._coreSidebarService.getSidebarRegistry(name).toggleOpen();
  }

  submit(form) {
    if (form.valid) {
      this.toggleSidebar('new-user-sidebar');
      this._httpClient.post(`${environment.apiUrl}/signup`, this.user).subscribe();
    }
  }

  ngOnInit(): void {}
}
