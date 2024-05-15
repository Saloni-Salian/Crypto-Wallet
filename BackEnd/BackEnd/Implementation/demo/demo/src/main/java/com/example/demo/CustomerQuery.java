package com.example.demo;

// note: this class is not being utilised fully and will be implemented in the second version of our prototype (post-presentation).
// CustomerQuery class is used to represent a customer's query to the customer support.
public class CustomerQuery {

    private int id;
    private int customerId;
    private String query;
    private QueryStatus status;

    // constructor for CustomerQuery class.
    public CustomerQuery(int id, int customerId, String query)
    {
        this.id = id;
        this.customerId = customerId;
        this.query = query;
        this.status = QueryStatus.Open;
    }

    // returns the id of the query.
    public int getId() {
        return this.id;
    }

    // sets the id of the query.
    public void setId(int id) {
        this.id = id;
    }

    // returns the id of the customer who raised the query.
    public int getCustomerId() {
        return this.customerId;
    }

    // sets the id of the customer who raised the query.
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    // returns the query raised by the customer.
    public String getQuery() {
        return this.query;
    }

    // sets the query raised by the customer.
    public void setQuery(String query) {
        this.query = query;
    }

    // returns the current status of the query.
    public QueryStatus getStatus() {
        return this.status;
    }

    // sets the status of the query.
    public void setStatus(QueryStatus status) {
        this.status = status;
    }
}