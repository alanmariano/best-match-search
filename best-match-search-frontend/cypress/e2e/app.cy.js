describe('Tests MealMapper Filter Page', () => {
  beforeEach(() => {
    cy.visit('http://localhost:3000/')
  })

  // General Tests
  it('should have app name', () => {
    cy.get('[data-cy="app-name"]').contains('MealMapper')
  })

  it('should have five filters', () => {
    cy.get('[data-cy="form-field"]').should('have.length', 5)
  })

  it('should display welcome message', () => {
    cy.get('[data-cy="welcome-message"]').should('be.visible')
    cy.get('[data-cy="restaurant-card"]').should('not.exist')
  })

  // Restaurant Name Input Tests
  it('inputting restaurant name should load autocomplete', () => {
    cy.get('[data-cy="form-field"]#restaurantName').click().type('chowc')
    cy.get('.p-autocomplete-item').should('have.length', 1)
  })

  // Rating Tests
  it('should have working rating component', () => {

    cy.get('[data-cy="form-field"] .p-rating-item-active').should('have.length', 1)
    cy.get('[data-cy="form-field"] .p-rating-item').eq(1).click()
    cy.get('[data-cy="form-field"] .p-rating-item-active').should('have.length', 2)

  })

  // Distance Slider Tests
  it('should have working distance slider', () => {
    
    const currentValue  = 1
    const targetValue = 10
    const increment = 1
    const steps = (targetValue - currentValue) / increment
    const arrows = '{rightArrow}'.repeat(steps)
  
    cy.get('[data-cy="form-field"] .slider-distance .p-slider-handle').type(arrows)
  
    cy.get('[data-cy="form-field"] .slider-distance .p-slider-handle').should('have.attr', 'aria-valuenow', 100)

  })

  // Price Slider Tests
  it('should have working price slider', () => {
  
    const currentValue  = 10
    const targetValue = 50
    const increment = 1
    const steps = (targetValue - currentValue) / increment
    const arrows = '{rightArrow}'.repeat(steps)

    cy.get('[data-cy="form-field"] .slider-price .p-slider-handle').type(arrows)
  
    cy.get('[data-cy="form-field"] .slider-price .p-slider-handle').should('have.attr', 'aria-valuenow', 100)

  })

  // Cuisine Input Tests
  it('inputting cuisine should load autocomplete', () => {
    cy.get('[data-cy="form-field"]#cuisineName').click().type('fr')
    cy.get('.p-autocomplete-item').should('have.length', 2)
  })

  // Search Tests
  it('inputting existing restaurant name and searching should return result', () => {
    cy.get('[data-cy="form-field"]#restaurantName').click().type('deli')
    cy.get('[data-cy="submit"]').click()
    cy.get('[data-cy="restaurant-card"]').should('have.length', 1)
  })

  it('inputting non-existing restaurant name and searching should return no results', () => {
    cy.get('[data-cy="form-field"]#restaurantName').click().type('ZXZXZX')
    cy.get('[data-cy="submit"]').click()
    cy.get('[data-cy="no-results-found-message"]').should('be.visible')
    cy.get('[data-cy="restaurant-card"]').should('not.exist')
  })

  it('searching without restaurant name should show error', () => {
    cy.get('[data-cy="submit"]').click()
    cy.get('.p-toast-message-error').should('be.visible')
    cy.get('.p-toast-detail').contains('The Name Parameter should not be empty')
  })

  it('searching with full filter should return values', () => {

    cy.get('[data-cy="form-field"]#restaurantName').click().type('de')
    cy.get('.App-header').click()
    cy.get('[data-cy="form-field"] .p-rating-item').eq(1).click()
    cy.get('.App-header').click()
    cy.get('[data-cy="form-field"] .slider-distance .p-slider-handle').type('{rightArrow}'.repeat(9))
    cy.get('[data-cy="form-field"] .slider-price .p-slider-handle').type('{rightArrow}'.repeat(39))
    cy.get('[data-cy="form-field"]#cuisineName').click().type('k')
    cy.get('.App-header').click()

    cy.get('[data-cy="submit"]').click()
    cy.get('[data-cy="restaurant-card"]').should('have.length', 5)

  })
  


})