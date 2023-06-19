import { Slider } from 'primereact/slider';
import { Tooltip } from 'primereact/tooltip';

import './prime-slider-custom.css';

export const PrimeSliderCustom = ({ field, config }) => {
	return (
        <div className="slider-container">
            <Tooltip
                target={`.slider-${field.name}>.p-slider-handle`}
                content={`${field.value}`}
                position="bottom"
                event="focus"
            />
            <Slider 
                id={field.name}
                name={field.name} 
                className={`slider-${field.name}`}
                min={config.min}
		 		max={config.max} 
                value={field.value} 
                onChange={(e) => field.onChange(e.value)}
            />
            <span className="min-max-slider-values">
		 		<small>{config.min}</small>
		 		<small>{config.max}</small>
		 	</span>
        </div>
	);
};