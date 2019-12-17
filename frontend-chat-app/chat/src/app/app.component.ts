import { Component, OnInit, NgZone } from "@angular/core";
import { Observable } from "rxjs";
import { ChatMessage, ChatMessageEvent } from "./model/chat-message";
import { MessageService } from "./service/message.service";
import { FormGroup, FormControl, NgForm } from "@angular/forms";

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.less"]
})
export class AppComponent implements OnInit {
  title = "chat";
  event: Observable<ChatMessage>;
  message: string;
  name: string;
  messages: Array<ChatMessage> ;
  constructor(private messageService: MessageService,
    private ngZone: NgZone) {}
  ngOnInit(): void {
    this.messageService
      .newObservable<ChatMessage>("//localhost:5000/messages")
      .subscribe( newMessage => {
        console.log('received evt: ', newMessage);
        this.ngZone.run( () => {
          if(!this.messages)
          this.messages = [];
          this.messages.push(newMessage);
       });
      });
  }
  onSubmit(f: NgForm): void {
    if (f.value.message == null) throw 'Message cannot be null';
    var chatMessage = new ChatMessage();
    chatMessage.date = new Date();
    chatMessage.message = this.message;
    chatMessage.sender = this.name;
    this.messageService
      .send(chatMessage)
      .then(() => {
        this.message = "";
        console.log("chat message sent");
      })
      .catch(err => console.warn(err));
  }
}
