import React from 'react';
import { PageHeader } from 'react-bootstrap';
import ActiveSensorCount from '../components/ActiveSensorCount';

const myCount = 100;//TODO dummy value to be replaced by proper pull from the application.
const Dashboard = () => {
  return (
    <div>
    <section>
      <PageHeader>Mock IoT Data Generator Project</PageHeader>
    </section>
      <ActiveSensorCount theCount={myCount}/>
    </div>
  );
};

export default Dashboard;
