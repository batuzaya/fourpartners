package com.charpixel.fourthpartnerenergy.Models;

/**
 * Created by ashu on 13-12-2016.
 */

public class PlantListApi {
    public static class Request{

        private String search ;
        private Integer page = 1;
        private Integer count = 50;
        private String date;
        private String sortKey;
        private Integer sortOrder;

        public String getSortKey() {
            return sortKey;
        }

        public void setSortKey(String sortKey) {
            this.sortKey = sortKey;
        }

        public Integer getSortOrder() {
            return sortOrder;
        }

        public void setSortOrder(Integer sortOrder) {
            this.sortOrder = sortOrder;
        }

        public String getSearch() {
            return search;
        }

        public void setSearch(String search) {
            this.search = search;
        }

        public Integer getPage() {
            return page;
        }

        public void setPage(Integer page) {
            this.page = page;
        }

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }



    public static class Response{

    }
}
