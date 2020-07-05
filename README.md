# Metrics API
This project allows users to create metrics, 
add values to a metric, and request a summary
of all the values for a given metric. 

A metric is a key value pair, with the key being a string and the value a number.

A metric summary will gather all the values for
the given metric and return the following: 
1. Min value
2. Max value
3. Mean value
4. Median value

### Running
1. Clone project from github:
    - `https://github.com/gmcolon/flexengage-metrics.git`
2. Project can be run in two different manners:
    - Import into an IDE as a Maven project & click run.
    - Using the terminal, run the following maven command in the project root:
        - `mvn springboot:run`
3. Once running, the api can be reached at:
    - `localhost:8080/api/metrics`   


### Api definition
1. The api endpoints are:
    - `POST /api/metrics` 
        - Used to create a new metric. 
        - Takes metric object as request body.
        - Only metric name should be populated in request body.
        
    - `POST /api/metrics/{metric name}`
        - Used to add a value to an already existing metric.
        - Metric should already exist. 
        - Takes metric object as request body.
        - Metric name and value should be populated in request body.
        
    - `GET /api/metrics/{metric name}/summary`
        - Used to get a metric's summary.
        - An existing metric name should be part of the path.
        
### Model definition
1. Metric model has the following fields:
    - uuid
    - name
    - value
2. Metric summary model has the following fields:
    - name
    - min
    - max
    - mean 
    - media    
    
### Complexity
1. The time & space complexity of each api call is as follows:
    - Creating a new metric:
        - Time: O(1) - constant
        - Space: O(1) - constant
        - Explanation:
            - This call only checks for validity of the parameters 
            and then saves to the database.
        
    - Adding value to metric:
        - Time: O(1) - constant
        - Space: O(1) - constant
        - Explanation:
            - This call only checks for validity of the parameters 
            and then saves to the database.
        
    - Getting a metric's summary:
        - Time: O(n) - linear
        - Space: O(n) - linear
        - Explanation:
            - This call gathers all values for the pertinent metric and
            loops through them to calculate the summary values. 
            - A list is created for each calculation of mean and median.  
        
