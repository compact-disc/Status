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
- Check ICMP or TCP Connections (UDP potentially later)

### Setup
I have the server running as a standalone Tomcat server on my web server. So I just start the Spring Boot application normally. The Gradle file is already setup to compile a jar that can be run standalone. The directories and files will automatically be generated when the application is first run, and if they are deleted it will create them again. They are placed in the root directory with the jar file.

I run the server on Ubuntu Linux and Screen using this script:
```
#!/bin/sh

echo "Starting status.cdero.com on port 8080"
cd ~/www/status.cdero.com/
screen -S status.cdero.com -d -m java -jar Status.jar
```

I then setup a reverse proxy on my Apache Web Server pointing to the local Spring Boot application on port 8080. I created a virtual host in Apache and simply enabled it:
```
<VirtualHost *:80>
ServerAdmin admin@cdero.com
ServerName status.cdero.com

ProxyPass / http://localhost:8080/
ProxyPassReverse / http://localhost:8080/

ErrorLog /home/chrisderoche/www/status.cdero.com/log/error.log
CustomLog /home/chrisderoche/www/status.cdero.com/log/access.log combined
</VirtualHost>
```

I have the subdomain [status.cdero.com](http://status.cdero.com) pointed to my data center and then Apache takes care of it from there. I try to keep as many ports closed as possible on my network to the public to using the reverse proxy has allowed me to keep port 8080 closed.

### Configuration Files
There is a directory created for all the hosts. Each host is another file with whatever name you choose. The default properties file can be used as a template for creating more hosts. Hosts files can be removed or added live when the server is running without having to restart because they are only read when it does a refresh.

Here is a copy of the default configuration file created when the program is first run. It can not be deleted from the program as it will be created again when restarted, but it can be disabled.
```
#Status default server and template for additional machines.
#If port is left blank ICMP or TCP on port 7 (Echo) will be used.
#Sat Jun 13 16:41:25 EDT 2020
port=ICMP
name=Default
enabled=true
host=127.0.0.1
description=Runs the status.cdero.com site.
os=Windows 10
```

### Known Issues -- 2/20/2020
- ~~Terraria Server showing offline after users play and leave~~
- ~~Fix ESXi hosts not being pinged when they are live. (Potentially a networking issue or the host itself)~~
- ~~Fix pfSense not being pinged when live. (Potentially a networking issue or the host itself)~~
- ~~Fix Windows 10 not being pinged when live. (Potentially a networking issue or the host itself)~~
- Unable to implement UDP Port Scanning

### TODO -- 2/20/2021
- Documentation/Javadoc
- Notification System
- Administration menu
- Make it look pretty
- Add UDP Port scanning for Game Server status and other UDP based applications.
- ~~Clean up logging~~
- ~~Better readme~~
- ~~Add default configuration file example~~
- ~~Add ping by port to check actual services instead of virtual machines~~

### Additional Notes
I would later like to move this application off site or on a Raspberry Pi that is separate from my production machines to monitor everything. This way if the production web server goes down the status site does not go down with it and services can be accurately be monitored.
