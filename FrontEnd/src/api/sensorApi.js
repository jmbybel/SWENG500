class sensorApi {
    static getAllSensors() {
        return fetch('http://localhost:4000/get-all-sensors').then(response => {
            return response.json();
        }).catch(error => {
            return error;
        });
    }

    static createSensor(sensor) {
        const request = new Request('http://localhost:4000/create-new-sensor', {
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
