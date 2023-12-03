package kz.iitu.javaLabs.service.impl;

import kz.iitu.javaLabs.model.User;
import kz.iitu.javaLabs.model.UserLocation;
import kz.iitu.javaLabs.repository.UserLocationRepository;
import kz.iitu.javaLabs.service.LocationService;
import kz.iitu.javaLabs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    private final UserLocationRepository locationRepository;
    private final UserService userService;

    @Autowired
    public LocationServiceImpl(UserService userService, UserLocationRepository locationRepository) {
        this.userService = userService;
        this.locationRepository = locationRepository;
    }

    @Override
    public UserLocation trackLocation(User user, double latitude, double longitude) {
        UserLocation location = new UserLocation();
        location.setUser(user);
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        location.setTrackedAt(new Date());

        return locationRepository.save(location);
    }

//    @Override
//    public List<UserLocation> getLocations(User user) {
//        return locationRepository.findByUser(user);
//    }
//
//    @Override
//    public UserLocation getLatestLocation(User user) {
//        return locationRepository.findTopByUserOrderByTimestampDesc(user);
//    }
//
//    @Scheduled(fixedRate = 300000) // 5 minutes = 300,000 milliseconds
//    public void sendLocationPeriodically() {
//        List<User> users = getUsersToTrack();
//
//        for (User user : users) {
//            // Simulate getting the user's current location (replace with actual logic)
//            double latitude = getRandomLatitude();
//            double longitude = getRandomLongitude();
//
//            // Track the user's location
//            trackLocation(user, latitude, longitude);
//
//            // Implement logic to send the location (e.g., through messaging or API calls)
//            sendLocationToExternalService(user, latitude, longitude);
//        }
//    }
//
//    private List<User> getUsersToTrack() {
//        // Implement logic to get the list of users you want to track
//        // For example, you can query the database for users with a specific role
//        // or those who have opted into location tracking
//        return userService.findUsersToTrack();
//    }
//
//    private double getRandomLatitude() {
//        // Implement logic to generate random latitude values
//        return Math.random() * 180 - 90;
//    }
//
//    private double getRandomLongitude() {
//        // Implement logic to generate random longitude values
//        return Math.random() * 360 - 180;
//    }
//
//    private void sendLocationToExternalService(User user, double latitude, double longitude) {
//        // Implement logic to send the location to an external service
//        // This could be sending an HTTP request to an API or using a messaging system
//        // Replace the following line with your actual logic
//        System.out.println("Sending location for user " + user.getUsername() +
//                ": Latitude " + latitude + ", Longitude " + longitude);
//    }
}