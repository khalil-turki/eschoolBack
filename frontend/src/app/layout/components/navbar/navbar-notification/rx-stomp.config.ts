import {RxStompConfig} from "@stomp/rx-stomp";

function getToken() {
    if (localStorage.getItem('currentUser')) {
        return "Bearer " + JSON.parse(localStorage.getItem('currentUser')).token;
    } else {
        return null;
    }
}

export const myRxStompConfig: RxStompConfig = {
    brokerURL: `ws://http://172.18.137.75:8087/ws`,
    connectHeaders: {
        Authorization: getToken()
    },
    reconnectDelay: 500,
    debug: (msg: string): void => {
        //console.log(new Date(), msg);
    },
};