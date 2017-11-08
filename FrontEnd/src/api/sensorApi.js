class sensorApi {
    static getNumberOfRunningSensors() {
        return fetch('http://sweng500-team1.cloudapp.net:4000/get-number-of-running-sensors').then(response => {
					return response.json();
        }).catch(error => {
					return error;
        });
    }

    static getAllSensors() {
        return fetch('http://sweng500-team1.cloudapp.net:4000/get-all-sensors').then(response => {
					return response.json();
        }).catch(error => {
					return error;
        });
    }

    static createSensor(sensor) {
        const request = new Request('http://sweng500-team1.cloudapp.net:4000/create-new-sensor', {
					method: 'POST',
					headers: new Headers({
							'Content-Type': 'application/json'
					}),
					body: JSON.stringify({ sensor })
        });

        return fetch(request).then(response => {
					return response.json();
        }).catch(error => {
					return error;
        });
    }
    
    static getSensor(sensorId) {
			if (sensorId != undefined) {
				return fetch('http://sweng500-team1.cloudapp.net:4000/get-sensor?sensorId='+ sensorId).then(response => {
					return response.json();
				}).catch(error => {
					return error;
				});
			}
    }
}

export default sensorApi;
