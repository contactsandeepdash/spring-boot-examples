processing - does not change the body of the message
transformation - change the body of the message

command to launch activemq on docker is below
docker pull rmohr/activemq
docker run -p 61616:61616 -p 8161:8161 rmohr/activemq
console | admin:admin


after added Kafka, receiver was not starting.
in MAC, need to add the hostname appeared on log file.