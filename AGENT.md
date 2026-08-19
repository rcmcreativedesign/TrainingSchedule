# Original Prompt
I want to build an app to track education `Paths`. 

For example: 

I want to learn:
• Python
• Project Management


The app will display the paths the user is pursueing. The user can select a path to drill down to the details.


In the details section the user can specify different `Steps` along the track.

For example: 

Steps:
• Find training materials
• Practice
• Take exam


Steps should contain a title, description, due date, and completion status. The user should be able to check a box to complete the Step. Once all the steps are complete, the path is considered compete.

The app should be an Android app built with Gradle and Kotlin. It should use **Jetpack Compose** for the UI, **Room** for local storage, and **Hilt** for Dependency Injection to ensure a modern, scalable architecture.


# Implementation Plan: Education Path Tracker

## 🎯 Goal
Build a native Android application using Kotlin, Jetpack Compose, Room, and Hilt to track educational paths, with each path containing sequential, trackable steps.

## 🧩 Technology Stack
*   **Language:** Kotlin
*   **UI:** Jetpack Compose
*   **Architecture:** MVVM (Model-View-ViewModel)
*   **Local Persistence:** Room Database
*   **Dependency Injection:** Hilt
*   **Platform:** Android (Gradle build system)

## 🗺️ Phase 1: Project Setup and Data Modeling (The Foundation)
The goal of this phase is to set up the project structure and define the core data entities that Room will manage.

### 1.1 Project Initialization
*   Create a new Android project using the recommended template (Kotlin/Compose).
*   Configure `build.gradle` files (project and module level) to include necessary dependencies:
    *   AndroidX Core KTX
    *   Jetpack Compose dependencies (ViewModel, Activity Compose)
    *   Room Persistence Library
    *   Hilt dependencies (Compiler, Android components)
    *   Coroutines/Flow for asynchronous operations.

### 1.2 Data Model Definition (Room Entities)
We need three primary entities: `Path`, `Step`, and potentially a relationship linking them.

*   **`Path` Entity:**
    *   `id`: Primary Key (Int/Long)
    *   `name`: String (e.g., "Python", "Project Management")
    *   `description`: String (Optional)
    *   `creationDate`: Long (Timestamp)
*   **`Step` Entity:**
    *   `id`: Primary Key (Int/Long)
    *   `pathId`: Foreign Key referencing `Path.id`
    *   `title`: String (e.g., "Find training materials")
    *   `description`: String (Detailed description)
    *   `dueDate`: Long (Timestamp, optional)
    *   `isCompleted`: Boolean (Default: `false`)

### 1.3 Data Access Layer (DAO & Database)
*   **`AppDatabase`:** Define the main Room database class, annotating it with `@Database(entities = [Path::class, Step::class], version = 1)`.
*   **`PathDao` & `StepDao`:** Create Data Access Objects (DAOs) containing suspend functions for CRUD operations:
    *   Getting all paths.
    *   Getting all steps for a specific `pathId`.
    *   Inserting new Paths and Steps.
    *   Updating step completion status (`updateStepCompletion(stepId: Long, isCompleted: Boolean)`).

## 📱 Phase 2: Architecture Implementation (The Brains)
This phase focuses on using the Repository and ViewModel pattern to abstract data logic away from the UI.

### 2.1 Repository Layer
*   **`EducationRepository`:** Implement a repository that mediates between the ViewModel and the DAO. This keeps the ViewModels clean.
    *   Methods should wrap the DAO calls (e.g., `getAllPaths(): Flow<List<Path>>`, `getStepsForPath(pathId: Long): Flow<List<Step>>`).

### 2.2 ViewModel Layer
*   **`PathListViewModel`:** Responsible for managing and exposing the list of Paths to the UI.
    *   It will observe the `Flow` from the repository and expose it to the Compose screen.
*   **`PathDetailViewModel`:** Responsible for managing the state and logic for a specific Path and its Steps.
    *   It must expose the list of steps and a function to mark a step as complete.

### 2.3 Dependency Injection Setup (Hilt)
*   Use Hilt annotations (`@AndroidEntryPoint`, `@Inject`) to inject the `AppDatabase` and the `EducationRepository` into the ViewModels, ensuring clean, testable dependencies.

## 🎨 Phase 3: UI Implementation (The View)
This phase builds the user interface using Jetpack Compose, following the MVVM pattern by observing the ViewModels.

### 3.1 Main Screen: Path List View
*   **Composable:** `PathListView`
*   **Functionality:** Displays a scrollable list of all `Path` names.
*   **Interaction:** Tapping a path navigates the user to the Path Detail Screen.

### 3.2 Detail Screen: Path Detail View
*   **Composable:** `PathDetailView(viewModel: PathDetailViewModel)`
*   **Functionality:**
    1.  Displays the Path title and general information.
    2.  Displays the list of `Step` items.
    3.  Each step item must be interactive:
        *   Show Checkbox / Status indicator.
        *   Display Title, Description, and Due Date.
        *   On click, call `viewModel.onStepToggled(stepId)`.
    4.  **Completion Logic:** Add logic to check if *all* steps in the list are marked `true`. If so, display a "Path Complete!" banner.

### 3.3 Navigation
*   Implement Compose Navigation (`NavController`) to manage movement between the list view and the detail view.

## ✨ Phase 4: Polish and Features (Completion)
This final phase focuses on user experience and finalizing logic.

*   **Path Creation/Editing:** Implement screens and logic to allow the user to add new Paths and add multiple Steps to an existing Path.
*   **Data Validation:** Ensure required fields (e.g., Step Title) are validated before saving.
*   **State Management:** Thoroughly test the asynchronous flow: Creating Path -> Adding Steps -> Completing Steps -> Seeing Path as Complete.
