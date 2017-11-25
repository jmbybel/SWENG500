# IoT Data Generator SWENG 500 Group 1 Project

This project generates sample Sensors and Payloads to an endpoint based on user entry specification for their sensor generation. This is done using Java, React, Redux, MongoDB, AWS to host our payload endpoint, and Azure to host our backend and web server.

## Getting Started

To get started, clone our Github repo using [this link](https://github.com/jmbybel/SWENG500.git). Once the repo is cloned proceed below.

### Prerequisites

To run this on your system, you will need three pieces of software on your system:
1. [Java Runtime Environment](https://java.com/en/download/)
2. [Node.js](https://nodejs.org/en/download/)
3. [MongoDB](https://www.mongodb.com/download-center#community)


### Installing and Deployment

Steps for installing the project:

First step is to have all the prereqs met and the repo on your machine. 

Navigate to the github repo on your machine. 
Go to the FrontEnd folder, then hold Shift+RightClick to Open Command Window Here
Then in command prompt, run
```
npm install
```
Once that is done, run 
```
npm run build
```
To build and start the project. Leave the command prompt window open.

To have static pages, Ctrl-C from the run in the above command prompt. Then type
```
node app.js
```
Leave this window open which is now running the web server. 

Next navigate to your install folder for your mongodb database. 
In the folder that contains mongod.exe, hold Shift+RightClick to Open Command Window Here
Then in command prompt, run
```
mongod.exe
```
Leave the command prompt window open.

Go to the WebServer folder, then go to 
```
src/main/java/edu/psu/iot/webserver/
```
Hold Shift+RightClick to Open Command Window Here
Then in command prompt, run 
```
javac Main.java
```
Leave the command prompt window open.

Example of data that comes out of system running to the front end and endpoint:
```
{"name":"Ramp Sensor","id":3,"value":5}
{"name":"Random Sensor","id":1,"value":72}
{"name":"Sin Sensor","id":4,"value":0}
{"name":"Binary Sensor","id":2,"value":1}
```

## Built With

* [Java](http://www.java.com/) - The coding framework
* [Node.js](https://nodejs.org/en/) - The front end framework 
* [React](https://reactjs.org/) - The front end utility
* [MongoDB](https://www.mongodb.com/) - The database  
* [Maven](https://maven.apache.org/) - Dependency Management
* [JUnit](https://junit.org/) - Used to unit test the system

## Versioning

We used [github](https://github.com/jmbybel/SWENG500). 

## Authors

* **Cory Perdue** - *Initial and continued setup of Data Generator* - [dk-csperdue](https://github.com/dk-csperdue)
* **Michael Cantu** - *Assistance working on and testing Data Generator* - [MichaelCantu93](https://github.com/MichaelCantu93)
* **David Lambl** - *Group member working on front end* - [davidlambl](https://github.com/davidlambl)
* **Jeremy Bybel** - *Group member working on database/frontend* - [jmbybel](https://github.com/jmbybel)
* **James Ward** - *Scrum Master* - [wardja28](https://github.com/wardja28)

See also the list of [contributors](https://github.com/jmbybel/SWENG500/graphs/contributors) who participated in this project.

## Acknowledgments

* Thanks to our Group 1 for all doing such great work!
