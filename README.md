# Android-Starter-Project
This is an android project template that I use in all new android projects

## Usage
Watch this video for steps 3, 4, 5 https://www.youtube.com/watch?v=EEDRt2X-Q90 
1. Download / clone the project
2. Open with android studio
3. Change the applicationId in the app's build.gradle to your package name 
```xml
  defaultConfig {
          applicationId "me.gilo.starter"
          minSdkVersion 19
          targetSdkVersion 28
          versionCode 1
          versionName "1.0"
          testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
      }
```

4. Similarly change your manifest.xml package name to your
5. Rename the folders inside of src/java folder to match your package name
6. Change the app name under strings.xml





## Checklist
Trello board here - https://trello.com/b/lYzZ08de/android-starter-project
This is a least of components to implement, drawn from the most common components appearing across all apps I build

- [x] MVVM
  - [x] data
  - [x] repositories
  - [x] viewmodels
  - [x] ui
- [x] REST
  - [x] API definition class
  - [x] Rest Adapter
  - [x] Authentication
- [x] Room
  - [x] Dao
- [x] DI
  - [x] Viewmodels
  - [x] Repo
  - [x] Retrofit
  - [x] Room
- [x] UI
  - [x] Login and Sign up Activity
  - [x] Tab activity
  - [x] Bottom Navigation activity
  - [x] Fonts
  - [x] App wide styling
 
