import React from 'react';
import PropTypes from 'prop-types';
import {
  Label,
} from 'react-bootstrap';

const DestinationIPSection = (props) => {
  return (
    <div
      className={'destinationIPDiv'}>
      <span
        className={'labelSpan'}>
        <h4>
          {"The Payload Destination Address is "}
          <Label bsStyle="info">
            {props.value}
          </Label>
        </h4>
      </span>
    </div>
  );
};

DestinationIPSection.propTypes = {
  value: PropTypes.oneOfType([
    PropTypes.string,
    PropTypes.number,
  ]),
};

export default DestinationIPSection;
