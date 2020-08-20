# The Movie DB Application
![build](https://github.com/lucashenriqueos/tmdb-app/workflows/CI/badge.svg)

Android application for movies synopsis using The Movie DB APIs

#### Architecture
 - For the application architectural design was used a modular approach of [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html);
    * Some of Reference used in this project:
      * [Github - Android CleanArchitecture](https://github.com/android10/Android-CleanArchitecture-Kotlin)
      * [Medium - Clean Architecture para android (PT-BR)](https://medium.com/android-dev-br/clean-architecture-para-android-eb492513263e)
    
 - Model-View-ViewModel (MVVM) Pattern;
 - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata), for its lifecycle awareness;
 - [RxJava](https://github.com/ReactiveX/RxJava) to handle asynchronisms at service communications;
 - [Dagger](https://github.com/google/dagger) for depedency injection;
 
#### Tests
To run unit tests: ./gradlew tests and ./gradlew jacocoFullReport
  - JUnit for testing application;
  - [Mockk](https://mockk.io/) for mocking purposes
  - [Kotest](https://github.com/kotest/kotest) for assertion helpers
  - [JaCoCo](https://github.com/jacoco/jacoco) is configured to deliver a unified report for code coverage;
    * [Sample](https://github.com/esafirm/pokedroid/blob/master/gradle/jacoco.gradle)
  
There's some TODO's yet:
  - UI Tests;

#### Continuous Integration
For the Continuous Integration, was used [Github Actions](https://github.com/lucashenriqueos/tmdb-app/actions), that is triggered on every Push and Pull Request and can be checked at the above badge.

#### Follow-Up
Checkout [Project Workflow](https://github.com/lucashenriqueos/tmdb-app/projects/1) for follow-up
