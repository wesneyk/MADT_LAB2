# WordCounter
A simple Android application that analyzes user input text and calculates various text metrics.

## Features
- **Counts metrics**: Calculates the number of sentences, words, punctuation marks, and numbers in the provided text.
- **Generates test sentences**: A button to populate the input field with a random test sentence to quickly try out the app.
- **Calculation history**: Shows a running log of calculations performed during the current app runtime.
- **Input validation**: Notifies the user with a toast message when attempting to calculate an empty input field.

## Calculation Metrics & History Format
The core logic resides in the `TextMetrics` class, which calculates metrics as follows:
- **Sentences**: A sentence is considered to end with a `.`, `!`, or `?`. Several ending marks in a row count as one. If a text contains words but lacks a concluding punctuation mark, it is still counted as one sentence.
- **Words**: Words are identified as groups containing letters or digits.
- **Numbers**: Numbers are identified as groups containing exclusively digits.
- **Punctuation**: Any character that is non-alphanumeric and non-whitespace is counted as a punctuation mark.

**History Format:**
The calculation history is formatted as: `"[Input text]" => [Sentences], [Words], [Punctuation], [Numbers]`.

## Project Structure
The metric calculations are decoupled from the UI into a standalone class `TextMetrics` located at:
`app/src/main/java/com/example/wordcounter/TextMetrics.kt`.
Because this class does not depend on Android framework components, you can easily copy and reuse it in other Kotlin projects.

## Localization
UI strings and test sentences are stored in `app/src/main/res/values/strings.xml`.
To add a new language, create a new resource directory such as `values-[language_code]` (e.g., `values-ru` for Russian) inside `app/src/main/res/` and provide translations in a `strings.xml` file within that folder.

## Requirements
- **JDK**: 11
- **Android Gradle Plugin (AGP)**: 9.4.1 (Android Studio compatible)
- **minSdk**: 24
- **targetSdk**: 37

## Build and Run
1. Open the project folder in Android Studio.
2. Wait for Gradle to sync the dependencies.
3. Select an emulator or a connected physical Android device from the target device dropdown in the toolbar.
4. Click the green "Run" (play) button or press `Shift + F10` to build and launch the app.

## Usage
1. Launch the application.
2. Enter text into the input field, or tap **"Generate test sentence"** to automatically insert a sample string.
3. Tap **"Calculate"** to analyze the text.
4. The results will appear at the bottom of the screen in the history section. As you perform more calculations, the history log will accumulate.

## Author
[AUTHOR]

## License
[LICENSE]
