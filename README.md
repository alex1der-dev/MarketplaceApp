# MarketplaceApp 
## Task : 
Create a marketplace application with the help of Java language 
## Requirements: 
- System has products that can be sold. Each product contains:
  1. Id
  2. Name
  3. Price
- System has users that can buy products. Each user has:
  1. Id
  2. First name
  3. Last name
  4. Amount of money
- System starting without users and products, add next operations to menu:
1. Display list of all users
2. Display list of all products
3. User should be able to buy product, to do this operation we should enter: Id of user who want to buy productId of 
product which user want to buy
    - If user doesn't have enough money to buy product, throw exception
    - If user successfully bought the product display message about successful purchase
    - When user is buying product, his amount of money decreases by product price
    - After successful purchase, information about user and his products has to be stored in collection best 
suited for that purpose
4. Display list of user products by user id (If user didn't buy anything yet, don't show anything)
5. Display list of users that bought product by product id (If nobody bought this product yet, don't show anything) 
6. Ability to add new users to the system (with autogenerated id, not null fields validation and check for numbers in 
amount of money field)
7. Ability to add new products to the system (with autogenerated id, not null fields validation and check for numbers 
in price field)
8. Ability to delete products and users 
    - If product deleted, it have to disappear from all users purchases
- If exception is thrown, don't crash application and show menu again, display error message to understand reason of error