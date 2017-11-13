class sensorApi {
    static getNumberOfRunningSensors() {
        return fetch('http://iotgenerator.ga:4000/get-number-of-running-sensors').then(response => {
					return response.json();
        }).catch(error => {
					return error;
        });
    }

    static getAllSensors() {
        return fetch('http://iotgenerator.ga:4000/get-all-sensors').then(response => {
					return response.json();
        }).catch(error => {
					return error;
        });
    }

    static getSensor(sensorId) {
			if (sensorId != undefined) {
				return fetch('http://iotgenerator.ga:4000/get-sensor?sensorId=' + sensorId).then(response => {
					return response.json();
				}).catch(error => {
					return error;
				});
			}
    }

    static startSensor(sensorId) {
      const request = new Request('http://iotgenerator.ga:4000/start-sensor', {
        method: 'POST',
        headers: new Headers({
            'Content-Type': 'text/plain'
        }),
        body: `{"_id": ${sensorId}}`
      });

      return fetch(request).then(response => {
        return response.json();
      }).catch(error => {
        return error;
      });
    }

    static pauseSensor(sensorId) {
      const request = new Request('http://iotgenerator.ga:4000/pause-sensor', {
        method: 'POST',
        headers: new Headers({
            'Content-Type': 'text/plain'
        }),
        body: `{"_id": ${sensorId}}`
      });

      return fetch(request).then(response => {
        return response.json();
      }).catch(error => {
        return error;
      });
    }

    static createSensor(sensor) {
        const request = new Request('http://iotgenerator.ga:4000/create-new-sensor', {
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

    static updateSensor(sensor) {
      const request = new Request('http://iotgenerator.ga:4000/update-sensor', {
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
}

export default sensorApi;
