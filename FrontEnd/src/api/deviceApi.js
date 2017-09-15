class deviceApi {
    static getAllDevices() {
        return fetch('http://localhost:4567/get-all-devices').then(response => {
            return response.json();
        }).catch(error => {
            return error;
        });
    }

    static createDevice(device) {
        const request = new Request('http://localhost:4567/create-new-device', {
            method: 'POST',
            headers: new Headers({
                'Content-Type': 'application/json'
            }),
            body: JSON.stringify({ device })
        });

        return fetch(request).then(response => {
            return response.json();
        }).catch(error => {
            return error;
        });
    }
}

export default deviceApi;
