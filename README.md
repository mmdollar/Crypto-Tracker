# Crypto Tracker App

A modern Android application for tracking cryptocurrencies, built with Jetpack Compose.

## Running the App

1. Clone the repository
2. Open the project in Android Studio
3. Sync Gradle
4. Run the app on an emulator or physical device (minimum SDK 28)

## Architecture & Design Choices

### Architecture
- **MVI (Model-View-Intent)**: Unidirectional data flow pattern for predictable state management
- **Jetpack Compose**: Modern declarative UI framework
- **Hilt**: Dependency injection for better testability and modularity
- **Room**: Local database for offline-first data persistence
- **Retrofit + Moshi**: Network layer for API communication
- **Navigation Component 3**: Type-safe navigation between screens

### Design
- **Material 3**: Following Google's latest design guidelines

## How to Run Tests

### Using Android Studio
1. Right-click on the `test` directory
2. Select "Run Tests"

### Using Gradle (Command Line)
```bash
# Run unit tests
./gradlew test
```