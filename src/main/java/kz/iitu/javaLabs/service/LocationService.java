package kz.iitu.javaLabs.service;

import kz.iitu.javaLabs.model.User;
import kz.iitu.javaLabs.model.UserLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

public interface LocationService {
    UserLocation trackLocation(User user, double latitude, double longitude);
//    List<UserLocation> getLocations(User user);
//    UserLocation getLatestLocation(User user);

}