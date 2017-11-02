import React from 'react';
import { PageHeader } from 'react-bootstrap';
import ActiveSensorCount from '../components/ActiveSensorCount';
import LiveDataFeed from '../components/LiveDataFeed';

const myCount = 100;//TODO dummy value to be replaced by proper pull from the application.

//TODO dummy values for live data feed til we can get pushed data
const rows = [{
  name: 'Sample Sensor 1',
  timestamp: 'Nov 1, 2017 4:12:04 AM',
  type: 'Random',
  payload: '67.889696'
},{
  name: 'Sample Sensor 2',
  timestamp: 'Nov 2, 2017 2:23:02 PM',
  type: 'Sin',
  payload: '36.0'
},{
  name: 'Sample Sensor 3',
  timestamp: 'Nov 2, 2017 2:43:01 PM',
  type: 'Ramp',
  payload: '109.2'
}] 
const Dashboard = () => {
  return (
    <section>
      <PageHeader>Mock IoT Data Generator Project</PageHeader>
      <ActiveSensorCount theCount={myCount}/>
      <LiveDataFeed sensors={rows} />
    </section>
  );
};

export default Dashboard;
