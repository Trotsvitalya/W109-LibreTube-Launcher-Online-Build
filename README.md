# W109 LibreTube Launcher

Мінімальний Android HOME-лаунчер для Toscido W109. На екрані одна велика кнопка запуску LibreTube.

## Властивості
- Java, без сторонніх бібліотек.
- `minSdk 28` (Android 9/API 28), підходить для Android API 28.
- Не запитує INTERNET, аналітику, доступ до контактів або інші дозволи.
- Не вимикає системні служби та не змінює налаштування пристрою.
- Пакет LibreTube очікується як `com.github.libretube`.

## Збірка
Потрібні Android Studio або Android SDK, JDK 17 і Gradle-сумісне середовище.
1. Відкрий папку проєкту в Android Studio.
2. Дочекайся Gradle Sync.
3. Обери **Build > Build APK(s)**.
4. APK буде в `app/build/outputs/apk/debug/app-debug.apk`.

Альтернатива з установленим Gradle:
```bash
gradle assembleDebug
```

## Встановлення через ADB
Після збірки:
```powershell
.\adb.exe install -r app-debug.apk
```

Потім натисни Home на планшеті й вибери **Player Home**. Якщо система запропонує вибір постійного домашнього застосунку, вибери Player Home.

## Відновлення
Не вимикай `com.android.launcher3` до перевірки нового лаунчера.
Щоб повернути старий лаунчер, вибери його в системних налаштуваннях домашнього застосунку або видали Player Home:
```powershell
.\adb.exe uninstall com.w109.playerhome
```

## Примітки
Це вихідний проєкт, не готовий APK. Його ще потрібно зібрати та перевірити на пристрої. Лаунчер не є kiosk-lockdown: кнопка Home дозволяє повернутися на домашній екран, але це не блокує інші системні екрани.


## Онлайн-збірка без Android Studio (GitHub Actions)

У ZIP є workflow `.github/workflows/build.yml`, який збирає APK у GitHub Actions.

1. Створи репозиторій на https://github.com/new (можна Private).
2. Розпакуй ZIP на комп'ютері.
3. На сторінці репозиторію вибери **Add file → Upload files** і завантаж файли проєкту, включно з `.github/workflows/build.yml`. Переконайся, що workflow збережено саме за шляхом `.github/workflows/build.yml`.
4. Натисни **Commit changes**.
5. Відкрий вкладку **Actions**, вибери **Build Android APK**, натисни **Run workflow** (або збірка запуститься після push у main).
6. Відкрий успішний запуск workflow, у секції **Artifacts** завантаж `w109-player-home-apk`.
7. Розпакуй завантажений артефакт ZIP — усередині буде `app-debug.apk`.

Для приватного репозиторію GitHub Actions має безкоштовну квоту залежно від плану; публічні репозиторії використовують стандартні GitHub-hosted runners без оплати. Публічний репозиторій означає, що код буде видимий усім. Не завантажуй секрети або особисті дані.

Після отримання APK:
```powershell
.\adb.exe install -r app-debug.apk
```
