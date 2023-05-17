# emailapi

Project aims to implement rest api endpoints for email resource

### Project Status
Following table lists implemented end-points.

| S/N |                   http method/ url                    |                                         Comment                                         |
|:---:|:-----------------------------------------------------:|:---------------------------------------------------------------------------------------:|
|  1  |    get / http://localhost:8090/emailapi/v1/emails     | Need to have a created email to access <br/> username should pass as a header parameter |
|  2  | post / http://localhost:8090/emailapi/v1/emails/{id} |                       username should pass as a header parameter                        |
|  3  |               get / http://localhost:8090/emailapi/v1/emails?pageNumber=1&pageSize=2&status=DRAFT               |                       Need to have sufficient data to test <br/> username should pass as a header parameter                       |

## Technical Debt
1. Add data-jpa starter and get the email list with PagingAndSortingRepository
2. Need to complete email sending and update recipient endpoints
3. Authorisation layer for the requests need to be placed before request reach Email APIs. Header username represent authorisation process. Ex : passing gwt token, loading principals, role and permission
4. Cors methods add to support services to serve client in microservice environment. Further, it needs to add cors filter and add response header("Access-Control-Allow-Origin", "*") to each requests

   