Feature: Product search
    As a user
    I want to search for a product on the landing page and verify it exists on the offers page

    Background:
        Given the user is on GreenKart Landing page

    @Search
    Scenario Outline: Search Experience for product search in both home and Offers page

        When the user searches for <product> and extracts actual name of product
        Then the user searches for <product> in Offers page to check if product exists
        And the user validates product name in offers matches with Landing Page

        Examples:
        |product |
        | Tom |
        | Cuc |
