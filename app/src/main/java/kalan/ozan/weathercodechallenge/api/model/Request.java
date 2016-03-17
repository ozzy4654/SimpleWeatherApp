package kalan.ozan.weathercodechallenge.api.model;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class Request {

        @SerializedName("query")
        public String searchQuery;

        @SerializedName("type")
        public String queryType;


    @Override
    public String toString(){

        ArrayList<String> mRequestDetails = new ArrayList<>();
        mRequestDetails.add(searchQuery);
        mRequestDetails.add(queryType);

        return mRequestDetails.toString();
    }
    
        public String getQuery() {
            return searchQuery;
        }

        public String getQueryType() {
            return queryType;
        }

}
