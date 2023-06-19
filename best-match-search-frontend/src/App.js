import React, { Component } from 'react';
import './App.css';
import RestaurantCard from './components/restaurant-card/restaurant-card.component';
import RestaurantSearchForm from './components/restaurant-search-form/restaurant-search-form.component';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { icon } from '@fortawesome/fontawesome-svg-core/import.macro'
import { Divider } from 'primereact/divider';

import "primereact/resources/themes/lara-light-indigo/theme.css";     
import "primereact/resources/primereact.min.css";
import 'primeicons/primeicons.css';

class App extends Component {
  
  state = {
    restaurantNames: [],
    cuisineNames: [],
    filteredRestaurants: null,
    isLoading: true   
  };

  async componentDidMount() {
    const restaurantResponse = await fetch('/api/restaurants/names');
    const restaurantBody = await restaurantResponse.json();
    const cuisineResponse = await fetch('/api/cuisines/names');
    const cuisineBody = await cuisineResponse.json();
    this.setState({restaurantNames: restaurantBody, cuisineNames: cuisineBody, isLoading: false});
  }

  handleFilteredRestaurants = (restaurants) => {
    this.setState({filteredRestaurants: restaurants});
  }

  render() {

    const {restaurantNames, cuisineNames, filteredRestaurants,  isLoading} = this.state;

    if (isLoading) {
      return <p>Loading...</p>;
    }

    let restaurantsList;
    if(filteredRestaurants == null) {
      restaurantsList = 
        <div data-cy="welcome-message" className="type-a-search-container">
          <h1>Feeling hungry?</h1>
          <h2>Fill the form and find some awesome restaurants near you!</h2>
        </div>
    } else if (filteredRestaurants.length === 0) {
      restaurantsList = 
        <div data-cy="no-results-found-message" className="empty-results-container">
          <h1>No restaurants were found based on your filter <FontAwesomeIcon style={{margin: "0 1rem"}} icon={icon({name: 'face-frown'}) }  /> </h1>
          <h2>Try loosening some parameters.</h2>
        </div>
    } else {
      restaurantsList = 
        <div className="search-results-container">
          <h1>Best Restaurants Found</h1>
          <div className="restaurant-cards-container">
            {filteredRestaurants.map( (restaurant, index) => 
                <RestaurantCard key={restaurant}
                  index={index+1}
                  restaurant={restaurant}
                /> 
            )}
          </div>
        </div>
    }

    return (
      <div className="App">
        <header className="App-header">
          <FontAwesomeIcon style={{color: "#ffffff", margin: "0.5rem 1rem"}} icon={icon({name: 'bowl-rice'}) }  /> 
          <span data-cy="app-name" className="app-title">MealMapper</span>
        </header>
        <div className="search-page-container">
          <div className="search-form-container">
            <RestaurantSearchForm 
              restaurantsNames={restaurantNames}
              cuisinesNames={cuisineNames}
              onFilter={this.handleFilteredRestaurants}
            />
          </div>
          <Divider />
          {restaurantsList}
        </div>
      </div>
    );
  }
}

export default App;
