# Playground Android

This repo is what it actually says. Its a personal app that I use to try out native Android features and experiment with them.

Everytime a new feature is released or I wanted to experiment with something, I always lost motivation by the time I had set up the project, architecture and integrate other basic libraries to make the app run in an accpetable state.

Day Mode                   |  Night Mode
:-------------------------:|:-------------------------:
![](https://github.com/suchoX/PlaygroundAndroid/blob/master/screens/day_mode.gif)  |  ![](https://github.com/suchoX/PlaygroundAndroid/blob/master/screens/night_mode.gif)

## Features as of now

- Well defined Clean architecture with MVVM via Jetpack Components
- Hilt for Dependency injection
- API calls wrapped with Kotlin Flows to continually fetch and update the Recycler View
- Syncing MotionLayout animation with Recyclerview's Item Animation
- Foreground task with Progress notification using WorkManager
- Room with Coroutines to store and fetch Data

## Libraries Used

### Architecture
- Clean Architecture
- Single Activity with AndroidX Navigation
- Hilt for Dependency Injection

### Design
- Dark Mode with MaterialComponents.DayNight
- ConstraintLayout
- Downloadable Fonts

### Network
- Retrofit
- Gson

### Background Execution
- Suspend Functions
- Kotlin Coroutines
- Kotlin Flows
- WorkManager

### Database
- Room with Coroutine

### Animation
- Lottie
- MotionLayout
- LayoutAnimation


This app will evolve with time, but will never be a specific feature driven. It will be a mixture of features that makes no sense with together (Imagine Notes app with Cat pictures and Chuck Norris Jokes) but will showcase the Android features
