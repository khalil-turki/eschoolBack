import {Component, OnInit, OnDestroy, ViewEncapsulation, ViewChild} from '@angular/core';
import {Router} from '@angular/router';
import {NgForm} from '@angular/forms';
import {Breadcrumb} from 'app/layout/components/content-header/breadcrumb/breadcrumb.component';

import {Subject} from 'rxjs';
import {takeUntil} from 'rxjs/operators';
import {FlatpickrOptions} from 'ng2-flatpickr';
import {cloneDeep} from 'lodash';

import {UserEditService} from 'app/main/apps/user/user-edit/user-edit.service';
import {User} from "../../../../auth/models";
import {readFileAsync} from "../../../../utils/fileUtils";

@Component({
  selector: 'app-user-edit',
  templateUrl: './user-edit.component.html',
  styleUrls: ['./user-edit.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class UserEditComponent implements OnInit, OnDestroy {
  public url = this.router.url;
  public urlLastValue;
  public rows;
  public currentRow: User;
  public tempRow;
  public avatarImage: string;
  public breadcrumbDefault: Breadcrumb = {
    links: [
      {
        name: 'Home',
        isLink: true,
        link: '/'
      }, {
        name: 'User',
        isLink: true,
        link: '/apps/user/user-list'
      }, {
        name: 'Edit',
        isLink: false,
        link: ''
      }]
  };

  @ViewChild('accountForm') accountForm: NgForm;

  public birthDateOptions: FlatpickrOptions = {
    altInput: true,
    altFormat: 'Y-m-d',
  };

  private _unsubscribeAll: Subject<any>;

  constructor(private router: Router, private _userEditService: UserEditService) {
    this._unsubscribeAll = new Subject();
    this.urlLastValue = this.url.substr(this.url.lastIndexOf('/') + 1);
  }

  resetFormWithDefaultValues() {
    this.accountForm.resetForm(this.tempRow);
  }

  async uploadImage(event: any) {
    if (event.target.files && event.target.files[0]) {
      let fileContents = await readFileAsync(event.target.files[0]);
      let res = await this._userEditService.uploadImage(fileContents);
      this.currentRow.avatar = res.url;
    }
  }

  async submit(form) {
    if (form.valid) {
      await this._userEditService.saveUser(this.currentRow);
      this.router.navigate(['/apps/user/user-list']);
    }
  }

  ngOnInit(): void {
    this.currentRow = new User();
    this._userEditService.onUserEditChanged.pipe(takeUntil(this._unsubscribeAll)).subscribe(response => {
      this.currentRow = response;
      this.avatarImage = this.currentRow.avatar;
      this.tempRow = cloneDeep(response);
    });
  }

  ngOnDestroy(): void {
    this._unsubscribeAll.next();
    this._unsubscribeAll.complete();
  }

  async removeAvatar() {
    await this._userEditService.removeAvatar();
    this.currentRow.avatar = '';
    this.avatarImage = '';
  }
}
