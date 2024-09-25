# QuickRead

QuickRead is an Android application that delivers the latest news articles in various categories. Built with Kotlin, it provides a smooth user experience with offline access, favorite articles, and real-time updates. The app follows the MVVM architecture, utilizing Room for local storage and Retrofit for fetching news from an external API.

## Features

- **Categorized News**: Browse news from multiple categories such as technology, sports, health, and more.
- **Offline Mode**: Save articles for offline reading using Room database.
- **Favorites**: Mark articles as favorites to easily access them later.
- **Pagination**: Seamless scrolling through paginated news articles.
- **Article Viewer**: Read full articles within the app using WebView, with a built-in progress indicator.

## Tech Stack

- **Kotlin**: The core language used for development.
- **MVVM Architecture**: Ensures a clean separation of concerns with ViewModel, LiveData, and Repository.
- **Room Database**: Handles local storage, enabling offline reading of saved articles.
- **Retrofit**: Manages network requests for fetching news from an external API.
- **Coroutines**: For asynchronous programming, ensuring smooth performance during network operations.
- **Glide**: Efficient image loading and caching for article thumbnails.

## Screenshots

*(Include relevant screenshots here)*

## Installation

1. Clone the repository:
    ```bash
    git clone https://github.com/ShekharAnand01/QuickRead.git
    ```

2. Open the project in Android Studio.

3. Sync the project with Gradle files.

4. Build and run the project.

## Contributing

Contributions are welcome! Feel free to fork the repository and submit pull requests.

