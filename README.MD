# Introduction
This  project shows how to enable session replication with a small JEE Webapp.
It is a simple counter incremented by each GET request. This counter is hold inside the 
HTTP session and will be shared by all nodes inside your cluster.
## Usage
### Build the project with Maven.
```
mvn clean package
```
### Deploy to Wildfly
Copy the war generated (inside target directory)  to your Wildfly nodes deployment directories
or use the jboss-cli.

### Test it
Point your browser to the application port and do several requests.
Then update the URL to use another node  and the counter will be incremented accordingly
You may use *curl* from your terminal rather than a browser