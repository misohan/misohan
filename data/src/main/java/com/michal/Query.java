package com.michal;

public class Query {
    String queryStatement;
    int queryNo;

    public Query(String queryStatement, int queryNo) {
        this.queryStatement = queryStatement;
        this.queryNo = queryNo;
    }

    public String getQueryStatement() {
        return queryStatement;
    }

    public void setQueryStatement(String queryStatement) {
        this.queryStatement = queryStatement;
    }

    public int getQueryNo() {
        return queryNo;
    }

    public void setQueryNo(int queryNo) {
        this.queryNo = queryNo;
    }
}
