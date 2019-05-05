# Architecture

## Structure

The application has three layers. The packages are as follows:

<img src="https://raw.githubusercontent.com/sinilandia/ohte2019/master/Documentation/kuvat/Packagediagram.png" width="400">

Package _ui_ has a graphical interface with JavaFX, _domain_ application logic and _dao_ has the code for relaying information to and from database _raid.db_.

## Interface

The interface has four windows:
- login window: log in with username or create a new user
- after logging in: the user will see upcoming raids and is able to sign up for the raids
- after logging in: the user can create new raids
- after logging in: the user can view a list of his/her raids

The interface has been created with JavaFX. Each button click will create a new view as a Scene object. 

## Application logic

The logic behind the application consists of classes [User](https://github.com/sinilandia/ohte2019/blob/master/Didiraidthat/src/main/java/domain/User.java), [Gym](https://github.com/sinilandia/ohte2019/blob/master/Didiraidthat/src/main/java/domain/Gym.java) and [Raid](https://github.com/sinilandia/ohte2019/blob/master/Didiraidthat/src/main/java/domain/Raid.java). 

Here is a sequence diagram of how to create a new EX Gym:

<img src="https://github.com/sinilandia/ohte2019/blob/master/Documentation/kuvat/Create%20a%20new%20EX%20gym.png" width="800">

To execute needed tasks, [RaidService](https://github.com/sinilandia/ohte2019/blob/master/Didiraidthat/src/main/java/domain/RaidService.java) object will be responsible for this. This is the class that links the ui with the other objects and data. Examples of functions are:
- boolean login(String username)
- User addNewUser()
- boolean createRaid()
- List<Raid> findUpcomingRaids()

_RaidService_ uses package _dao_ to access interfaces _RaidDao_, _UserDao_, _GymDao_ and _RaidUserDao_. 


## Data and keeping it

[Data Access Object](https://en.wikipedia.org/wiki/Data_access_object) - model is used to access and save information into the file _raid.db_. The program uses _sqlite_ to store SQL data.

_UserDao_ is an independent dao-object.

_RaidDao_ references _GymDao_ since each Raid happens at one specific Gym.

_RaidUserDao_ keeps tabs on user's raids.


### Main functions

#### User login

Login window > login button

#### Creating a new user

Create new user button > type in name & create button

#### Creating a new gym 

process

#### Creating a new raid

process



## Vulnerabilities in the application and ideas for the future
- deleting old raids and only keeping raids from past 2 weeks
- if keeping all time raid data, then drawing graphs of user's raid activity amount of raids vs. months
- streamlining DAO-classes
- refactoring the ui
- showing user's EX Gyms, now the methods exist but this is not utilized in the ui
