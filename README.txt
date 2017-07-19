Nico Tandyo, TCSS 445 - Final Project
If you encounter no internet connection problem, if you are using java go to your project, select properties > java build path > add external jar > and add this file "mysql-connector-java-5.1.40-bin.jar".
default owner account: username = testowner, password = test
default customer account: username = testcustomer, password = test
you can also make a new account with specific role (owner or customer) if you want.
owner can only add new car, and then they can see the car description and vin number.
customer can make a reservation to a car, cancel reservation. However, to make a reservation to a car, you have to enter existing VIN (vehicle identification number). You have to select a date. You can also look at the list of the car, or brand of the car. As a customer you cannot add a new car.
You can also leave ratings for a reservation but you have to remember the reservationID (reservationID must exist)!

 