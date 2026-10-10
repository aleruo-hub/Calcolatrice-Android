# Calcolatrice Android — GitHub Actions

Progetto Android Java + XML per compilazione online, senza Android Studio.

## Compilare su GitHub

1. Estrai il contenuto dello ZIP.
2. Crea un repository GitHub (ramo `main`).
3. Carica il **contenuto** della cartella estratta nella radice del repository, inclusa `.github/workflows/build-apk.yml` (non caricare lo ZIP come singolo file).
4. Apri la scheda **Actions** -> **Compila APK Android** -> **Run workflow** -> **Run workflow**. Se richiesto, abilita GitHub Actions.
5. Quando il workflow termina con esito positivo, apri l'esecuzione e scarica **calcolatrice-debug-apk** nella sezione **Artifacts**.
6. Estrai lo ZIP scaricato da GitHub: trovi `app-debug.apk`, da trasferire e installare sullo smartphone Android.

## Requisiti e note

- Usa JDK 17, Gradle 8.11.1, AGP 8.9.2, Android compile SDK 35.
- L'APK generato e' una versione **debug**, firmata automaticamente con chiave debug. E' adatta a test/installazione personale, non alla pubblicazione sul Play Store.
- `applicationId`: `com.example.calcolatrice`.
- Versione minima: Android 6.0 (API 23).
- Il progetto non e' stato compilato nell'ambiente in cui e' stato creato questo archivio; il workflow effettua la vera compilazione e mostra eventuali errori.
- Se il browser non carica la cartella nascosta `.github`, crea manualmente `.github/workflows/build-apk.yml` nel repository tramite **Add file** -> **Create new file**.
