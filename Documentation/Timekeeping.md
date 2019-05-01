# Timekeeping

| date | time | what did I do  |
| :----:|:-----| :-----|
| 21.3. | 3    | Reading through the material and exploring possible topics. Researching how and where to get weather API, how to build a Telegram bot, etc. |
| 24.3. | 1.5  | Wrote [Software requirements specification documentation](https://github.com/sinilandia/ohte2019/blob/master/Documentation/Srs.md). |
| 30.3  | 4.5  | Initial code for keeping tabs on raids and some tests |
| 4.3.  | 1    | Trying to (again) troubleshoot my double folder problem: DidIRaidThat and Didiraidthat. The first one includes only pom.xml and the latter all the code. When course perssonel clones my repo, they get both folders. When I clone my repo, I only get one. I don't know how to get rid of the other folder. Will troubleshoot at paja later this week. | 
| 9.4.  | 1.5    | Fixed my github - netbeans - folder naming issue. |  
| 9.4.  | 1.0    | Added package structure. Changed package names to start with a small letter, per instructions. For some reason the change didn't reflect in Netbeans and once again this resulted in a naming issue. So had to change everything back and for now, live with incorrect package names. |  
| 9.4.  | 2.5    | Drew package diagram. Added checkstyle (but didn't fix all the 100+ errors that the report gave me). |  
| 13.4. | 1.0    | Added GitHub issues from settings, read theory and code for reference application [OtmTodoApp](https://github.com/mluukkai/OtmTodoApp).| 
| 14.4  | 1.0  | Trying to understand why you need new File in FileWriter constructor, e.g. FileWriter writer = new FileWriter(**new File(file)**) instead of FileWriter writer = new FileWriter(**file)**. I still don't know. |
| 14.4  | 0.5  | Refactor and create classes Gym and Raid. |
| 16.4  | 7.5  | Researching more about FileWriter constructor and error handling. Got a reply in Telegram. Now I know that you don't need a new File in FileWriter constructor. Refactoring. Added jar-file generation, DAO classes, RaidService class and Raidui class and skipped_files.xml for checkstyle. Fixed checkstyle errors. Added tests and a sequence diagram. Fixed package diagram. |
| 17.4  | 0.5  | Added a new release with a new jar file. I'd forgotten to delete the old version of my application, package Didiraidthat, from the first release jar file.|
| 20.4. | 2.5  | Checking a fellow student's code and providing feedback and actionable improvements for her code Snake Game. |
| 21.4  | 1.75 | Added content for class User, UserDao and FileUserDao. Added [Xerial's SQLiteJDBC](https://github.com/xerial/sqlite-jdbc#using-sqlitejdbc-with-maven2) and tested it but doesn't work. Error: java.sql.SQLException: No suitable driver found for jdbc:sqlite:testi.db. Then added plugin "Netbeans SQLiteJDBC" into Netbeans but it didn't work either, same error.|
| 21.4  | 0.15 | Fixed SQLiteJDBC -error. Apparently version 3.7.x doesn't work but 3.27.xx and 3.8.xx all work. |
| 22.4  | 3.00 | Added database raid.db and a table Users into it. Now it is possible to add new users with username, find users by username and find all users. **Todo for later:** check for existing users when creating a new user to avoid duplicates. Fix user input when there's spaces, now spaces cut the name.|
| 23.4  | 3.5  | Added createRaid() and getAll() raids but I'm having trouble with converting Java LocalDate and LocalTime into corresponding SQL formats. Hence, it is possible to create raids with jibberish dates and times, but fetching all raids encounters parsing error with both date and time formats. |
| 25.4. | 1.5  | Created drafts of Architecture.md and Instructions.md and continued Javadoc. |
| 26.4  | 1.0  | Fixed java-sql date problem. My colleague introduced me to a new concept: epoch date. Turns out nothing was wrong, it was just in epoch date. |
| 26.4  | 3.0  | Added some tests. Had major troubles. RaidServiceUserTest somewhat works but it's not testing RaidService, which it's supposed to do, but instead it tests User-class. |
| 29.4  | 1.0  | Add FileRaidUserDao for new features, such as user can sign up for a raid and user can find out all the raids s/he's been to. |
| 30.4  | 2.0  | Add feature to find user's raids with SQL table RaidUser. Add feature to find user's gyms. Learn about javafx from [ohja course](https://ohjelmointi-19.mooc.fi/osa-12). |
| 1.5   | 2.15 | Added javafx graphical ui for logging in and creating a new user, _uiApplication_ class. |
| 00.0  | 0.0  | N/A |
| **total** | **45.15** | | 

