MVVM + Clean Architecture:
- UI Layer (Compose) → ViewModel → Repository → API/Database

## Voraussetzungen

- Android Studio Arctic Fox (2020.3.1) oder neuer
- Kotlin 1.9 oder neuer
- Gradle 8.0+
- Android SDK 24+ (min), 34+ (target)
- Internet-Verbindung

## Installation

### 1. Repository klonen

```bash
git clone https://github.com/Nico-T-Ihle/android-mobile-challenge.git
cd android-mobile-challenge
```

### 2. API-Key konfigurieren

#### Option A: Local Properties (empfohlen für Entwicklung)

Datei `local.properties` erstellen (root level):

```properties
sdk.dir=/path/to/android/sdk
MOVIE_API_KEY=your_api_key_here
MOVIE_API_BASE_URL=https://api.themoviedb.org/3/
```

#### Option B: Environment Variable

```bash
# Mac/Linux
export MOVIE_API_KEY="your_api_key"
export MOVIE_API_BASE_URL="https://api.themoviedb.org/3/"

# Windows PowerShell
$env:MOVIE_API_KEY="your_api_key"
$env:MOVIE_API_BASE_URL="https://api.themoviedb.org/3/"
```

#### Option C: BuildConfig (hardcoded)

In `build.gradle.kts`:

```kotlin
android {
    buildTypes {
        debug {
            buildConfigField("String", "MOVIE_API_KEY", "\"your_api_key\"")
            buildConfigField("String", "MOVIE_API_BASE_URL", "\"https://api.themoviedb.org/3/\"")
        }
    }
}
```

### 3. Dependencies installieren

```bash
./gradlew clean build
```

### 4. In Android Studio öffnen

```bash
# Mac/Linux
open -a "Android Studio" .

# Windows
start "Android Studio" .
```

## API-Setup

### TheMovieDB API verwenden

1. Account erstellen → [themoviedb.org](https://www.themoviedb.org/settings/api)
2. API Key generieren (kostenlos!)
3. Key in `local.properties` eintragen

### API-Endpoints

```kotlin
// GET /movie/popular
GET /3/movie/popular?api_key={key}&page={page}

// Response
{
  "results": [
    {
      "id": 550,
      "title": "Fight Club",
      "poster_path": "/...",
      "overview": "...",
      "vote_average": 8.5
    }
  ],
  "total_pages": 500
}

// GET /movie/{id}
GET /3/movie/{movieId}?api_key={key}
```

## Abhängigkeiten

```kotlin
dependencies {
    // Jetpack Compose
    implementation("androidx.compose.ui:ui:1.6.0")
    implementation("androidx.compose.material3:material3:1.1.0")
    implementation("androidx.activity:activity-compose:1.8.0")
    
    // Lifecycle & ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.0")
    
    // Navigation
    implementation("androidx.navigation:navigation-compose:2.7.0")
    
    // Networking
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.11.0")
    
    // Dependency Injection
    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-compiler:2.48")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")
    
    // Image Loading
    implementation("io.coil-kt:coil-compose:2.5.0")
    
    // Testing
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.mockito.kotlin:mockito-kotlin:5.1.0")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.6.0")
}
```

## Verwendung

### App starten

```bash
# Über Gradle
./gradlew installDebug

# Oder in Android Studio: Run > Run 'app'
```

### Test ausführen

```bash
# Unit Tests
./gradlew test

# Instrumented Tests (auf Device/Emulator)
./gradlew connectedAndroidTest
```

## Testing

### Unit Tests Beispiel

```kotlin
@Test
fun `movieListViewModel loads movies on init`() {
    val viewModel = MovieListViewModel(mockRepository)
    
    assertEquals(MovieListUiState.Loading, viewModel.uiState.value)
    // nach API call
    assertIsInstance<MovieListUiState.Success>(viewModel.uiState.value)
}
```

### Docker Testing (optional)

```dockerfile
FROM ubuntu:22.04

RUN apt-get update && apt-get install -y \
    openjdk-11-jdk git

ENV JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64

WORKDIR /app
COPY . .

RUN chmod +x gradlew
CMD ["./gradlew", "test", "--stacktrace"]
```

```bash
docker build -t movie-app-test .
docker run --rm movie-app-test
```

## Troubleshooting

### API Key invalid

Überprüfe:
- local.properties richtig konfiguriert?
- API Key von themoviedb.org gültig?
- Internet-Verbindung aktiv?

### Compose compilation error

Überprüfe versions in build.gradle.kts:

```kotlin
ext {
    compose_version = "1.6.0"
}
```

### Hilt-Fehler

```bash
# Clean & rebuild
./gradlew clean build --rerun-tasks
```

### Bilder laden nicht

```kotlin
// Überprüfe Coil-Konfiguration
Image(
    painter = rememberAsyncImagePainter("https://image.tmdb.org/..."),
    contentDescription = "Movie Poster",
    contentScale = ContentScale.Crop
)
```

## Weitere Ressourcen

- [Jetpack Compose Dokumentation](https://developer.android.com/compose)
- [MVVM Architecture Guide](https://developer.android.com/jetpack/guide)
- [TheMovieDB API Docs](https://developers.themoviedb.org/3)
- [Retrofit Documentation](https://square.github.io/retrofit/)
- [Hilt Dependency Injection](https://developer.android.com/training/dependency-injection/hilt-android)

## Challenge Details

- Dauer: 3 Tage
- Basis: Gegebenes Design/Mockup
- Tech Stack: Kotlin + Jetpack Compose
- Komplexität: Medium (API + Navigation)

## Contributing

Issues & Pull Requests sind willkommen!

```bash
git checkout -b feature/xyz
git commit -am "Add xyz"
git push origin feature/xyz
# PR erstellen
```

## License

MIT License — siehe [LICENSE](LICENSE)

## Autor

Nico T. Ihle
- GitHub: [@Nico-T-Ihle](https://github.com/Nico-T-Ihle)

---

## Project Stats

- Language: Kotlin 100%
- Lines of Code: ~2000 LOC
- Dependencies: 15+
- Min SDK: 24
- Target SDK: 34
