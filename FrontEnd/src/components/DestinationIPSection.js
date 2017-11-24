import React from 'react';
import PropTypes from 'prop-types';
import {
  Button,
} from 'react-bootstrap';

const DestinationIPSection = (props) => {
  const handleChange = (e) => {
    props.onChange(props.name, e.target.value);
  };

  return (
    <div
      className={'destinationIPDiv'}>
      <span
        className={'labelSpan'}>
        {"Destination IP"}
      </span>
      <input
        type={"text"}
        name={'destinationIP'}
        value={props.value}
        onChange={handleChange} />
      <Button
        className={'destinationIPSaveButton'}
        bsStyle="primary"
        onClick={props.save}>
        {"Save"}
      </Button>
    </div>
  );
};

DestinationIPSection.propTypes = {
  name: PropTypes.string,
  value: PropTypes.oneOfType([
    PropTypes.string,
    PropTypes.number,
  ]),
  onChange: PropTypes.func.isRequired,
  save: PropTypes.func.isRequired,
};

export default DestinationIPSection;
