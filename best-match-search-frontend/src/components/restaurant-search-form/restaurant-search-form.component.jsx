import React, {useState, useRef } from "react";
import { useFormik } from 'formik';
import { Toast } from 'primereact/toast';
import { Button } from 'primereact/button';
import { AutoComplete } from 'primereact/autocomplete';
import { Rating } from 'primereact/rating';
import { PrimeSliderCustom } from '../prime-slider-custom/prime-slider-custom';

import './restaurant-search-form.css';

export default function RestaurantSearchForm({restaurantsNames, cuisinesNames, onFilter}) {

    const toast = useRef(null);
    const [restaurantNameSuggestions, setRestaurantNameSuggestions] = useState([]);
    const [cuisineNamesSuggestions, setCuisineNamesSuggestions] = useState([]);

    const search = (event, listToFilter, setter) => {
        setter([...listToFilter].filter((item) => item.toLowerCase().includes(event.query.toLowerCase())));
    };

    const formik = useFormik({
        initialValues: {
            name: '',
            cuisine: '',
            rating: 1,
            distance: 1,
            price: 10,
        },
        onSubmit: async (data) => {
            try {
                const restaurantResponse = await fetch(`api/restaurants/search?name=${data.name}`+
                    `&distance=${data.distance}`+
                    `&rating=${data.rating}`+
                    `&price=${data.price}`+
                    `&cuisine=${data.cuisine}`
                );
                
                const requestOk = restaurantResponse.ok;
                const restaurantBody = await restaurantResponse.json();
                if(requestOk) {                    
                    toast.current.show({ severity: 'success', summary: 'Search Completed', detail: ""}); 
                    onFilter(restaurantBody);
                } else {
                    toast.current.show({ severity: 'error', summary: 'Error', detail: restaurantBody.detail});
                }                
            } catch(err) {
                toast.current.show({ severity: 'error', summary: 'Error', detail: "An unknown internal error occurred"});
            }
        }
    });


    return (
        <div>
            <form onSubmit={formik.handleSubmit} className="search-form">

                <Toast ref={toast} />

                <div className="input-container">
                    <label htmlFor="name">Restaurant Name</label>
                    <AutoComplete 
                        data-cy="form-field"
                        id="restaurantName" 
                        name="name" 
                        appendTo="self"
                        value={formik.values.name} 
                        suggestions={restaurantNameSuggestions} 
                        completeMethod={(event) => search(event, restaurantsNames, setRestaurantNameSuggestions)} 
                        onChange={(e) => {
                            formik.setFieldValue('name', e.value);
                        }}          
                    />
                </div>
                
                <div className="input-container">
                    <label htmlFor="rating">Rating</label>
                    <Rating
                        data-cy="form-field"
                        name="rating" 
                        value={formik.values.rating} 
                        onChange={(e) => formik.setFieldValue('rating', e.value)} 
                        cancel={false} 
                    />
                </div>

                <div className="input-container">
                    <label htmlFor="distance">Distance</label>
                    <PrimeSliderCustom
                        dataCy={"form-field"}
                        field={{name: "distance", value: formik.values.distance, onChange: (value) => formik.setFieldValue('distance', value)}} 
                        config={{min: 1, max: 10}}
                    />
                </div>
                
                <div className="input-container">
                    <label htmlFor="price">Price</label>
                    <PrimeSliderCustom
                        dataCy={"form-field"}
                        field={{name: "price", value: formik.values.price, onChange: (value) => formik.setFieldValue('price', value)}} 
                        config={{min: 10, max: 50}}
                    />
                </div>
                
                <div className="input-container">
                    <label htmlFor="cuisine">Cuisine</label>
                    <AutoComplete
                        data-cy="form-field"
                        id="cuisineName" 
                        name="cuisine" 
                        appendTo="self"
                        value={formik.values.cuisine} 
                        suggestions={cuisineNamesSuggestions} 
                        completeMethod={(event) => search(event, cuisinesNames, setCuisineNamesSuggestions)} 
                        onChange={(e) => {
                            formik.setFieldValue('cuisine', e.value);
                        }}          
                    />
                </div>
                
                <Button data-cy="submit" label="Search" icon="pi pi-search" />
            </form>
        </div>        
    )
}