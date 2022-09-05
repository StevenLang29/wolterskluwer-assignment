# Number Extractor
The project is the assignment from Wolters Kluwer.
The project can receive a file from frontend, and extract the numbers of the give file.

## Requirements
For building and running the application you need:

- [JDK 13.0.2](https://www.oracle.com/java/technologies/javase/jdk13-archive-downloads.html)
- [Maven](https://maven.apache.org)
- [Spring](https://spring.io/)

## How to run
* Clone this repository
* Make sure you are using JDK 13.0.2, and Maven
* Use Intellij as IDE
* Reload maven, click on sync button of pom.xml
* Once the program runs, you will see messages like this:</br>
```
2022-09-05 11:39:31.445  INFO 1548 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2022-09-05 11:39:31.461  INFO 1548 --- [           main] c.h.w.WolterskluwerAssignmentApplication : Started WolterskluwerAssignmentApplication in 5.107 seconds (JVM running for 6.001)
```
8080 in first message represents the port that the project is running on, and port 8080 is the default port of the project.

## Maven dependencies
* Spring-boot-starter-web
```
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

* Spring-boot-starter-test
```
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-test</artifactId>
	<scope>test</scope>
</dependency>
```

* Gson
```
<dependency>
	<groupId>com.google.code.gson</groupId>
	<artifactId>gson</artifactId>
	<version>2.9.0</version>
</dependency>
```

* Commons-io
```
<dependency>
	<groupId>commons-io</groupId>
	<artifactId>commons-io</artifactId>
	<version>2.11.0</version>
</dependency>
```

* Lombok
```
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.24</version>
</dependency>
```

## About this service
When the project is running, there is only 1 API available.<br />
The default port of the project that will be running on is 8080.<br />
The path of it is /parser.<br />
Only POST method is available.<br />
When calling it, the body of POST request needs to contain the file that will be parsed, 
the file needs to be uploaded via body of the request. Within the body request, key needs to be "file",
the value that matches the key needs to be the file.
### An example of calling the API
```
POST /parser HTTP/1.1
Host: localhost:8080
Content-Length: 225
Content-Type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW

----WebKitFormBoundary7MA4YWxkTrZu0gW
Content-Disposition: form-data; name="file"; filename="/C:/Users/steve/Advanced app developing/Test files/test.txt"
Content-Type: text/plain

(data)
----WebKitFormBoundary7MA4YWxkTrZu0gW
```

## Spring setting
The setting of Spring can be found in main -> resources -> application.properties
```
spring.servlet.multipart.max-file-size=100MB
```
The max-file-size limits the size of an uploaded file from front-end to 100MB

## Author
Haoshuang Lang(Steven)

## Contact
haoshuanglang@gmail.com
