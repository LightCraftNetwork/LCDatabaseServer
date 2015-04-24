package com.arrayprolc.lunadb.logger;

import java.util.ArrayList;

public class QueryManager {

    private ArrayList<LoggedQuery> queries = new ArrayList<LoggedQuery>();

    private static QueryManager instance;

    private static QueryManager getInstance() {
        if (instance == null) {
            instance = new QueryManager();
        }
        return instance;
    }

    private QueryManager() {
    }

    public static void logQuery(LoggedQuery query) {
        getInstance().queries.add(query);
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<LoggedQuery> getQueries() {
        return (ArrayList<LoggedQuery>) getInstance().queries.clone();
    }

}
