import {Component, OnInit, OnDestroy, ViewEncapsulation, ViewChild} from '@angular/core';
import {Router} from '@angular/router';
import {NgForm} from '@angular/forms';

import {Subject} from 'rxjs';
import {takeUntil} from 'rxjs/operators';
import {FlatpickrOptions} from 'ng2-flatpickr';
import {cloneDeep} from 'lodash';

import {UserEditService} from 'app/main/apps/user/user-edit/user-edit.service';
import {User} from "../../../../auth/models";

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

    @ViewChild('accountForm') accountForm: NgForm;

    public birthDateOptions: FlatpickrOptions = {
        altInput: true
    };

    private _unsubscribeAll: Subject<any>;

    constructor(private router: Router, private _userEditService: UserEditService) {
        this._unsubscribeAll = new Subject();
        this.urlLastValue = this.url.substr(this.url.lastIndexOf('/') + 1);
    }

    resetFormWithDefaultValues() {
        this.accountForm.resetForm(this.tempRow);
    }

    uploadImage(event: any) {
        if (event.target.files && event.target.files[0]) {
            let reader = new FileReader();

            reader.onload = (event: any) => {
                this.avatarImage = event.target.result;
            };

            reader.readAsDataURL(event.target.files[0]);
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
}
