# PeopleCrud - Peer instruction - Session Beans  
Simple distributed dockerized CRUD built over EJB, JSF, JDBC, POSTGRES, SWING.


#Architecture Overview

**Module PeopleCrudCore**

This ejb module wraps business logic into Stateless Bean PersonService and PersonDao. 

PersonService interface is embedded in PeopleCrudLibrary module of which this module depends.

PeopleCrudCore was deployed on a glassfish docker image that only has the port 3700 exposed. 

**Module PeopleCrudLibrary**

This module is a library containing: 
  
  -Person entity;
  
  -PersonService interface;
  
  -The ServiceLocator class provides a method for retrieve remote objects from glassfish server.
  
  ServiceLocator contains host and port properties that can be setted.
    
  The host default value is `pcCore` that represents the NETWORK ADDRESS of the PeopleCrudCore docker container
    
  `This will be better explained further.`
    
  If you want to deploy PeopleCrudCore on your own real machine, you must change the host to `localhost`
    
All the others modules depends on this module.

**Module PeopleCrud**

This module is a jsf web client. It uses the ServiceLocator to retrieve the PersonService object from PeopleCrudCore.

PeopleCrud was deployed on a glassfish docker image that only has the port 8080 exposed.

**Module PeopleCrudClient**

This module is a Java SE Swing Client. It uses the ServiceLocator to retrieve the PersonService object from PeopleCrudCore. 

It has the PersonController class that contains the `buildServiceLocator(String host, String port)` method.

You will see this 
  
      private PersonService buildPersonService(){
          return buildServiceLocator("0.0.0.0", "370").lookup("java:global/PeopleCrudCore/PersonServiceImpl!com.vmvini.peoplecrudlibrary.PersonService", PersonService.class);
      }  

The code above shows the host `0.0.0.0` and the port `370`.
The PeopleCrudCore will be running in a glassfish docker container and its public port will be setted to 370 at the `docker run` command.

As the PeopleCrudClient will be deployed on your real machine, the above host parameter its setted to 0.0.0.0 because your real machine sees the docker container as 0.0.0.0. **only if docker is running on linux**



#Steps to deploy 

**1- clone this repository**

    git clone https://github.com/vmvini/peoplecrud_dac.git

**2- Inside every module folder, do: `mvn clean install **
    
    follow the order below:
      1- PeopleCrudLibrary
      2- PeopleCrudCore
      3- PeopleCrud
      4- PeopleCrudClient

**3 - Inside the postgres folder, do:**

    sudo docker build -t postgres-peoplecrud . 
    sudo docker run -d --name peopleCrudDB postgres-peoplecrud
  
**4 - Copy the following libs to the PeopleCrudCore folder**

    a) PeopleCrudLibrary-1.0-SNAPSHOT.jar 
    (You can find it on PeopleCrudLibrary/target folder)
    
    b) postgresql-9.1-901-1.jdbc4.jar
    (Download the postgres driver from the web) 
    
**5- Inside the PeopleCrudCore folder, do:**

    sudo docker build -t glassfish-peoplecrud .
    sudo docker run -d -p 370:3700 --link peopleCrudDB:postgres --name peopleCrudCore glassfish-peoplecrud 
    
The **--link** flag is telling to glassfish-peoplecrud container that `postgres` is a alias to the peoplecrudDB container (name of postgres-peoplecrud container image)

Thus, `postgres` will be interpreted as the network address of the postgres docker container.


**6- Inside the PeopleCrud folder, do:**

    sudo docker build -t  glassfish-peoplecrudweb .
    sudo docker run -d -p 80:8080 --link peopleCrudCore:pcCore glassfish-peoplecrudweb
  
Do you remember the `pcCore` value quoted at the beginning of this readme? The line of code above tells docker to interpret `pcCore` as the network address of the peopleCrudCore container.

**7- WEB Client test:**
    open  `http://0.0.0.0/PeopleCrud/`  on the browser. 
    
**8- Desktop Client test:**
    Through your IDE, execute the MainFrame class.
