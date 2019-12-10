import { sendRequest } from 'selenium-webdriver/http'

export class ChatMessage{
    sender: string;
    message: string;
    date: Date;

}

export class ChatMessageEvent{
    message: ChatMessage
}