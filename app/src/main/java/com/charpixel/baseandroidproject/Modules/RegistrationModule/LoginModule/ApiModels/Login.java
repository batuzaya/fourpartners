package com.charpixel.baseandroidproject.Modules.RegistrationModule.LoginModule.ApiModels;

/**
 * Created by ashu on 25-11-2016.
 */

public class Login {
    public static class Request{
        private String email;
        private String password;
        private String deviceToken;
        private String deviceType;


        public Request(){

        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getDeviceToken() {
            return deviceToken;
        }

        public void setDeviceToken(String deviceToken) {
            this.deviceToken = deviceToken;
        }

        public String getDeviceType() {
            return deviceType;
        }

        public void setDeviceType(String deviceType) {
            this.deviceType = deviceType;
        }
    }

    public static class Response{

        private String token;
        private String _id;
        private String name;

        /**
         * @return The token
         */
        public String getToken() {
            return token;
        }

        /**
         * @param token The token
         */
        public void setToken(String token) {
            this.token = token;
        }

        /**
         * @return The id
         */
        public String getId() {
            return _id;
        }

        /**
         * @param id The _id
         */
        public void setId(String id) {
            this._id = id;
        }

        /**
         * @return The name
         */
        public String getName() {
            return name;
        }

        /**
         * @param name The name
         */
        public void setName(String name) {
            this.name = name;
        }
    }
}
