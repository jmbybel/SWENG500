import React from 'react';
import PropTypes from 'prop-types';
import {
  DropdownButton,
  MenuItem,
} from 'react-bootstrap';

const NewSensorRandomIntervalDropdown = (props) => {
  const handleSelect = (eventKey, event) => {
    props.onSelect(eventKey, event);
  };

  return (
    <DropdownButton
      id={props.id}
      bsStyle={'primary'}
      title={'Select...'}
      onSelect={handleSelect}>
      <MenuItem eventKey="1">True</MenuItem>
      <MenuItem eventKey="2">False</MenuItem>
    </DropdownButton>
  );
};

NewSensorRandomIntervalDropdown.propTypes = {
  onSelect: PropTypes.func.isRequired,
};

export default NewSensorRandomIntervalDropdown;
