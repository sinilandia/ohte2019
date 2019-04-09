# Software Requirements Specification: Pokemon Go raid counter aka Did I Raid That?

## Purpose

With this software, the users can keep tab on the Pokemon Go Raids and find out which raids they've attended and if they're eligible for EX Raid invitations.

> *Pokemon Go Raids are organized multiple times per day, when players gather together to fight the raid boss at the same time, at the same physical location called a Gym. Players get 1 raid pass/day. Raids show up in the game approximately 1 hour before the raid starts.*

> *EX Raids are special raids organized once / 2 weeks / gym. EX raids are only organized at special, EX Raid gyms. Players can get an EX Raid invitation when s/he has previously won a regular raid at that gym. Invitations are sent approximately one week before the EX Raid. Player can invite one friend to an EX Raid.*

> *There is no open API for Pokemon Go Raids. Therefore, users need to add this information manually.*


## Users

There are two types of users: a _regular_ user and an _admin_ user. The _admin_ user has the right to delete users and perform other admin tasks.


## Product functions

### Before login

- user can create a new user
  - username has to be at least 3 characters and unique

- user can login to the software
  - if the user doesn't exist, the software will show the user an error message

### After login

- user will see all new, upcoming raids and how many are attending

- user can add a new raid
  - raid information includes: level (1-5), date, time, gym

- user can add a new gym
  - gym information includes: gym name, EX Raid gym (Yes / No)

- user can register for upcoming raids

- user can see all the raids s/he has attended within 2 weeks
  - user can see which of those gyms are EX Raid gyms

- user can logout


## Further improvement ideas

- When creating a raid, the software will automatically add weather information from open API and the weather boosted Pokemon types
- Turning this software into a Telegram bot that can be added into a Telegram chat group
- Editing raid information afterwards
- User can unregister from a raid
- User can add friends, and friends can see who has an invitation for an EX Raid and invite friends to that EX Raid
- Friend groups based on location (this is how Pokemon Go chat groups are set up currently)
- The software can send notifications to the user and call-to-action
- The software will the current weather and boosted Pokemon types on the front page (without the need to sign in)
- A password for users
- The software will show the attended raids as a calendar instead of as a list
- User can set her/his location and see which gyms s/he has not raided yet
- User can set a target for how many raids s/he should attend
- The software will tell the user which EX Raid gyms s/he hasn't raided yet within 2 weeks
- The user can set their "homebase"
- The software will then calculate how many meters the user has walked to the raid/s in 1 day, 1 week, etc.
- The user can set walking distance targets
