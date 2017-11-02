import React from 'react';
import { PageHeader } from 'react-bootstrap';
import ActiveSensorCount from '../components/ActiveSensorCount';

const myCount = 100;//TODO dummy value to be replaced by proper pull from the application.
const Dashboard = () => {
  return (
    <section>
      <PageHeader>Mock IoT Data Generator Project</PageHeader>
      <ActiveSensorCount theCount={myCount}/>
    </section>
  );
};

export default Dashboard;
