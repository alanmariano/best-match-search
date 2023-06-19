import { Card } from 'primereact/card';

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { icon } from '@fortawesome/fontawesome-svg-core/import.macro'

import './restaurant-card.css';

export default function RestaurantCard({index, restaurant}) {
    return (
        <div data-cy="restaurant-card" className="restaurant-card">
            <Card title={"#"+index+" "+restaurant.name}>
                <div className="restaurant-info">
                    <FontAwesomeIcon style={{color: "#ff6140", marginRight: "0.5rem"}} icon={icon({name: 'location-dot'})} /><span>{restaurant.distance} mi</span>
                </div>
                <div className="restaurant-info">
                    <FontAwesomeIcon style={{color: "#ffb300", marginRight: "0.5rem"}} icon={icon({name: 'star'})} /><span>{restaurant.rating} stars</span>
                </div>
                <div className="restaurant-info">  
                    <FontAwesomeIcon style={{color: "#992626", marginRight: "0.5rem"}} icon={icon({name: 'wallet'})} /><span>{restaurant.price}$ per person</span>  
                </div>
                <div className="restaurant-info">              
                    <FontAwesomeIcon style={{color: "#ff4545", marginRight: "0.5rem"}} icon={icon({name: 'heart'})} /><span>{restaurant.cuisine.name} cuisine</span>
                </div>
            </Card>
        </div>  
    )
}