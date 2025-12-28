
@PlaceOrder
Feature: Checkout and place order
    As a user
    I want to search for a product on the landing page, add it to cart, proceed to checkout and place the order

  Background:
    Given the user is on GreenKart Landing page

  Scenario Outline: Add product to cart and proceed to checkout

    When the user searches for <product> and extracts actual name of product
    And the user adds <qty> items of the selected product to cart
    Then the user proceeds to Checkout
    And the checkout page should show the selected <product>
    And verify the user has ability to apply promo code
    And the user should be able to place the order

    Examples:
      |product | qty  |
      | Tom    | 3    |
      | Cuc    | 5    |