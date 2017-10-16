import React from 'react';
import PropTypes from 'prop-types';
import {
  DropdownButton,
  MenuItem,
} from 'react-bootstrap';

const NewDeviceSensorTypeDropdown = (props) => {
  const handleSelect = (eventKey, event) => {
    props.onSelect(eventKey, event);
  };

  return (
    <DropdownButton
      id={props.id}
      bsStyle={'primary'}
      title={'Select...'}
      onSelect={handleSelect}>
      <MenuItem eventKey="1">Sin</MenuItem>
      <MenuItem eventKey="2">Ramp</MenuItem>
      <MenuItem eventKey="3">Random</MenuItem>
      <MenuItem eventKey="4">Binary</MenuItem>
    </DropdownButton>
  );
};

NewDeviceSensorTypeDropdown.propTypes = {
  onSelect: PropTypes.func.isRequired,
};

export default NewDeviceSensorTypeDropdown;
