# Status
An application to display the status of multiple services/machines on a network.

Created using the [Spring Framework](https://spring.io/) and Java.

Live version located at [status.cdero.com](http://status.cdero.com/)

### Features
- Built in web server from Spring
- Automatically pings hosts in the /hosts/ folder
- Add and remove hosts from the folder without restarting the application
- Runs using a Tomcat server
- Can be reached using a reverse proxy on Apache

### Setup
I have the server running as a standalone Tomcat server on my web server. So I just start the Spring Boot application normally. The Gradle file is already setup to compile a jar that can be run standalone. The directories and files will automatically be generated when the application is first run, and if they are deleted it will create them again. They are placed in the root directory with the jar file.

I run the server on Ubuntu Linux and Screen using this script:
```
#!/bin/sh

echo "Starting status.cdero.com on port 8080"
cd ~/www/status.cdero.com/public_html/
screen -S status.cdero.com -d -m java -jar Status.jar
```

I then setup a reverse proxy on my Apache Web Server pointing to the local Spring Boot application on port 8080. I created a virtual host in Apache and simply enabled it:
```
<VirtualHost *:80>
ServerAdmin admin@cdero.com
ServerName status.cdero.com

ProxyPass / http://localhost:8080/
ProxyPassReverse / http://localhost:8080/

ErrorLog ${APACHE_LOG_DIR}/error.log
CustomLog ${APACHE_LOG_DIR}/access.log combined
</VirtualHost>
```

I have the subdomain [status.cdero.com](http://status.cdero.com) pointed to my data center and then Apache takes care of it from there. I try to keep as many ports closed as possible on my network to the public to using the reverse proxy has allowed me to keep port 8080 closed.


### TODO
- Fix ESXi hosts not being pinged when they are live. (Potentially a networking issue or the host itself)
- Fix pfSense not being pinged when live. (Potentially a networking issue or the host itself)
- Fix Windows 10 not being pinged when live. (Potentially a networking issue or the host itself)
- ~~Better readme~~
- Documentation/Javadoc
- Notification System
- Administration menu
- Anything else?
