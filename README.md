# ProficoTask

This repository was used to solve an Android hiring task made by the Profico company. 
The task was to create an app which will allow users to select their favorite video game genres. After selecting favorite genres,
the user can browse all video games within the selected genres. Genre selection can be changed inside the settings.

### Features
- **Sign in**
  - A user can sign into the app by using his Google account
- **Genre selection**
  - Once the user has signed in, he can select his favorite video game genres
- **Games**
  - A list of games corresponding to the selected genres is displayed
  - By tapping a game from the list, a window opens containing details of the selected game
- **Settings**
  - Inside the settings, the user can change his genre selection and log out of his account
  
### Technologies
- Retrofit - used for fetching data from the provided API
- Firebase - used to implement user sign in with a Google account
- Coil - used to display images
- Room database - used to store selected genres locally
- Paging - used to display a list of video games
