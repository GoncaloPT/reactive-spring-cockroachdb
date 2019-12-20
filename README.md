# reactive-spring-cockroachdb
Poc to show how to use spring reactive web with cockroach db in a chat application

## to run backend
backend/spring-boot:run
## to run frontend
(once) npm install
ng serve

## to run coackroach (on docker)
docker container run -p 26257:26257 043316b7542b start --insecure

### Backend startup
If a valid connection to coackroach database exists, than the app will make sure the chat_message table exists

### Limitations
Currently using a UnicastProcessor to allow pushing messages to frontend. 
In a distributed architecture, this will not work.
A message queue or other kind of publisher should we used