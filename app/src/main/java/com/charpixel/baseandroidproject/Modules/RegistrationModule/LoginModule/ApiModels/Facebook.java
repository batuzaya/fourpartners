package com.charpixel.baseandroidproject.Modules.RegistrationModule.LoginModule.ApiModels;

/**
 * Created by ashu on 24-07-2016.
 */
public class Facebook {
    public static class Request {

        private String facebookId;
        private String facebookAccessToken;
        private String deviceType = "ANDROID";
        private String deviceToken;
        private String email;

        private String countryCode;
        private String firstName;
        private String lastName;
        private String appVersion;

        private String profilePic;

        public String getProfilePic() {
            return profilePic;
        }

        public void setProfilePic(String profilePic) {
            this.profilePic = profilePic;
        }

        public String getFacebookId() {
            return facebookId;
        }

        public void setFacebookId(String facebookId) {
            this.facebookId = facebookId;
        }

        public String getFacebookAccessToken() {
            return facebookAccessToken;
        }

        public void setFacebookAccessToken(String facebookAccessToken) {
            this.facebookAccessToken = facebookAccessToken;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getAppVersion() {
            return appVersion;
        }

        public void setAppVersion(String appVersion) {
            this.appVersion = appVersion;
        }

        public Request(){

        }



        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getCountryCode() {
            return countryCode;
        }

        public void setCountryCode(String countryCode) {
            this.countryCode = countryCode;
        }


        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getFbId() {
            return facebookId;
        }

        public void setFbId(String facebookId) {
            this.facebookId = facebookId;
        }

        public String getFbToken() {
            return facebookAccessToken;
        }

        public void setFbToken(String facebookAccessToken) {
            this.facebookAccessToken = facebookAccessToken;
        }

        public String getDeviceType() {
            return deviceType;
        }

        public void setDeviceType(String deviceType) {
            this.deviceType = deviceType;
        }

        public String getDeviceToken() {
            return deviceToken;
        }

        public void setDeviceToken(String deviceToken) {
            this.deviceToken = deviceToken;
        }
    }

    public static class Response {

        private String accessToken;

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }
    }
}
