import { Observable } from "rxjs";

import * as _ from "lodash";
import { Injectable } from "@angular/core";
import { ChatMessage } from "../model/chat-message";
import { HttpClient } from "@angular/common/http";
import { resolve } from 'url';
@Injectable({
  providedIn: "root"
})
export class MessageService {
  constructor(private http: HttpClient) {}
  /**
   * Sends a new messages
   * @param message 
   */
  send(message: ChatMessage): Promise<ChatMessage> {
    return new Promise((resolve, reject) => {
      this.http.post<ChatMessage>("//localhost:5000/message", message)
      .subscribe((resultado: ChatMessage) => {
          resolve(resultado);
        },
        erro => {
          reject(erro);
        }
      );
    });
  }
  newEventSource(path: string): EventSource {
    return new EventSource(path);
  }
  newObservable<R>(
    path: string,
    converter: (data: string) => R = _.identity
  ): Observable<R> {
    return new Observable(observer => {
      const eventSource = this.newEventSource(path);
      eventSource.onmessage = event => {
        observer.next(converter(event.data));
      };
      eventSource.onerror = () => {
        if (eventSource.readyState !== eventSource.CONNECTING) {
          observer.error("An error occurred.");
        }
        eventSource.close();
        observer.complete();
      };
      return () => {
        eventSource.close();
      };
    });
  }
}
