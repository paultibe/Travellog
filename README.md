# My Personal Project

## Travellog: Save Where You've Been, Made Easy
![Travellog](https://github.com/paultibe/Travellog/assets/121990596/b68c06c5-cabe-442f-a392-78e38a8f47e5)
This program allows users to keep track of their travel destinations as well 
as various information about them, such as city name, country name, continent name;
food rating, cultural rating, price rating; whether they would recommend it to a friend
or not. It first prompts the user to input travel destinations and information about them
and then asks which action they would like to perform. These actions include:
- quit the application
- enter a new destination
- return the user's favourite destination based on the ratings in each category.
- return the destination with the highest rating in a particular category

Having three nationalities, I've travelled ever since I was a little kid. Travel has become
a core part of my identity and is an expression of my curiosity and desire to get outside
of my comfort zone. The diversity of human stories that I encounter while travelling is one
of my greatest sources of inspiration. This project allowed me to apply my coding
skills towards a program that I could actually use in the years to come, and that I could
share with my friends and family as well.

## User Stories

- As a user, I want to be able to add a destination to my destinations travelled.
- As a user, I want to be able to add multiple destinations to my destinations travelled. 
- As a user, I want to be able to determine my favourite destination.
- As a user, I want to be able to determine my top destination in terms of food.
- As a user, I want to be able to determine my top destination in terms of price.
- As a user, I want to be able to determine my top destination in terms of culture.
- As a user, I want to be able to determine which destinations I would recommend to a friend.
- As a user, I want to be able to access all the destinations I've travelled to in my life.
- As a user, I want to be able to save the travel destinations I have entered into the database onto a file. 
- As a user, I want to be able to load travel destinations from a file into the database.

# A Few Reflections
]- If I revisit this project in the future, I'd like to:
  - Change the functionality of the getDatabase method in DestinationDatabase to return a list of TravelDestinations
    instead of a list of Strings. This would change the functionality of the GetDatabase class, which would print out
    on screen all of the information about destinations entered in the database instead of just the city names.
  - Move the declaration of the TravelDestination field in AddDestination to the actionPerformed method with its
    instantiation to decrease coupling. 
  - Increase cohesion of the MyFrame class by converting the methods that add the different components of the GUI
    onto the frame into separate classes (ie. make new classes for addTopLabel, addCenterPanel, addVisualComponent)

