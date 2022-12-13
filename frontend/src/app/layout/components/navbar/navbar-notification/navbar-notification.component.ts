import {Component, OnInit} from '@angular/core';

import {NotificationsService} from 'app/layout/components/navbar/navbar-notification/notifications.service';
import {RxStompService} from "./rx-stomp.service";
import {Message} from "@stomp/stompjs";
import {Subscription} from "rxjs";
import {Notification} from "./notification.model";
import {Router} from "@angular/router";


@Component({
    selector: 'app-navbar-notification',
    templateUrl: './navbar-notification.component.html'
})
export class NavbarNotificationComponent implements OnInit {
    public notifications: Notification[];
    private topicSubscription: Subscription;

    constructor(private _notificationsService: NotificationsService,
                private rxStompService: RxStompService,
                private _router: Router) {

    }

    ngOnInit(): void {
        this._notificationsService.onApiDataChange.subscribe(res => {
            this.notifications = res;
        });
        this.topicSubscription = this.rxStompService
            .watch('/user/queue/notification')
            .subscribe((message: Message) => {
                const notification = JSON.parse(message.body) as Notification;
                this.notifications.push(notification);
            });
    }

    ngOnDestroy(): void {
        this.topicSubscription.unsubscribe();
    }

    async markAsRead(notification: Notification): Promise<void> {
        await this._notificationsService.markAsRead(notification);
        notification.isRead = true;
    }

    async markAllAsRead(): Promise<void> {
        await this._notificationsService.markAllAsRead();
        this.notifications.forEach(notification => {
            notification.isRead = true;
        });
    }

    countUnreadNotifications(): number {
        return this.notifications.filter(notification => !notification.isRead).length;
    }
}
