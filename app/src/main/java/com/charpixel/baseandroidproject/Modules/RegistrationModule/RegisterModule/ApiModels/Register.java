package com.charpixel.baseandroidproject.Modules.RegistrationModule.RegisterModule.ApiModels;

import javax.inject.Inject;

/**
 * Created by ashu on 25-11-2016.
 */

public class Register {
    public static class Request{
        private String email;
        private String password;
        private String fullName;

        public String getConfirm_password() {
            return confirm_password;
        }

        public void setConfirm_password(String confirm_password) {
            this.confirm_password = confirm_password;
        }

        private String confirm_password;


        @Inject
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

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }



    }

    public static class Response{

        private String token;
        private String id;
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
            return id;
        }

        /**
         * @param id The _id
         */
        public void setId(String id) {
            this.id = id;
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
