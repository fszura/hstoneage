**Posting messages**

To post new message use url /message/post
This message should be send as http post message which in body should contains json.
Example content of json for this request:

    "username" : "posting-user-name"
    "message" : "posted-message-max-140-characters"


**Obtain wall of posted messages**

To get a wall of messages use the following url: /message/wall/{username} treated as http get request where as {username} place the user name.
As the result the api returns a list of messages which contains: 
- name of user who posted message (in this case it will be the name of the user who request a wall of messages)
- content of the message
- date when the message was added to the system

**Obtain a timeline**

To obtain a list of messages which are posted by followed users use the following url /message/timeline
As the result the api returns the list of messages which are sorted by date.

**Follow user**

To follow any user use the following url: /user/follow as the http post request which will contains as the content the following data:

    "username" : "user-who-will-follow"
    "usernameToFollow" : "user-who-will-be-followed"
    
This method requires that the user and followed user are already defined in the application. 
If the user is not defined then the exception is thrown and it is returned as http 200 status with message that user do not exist. 