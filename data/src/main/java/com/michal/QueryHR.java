package com.michal;

public class QueryHR {

    public static void main(String[] args) {
        QueryView newView = new QueryView();
        QueryControllerDAOImpl newQuery = new QueryControllerDAOImpl();

        newQuery.Batchupdates();

        newView.HRQueries();
    }
}