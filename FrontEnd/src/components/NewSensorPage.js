import React from 'react';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';
import * as sensorActions from '../actions/sensorActions';
import NewSensorForm from '../components/NewSensorForm';
import {
  Tabs,
  Tab,
} from 'react-bootstrap';

class NewSensorPage extends React.Component {
  constructor(props, context) {
    super(props, context);

    this.state = {
      key: 1,
    };
    this.handleSelect = this.handleSelect.bind(this);
  }

  handleSelect(key) {
    this.setState({
      key,
    });
  }

  render() {
    const {
      props: {
        sensor,
        history,
        actions: {
          saveNewSensor,
        },
        onHistoryChanged,
      }
    } = this;

    return (
      <section>
        <Tabs activeKey={this.state.key} onSelect={this.handleSelect} id="controlled-tab-example" bsStyle={"tabs"}>
          <Tab eventKey={1} title="Properties">
            <NewSensorForm
              sensor={sensor}
              history={history}
              saveNewSensor={saveNewSensor}
              onHistoryChanged={onHistoryChanged} />
          </Tab>
          <Tab eventKey={2} title="Live">Live Time Series Chart</Tab>
        </Tabs>
      </section>
    );
  }
}

NewSensorPage.propTypes = {
  sensor: PropTypes.object,
  history: PropTypes.object,
  actions: PropTypes.object.isRequired,
};

function mapStateToProps(state) {
  return {
    sensor: state.sensor,
  };
}

function mapDispatchToProps(dispatch) {
  return {
    actions: bindActionCreators(sensorActions, dispatch)
  };
}

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(NewSensorPage);
