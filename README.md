# RideSharing
Ride Sharing System that enables users to manage drivers, request rides, and calculate fares based on location and distance. The system is built using Java, Spring Boot, and incorporates basic RESTful API principles.
1. Clone the repository
   git clone https://github.com/your-username/RideSharing.git
   cd RideSharing/Ride
2. Build the Project
   mvn clean install
3. Run the application
   mvn spring-boot:run

**EndPoints**
1. Add New Driver:
  HTTP method: POST
  EndPoint: /addDriver
2. Request a ride for a passenger
   HTTP method: POST
   EndPoint: /requestRide

**Request Payloads Examples to test in PostMan**
1. Add Driver:
{
    "id": 1,
    "name": "John Doe",
    "location": [10, 20],
    "status": true
}
2. Request Ride
{
    "id": 1,
    "name": "Alice",
    "location": [15, 25],
    "destination": [30, 45]
}



