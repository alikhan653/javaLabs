package kz.iitu.javaLabs.dto;

public class UserProfileResponse {
    private final String username;
    private final String firstName;
    private final String lastName;

    public UserProfileResponse(String username, String firstName, String lastName) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
    }

}