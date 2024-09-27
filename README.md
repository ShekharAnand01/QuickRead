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

<p align="center">
  <figure style="display:inline-block; margin: 20px;">
    <img src="https://github.com/user-attachments/assets/73cd1073-e043-45d2-b295-5d2b1e79126e" alt="Home Screen" width="300" height="600">
   
  </figure>
  <figure style="display:inline-block; margin: 20px;">
    <img src="https://github.com/user-attachments/assets/989c2160-b530-49e3-92b3-562ed91596f3" alt="Category Screen" width="300" height="600">
  </figure>
  <figure style="display:inline-block; margin: 20px;">
    <img src="https://github.com/user-attachments/assets/bc5a2e2e-ff94-4f56-af15-51b45016ba10" alt="Search Screen" width="300" height="600">
  </figure>
   <figure style="display:inline-block; margin: 20px;">
    <img src="https://github.com/user-attachments/assets/db329338-0984-4f54-b7e5-3cb2978e6165" alt="Favourites Screen" width="300" height="600">
  </figure>
   <figure style="display:inline-block; margin: 20px;">
    <img src="https://github.com/user-attachments/assets/66085540-fd43-4bfc-bdaf-a444a4441843" alt="Article Screen" width="300" height="600">
  </figure>
</p>




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

