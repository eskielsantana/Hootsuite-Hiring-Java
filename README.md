# Hootsuite-Hiring-Java

<a href="https://opensource.org/licenses/MIT"><img src="https://img.shields.io/badge/License-MIT-blue.svg"></a>

## About

You develop a "Smart Fridge" application to manage the products inside your fridge, your app must allow you to add and remove products from there.

### Requirements

* Every day the products will have their quality decreased
* Products start with quality 10 and spoils at quality 0
* Products with type "produce" decreses the quality by 2 points each day
* Products with type "animal" decreses the quality by 3 points each day
* When a product is close to spoiling (quality <= 2), your fridge should alert the user to consume/dispose the product
* When a product quality hits 0, your fridge should alert the user to restock the product
* Assume there is a cron method which is invoked once a day, and will call whichever method is being used to reduce the quality of the items

```
├── .gitignore
├── pom.xml
├── README.md
└─── src
    ├─── main
    │   └─── java
    │       ├─── domain
    │       │   │    FridgeService.java
    │       │   └─── product
    │       │           Product.java
    │       │           Type.jav
    │       │   
    │       └─── infrastructure
    │               AlertsManager.java
    │               FridgeManager.java    
    │               Main.java
    │
    └─── test
        └─── java
            └─── domain
                   FridgeServiceTest.java
```

## Install
#### Download the repository
```sh
$ git clone https://github.com/eskielsantana/Hootsuite-Hiring-Java.git
```

#### Run tests
```sh
$ cd Hootsuite-Hiring-Java && mvn clean test
```

#### Run application
```sh
mvn exec:java
```
## Author

* **Ezequiel Santana** - [LinkedIn](https://www.linkedin.com/in/ezequiel-santana/)