# The Movie DB Application
![build](https://github.com/lucashenriqueos/tmdb-app/workflows/CI/badge.svg)

Android application for movies synopsis using The Movie DB APIs

#### Architecture
 - For the application architectural design was used a modular approach of Clean Architecture;
    * Some of referrences used in this project:
      * [Github - Android CleanArchitecture](https://github.com/android10/Android-CleanArchitecture-Kotlin)
      * [Medium - Clean Architecture para android (PT-BR)](https://medium.com/android-dev-br/clean-architecture-para-android-eb492513263e)
    
 - Model-View-ViewModel (MVVM) Pattern;
 - LiveData, for its lifecycle awareness;
 - RxJava to handle asynchronisms at service communications;
 
#### Tests
To run unit tests: ./gradlew tests
  - JUnit for testing application;
  - [Mockk](https://mockk.io/) for mocking purposes
  - [Kotest](https://github.com/kotest/kotest) for assertion helpers
  
There's some TODO's yet:
  - UI Tests;
  - Test reports, probably [JaCoCo](https://github.com/jacoco/jacoco) will be used;
  
#### Continuous Integration
For the Continuous Integration, was used Github Actions, that is triggered on every Push and Pull Request and can be checked at the above badge.

#### Follow-Up
Checkout project Workflow for follow-up
