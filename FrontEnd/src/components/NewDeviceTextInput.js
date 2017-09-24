import React from 'react';
import PropTypes from 'prop-types';

const NewDeviceTextInput = (props) => {
  const handleChange = (e) => {
    props.onChange(props.name, e.target.value);
  };

  return (
    <input
      className="small"
      type="text"
      placeholder={props.placeholder}
      value={props.value}
      onChange={handleChange} />
  );
};

NewDeviceTextInput.propTypes = {
  placeholder: PropTypes.string,
  value: PropTypes.oneOfType([
    PropTypes.string,
    PropTypes.number,
  ]),
  onChange: PropTypes.func.isRequired,
};

export default NewDeviceTextInput;
