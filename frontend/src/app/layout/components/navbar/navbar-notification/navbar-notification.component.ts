import {Component, OnInit} from '@angular/core';

import {NotificationsService} from 'app/layout/components/navbar/navbar-notification/notifications.service';
import {RxStompService} from "./rx-stomp.service";
import {Message} from "@stomp/stompjs";
import {Subscription} from "rxjs";

interface notification {
    title: string;
    content: string;
    icon: string;
    isRead: boolean;
}

@Component({
    selector: 'app-navbar-notification',
    templateUrl: './navbar-notification.component.html'
})
export class NavbarNotificationComponent implements OnInit {
    public notifications: notification[];
    private topicSubscription: Subscription;

    constructor(private _notificationsService: NotificationsService,
                private rxStompService: RxStompService) {
    }

    ngOnInit(): void {
        this._notificationsService.onApiDataChange.subscribe(res => {
            this.notifications = res;
        });
        this.topicSubscription = this.rxStompService
            .watch('/user/queue/notification')
            .subscribe((message: Message) => {
                const notification = JSON.parse(message.body) as notification;
                this.notifications.push(notification);
            });
    }

    ngOnDestroy(): void {
        this.topicSubscription.unsubscribe();
    }
}
