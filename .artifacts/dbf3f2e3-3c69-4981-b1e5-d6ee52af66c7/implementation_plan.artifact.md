# Training Schedule App Implementation Plan

This plan outlines the steps to build an education path tracker. The app will allow users to create learning paths, add steps to those paths, and track their progress.

## User Review Required

> [!IMPORTANT]
> The project currently appears to be empty. I will be creating the standard Android project structure (app module, build scripts, etc.) from scratch.

> [!NOTE]
> I will use **Jetpack Compose** for the UI, **Room** for local storage, and **Hilt** for Dependency Injection to ensure a modern, scalable architecture.

## Proposed Changes

### Project Infrastructure
#### [NEW] [build.gradle.kts](file:///C:/Users/rcmcd/AndroidStudioProjects/TrainingSchedule/build.gradle.kts)
Root-level build script.
#### [NEW] [settings.gradle.kts](file:///C:/Users/rcmcd/AndroidStudioProjects/TrainingSchedule/settings.gradle.kts)
Project settings and module inclusion.
#### [NEW] [app/build.gradle.kts](file:///C:/Users/rcmcd/AndroidStudioProjects/TrainingSchedule/app/build.gradle.kts)
App module configuration and dependencies.

---

### Data Layer
#### [NEW] [Path.kt](file:///C:/Users/rcmcd/AndroidStudioProjects/TrainingSchedule/app/src/main/java/com/example/trainingschedule/data/model/Path.kt)
Entity representing an education path.
#### [NEW] [Step.kt](file:///C:/Users/rcmcd/AndroidStudioProjects/TrainingSchedule/app/src/main/java/com/example/trainingschedule/data/model/Step.kt)
Entity representing a step within a path.
#### [NEW] [AppDatabase.kt](file:///C:/Users/rcmcd/AndroidStudioProjects/TrainingSchedule/app/src/main/java/com/example/trainingschedule/data/local/AppDatabase.kt)
Room database definition.
#### [NEW] [TrainingDao.kt](file:///C:/Users/rcmcd/AndroidStudioProjects/TrainingSchedule/app/src/main/java/com/example/trainingschedule/data/local/TrainingDao.kt)
DAO for accessing Path and Step data.

---

### Repository & DI
#### [NEW] [TrainingRepository.kt](file:///C:/Users/rcmcd/AndroidStudioProjects/TrainingSchedule/app/src/main/java/com/example/trainingschedule/data/repository/TrainingRepository.kt)
Repository to abstract data sources.
#### [NEW] [DatabaseModule.kt](file:///C:/Users/rcmcd/AndroidStudioProjects/TrainingSchedule/app/src/main/java/com/example/trainingschedule/di/DatabaseModule.kt)
Hilt module for providing database and repository instances.
#### [NEW] [TrainingApplication.kt](file:///C:/Users/rcmcd/AndroidStudioProjects/TrainingSchedule/app/src/main/java/com/example/trainingschedule/TrainingApplication.kt)
Application class for Hilt initialization.

---

### UI Layer
#### [NEW] [PathListViewModel.kt](file:///C:/Users/rcmcd/AndroidStudioProjects/TrainingSchedule/app/src/main/java/com/example/trainingschedule/ui/pathlist/PathListViewModel.kt)
ViewModel for the main list screen.
#### [NEW] [PathDetailViewModel.kt](file:///C:/Users/rcmcd/AndroidStudioProjects/TrainingSchedule/app/src/main/java/com/example/trainingschedule/ui/pathdetail/PathDetailViewModel.kt)
ViewModel for the detail screen.
#### [NEW] [PathListScreen.kt](file:///C:/Users/rcmcd/AndroidStudioProjects/TrainingSchedule/app/src/main/java/com/example/trainingschedule/ui/pathlist/PathListScreen.kt)
Compose UI for displaying all paths.
#### [NEW] [PathDetailScreen.kt](file:///C:/Users/rcmcd/AndroidStudioProjects/TrainingSchedule/app/src/main/java/com/example/trainingschedule/ui/pathdetail/PathDetailScreen.kt)
Compose UI for displaying and editing steps.
#### [NEW] [MainActivity.kt](file:///C:/Users/rcmcd/AndroidStudioProjects/TrainingSchedule/app/src/main/java/com/example/trainingschedule/MainActivity.kt)
Entry point with Navigation setup.

## Verification Plan

### Automated Tests
- Unit tests for `TrainingRepository`.
- Room DAO tests for data persistence logic.

### Manual Verification
- Create a new Path.
- Add Steps to the Path.
- Toggle completion of Steps.
- Verify that a Path is marked as complete only when all Steps are finished.
