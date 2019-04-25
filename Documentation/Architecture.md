# Architecture

## Structure

The application has three layers. The packages are as follows:

<img src="https://raw.githubusercontent.com/sinilandia/ohte2019/master/Documentation/kuvat/Packagediagram.png" width="400">

Package _ui_ has a graphical interface with JavaFX, _domain_ application logic and _dao_ has the code for relaying information to and from database _raid.db_.

## Interface

Interface has three windows:
- login
- creating a new raid
- list view of ongoing raids

## Application logic

The logic behind the application consists of classes [User](https://github.com/sinilandia/ohte2019/blob/master/Didiraidthat/src/main/java/domain/User.java), [Gym](https://github.com/sinilandia/ohte2019/blob/master/Didiraidthat/src/main/java/domain/Gym.java) and [Raid](https://github.com/sinilandia/ohte2019/blob/master/Didiraidthat/src/main/java/domain/Raid.java). Here is a sequence diagram of how to create a new EX Gym:

<img src="https://github.com/sinilandia/ohte2019/blob/master/Documentation/kuvat/Create%20a%20new%20EX%20gym.png" width="800">

To execute needed tasks, [RaidService](https://github.com/sinilandia/ohte2019/blob/master/Didiraidthat/src/main/java/domain/RaidService.java) object will be responsible for this. This is the class that links the ui with the other objects and data. Examples of functions are:
- boolean login(String username)
- User addNewUser()
- Gym getGym(String gymName)
- String getAllGyms() 
- boolean createRaid()

_RaidService_ uses package _dao_ to access interfaces _RaidDao_, _UserDao_ and _GymDao_.  [Injection?](https://en.wikipedia.org/wiki/Dependency_injection)

## Data and keeping it

[Data Access Object](https://en.wikipedia.org/wiki/Data_access_object) - model is used to access and save information into the file _raid.db_. 


### File

raid.db
What is [config.properties](https://github.com/mluukkai/OtmTodoApp/blob/master/config.properties)?

The format in which the data is saved is:
example
example
example



### Main functions

#### User login

Login window > login button

#### Creating a new user

Create new user button > type in name & create button

#### Creating a new gym 

process

#### Creating a new raid

process

#### Other functions

what else?



## Vulnerabilities in the application

### User interface


### DAO classes

Lots of non-DRY code.
