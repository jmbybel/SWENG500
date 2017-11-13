import React from 'react';
import PropTypes from 'prop-types';

const NewSensorTextInput = (props) => {
  const handleChange = (e) => {
    props.onChange(props.name, e.target.value);
  };

  return (
    <input
      type="text"
      placeholder={props.placeholder}
      value={props.value}
      onChange={handleChange} disabled={props.disabled} />
  );
};

NewSensorTextInput.propTypes = {
  name: PropTypes.string,
  placeholder: PropTypes.string,
  disabled: PropTypes.boolean,
  value: PropTypes.oneOfType([
    PropTypes.string,
    PropTypes.number,
  ]),
  onChange: PropTypes.func.isRequired,
};

export default NewSensorTextInput;
