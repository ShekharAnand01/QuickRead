QuickRead
QuickRead is an Android application that delivers the latest news articles in various categories. Built with Kotlin, it provides a smooth user experience with offline access, favorite articles, and real-time updates. The app follows the MVVM architecture, utilizing Room for local storage and Retrofit for fetching news from an external API.

Features
Categorized News: Browse news from multiple categories such as technology, sports, health, and more.
Offline Mode: Save articles for offline reading using Room database.
Favorites: Mark articles as favorites to easily access them later.
Pagination: Seamless scrolling through paginated news articles.
Article Viewer: Read full articles within the app using WebView, with a built-in progress indicator.
Tech Stack
Kotlin: The core language used for development.
MVVM Architecture: Ensures a clean separation of concerns with ViewModel, LiveData, and Repository.
Room Database: Handles local storage, enabling offline reading of saved articles.
Retrofit: Manages network requests for fetching news from an external API.
Coroutines: For asynchronous programming, ensuring smooth performance during network operations.
Glide: Efficient image loading and caching for article thumbnails.
Installation
Clone the repository:

bash
Copy code
git clone https://github.com/ShekharAnand01/QuickRead.git
Open the project in Android Studio.

Sync the project with Gradle files.

Obtain an API key for the news service and add it to local.properties:

bash
Copy code
NEWS_API_KEY="your_api_key"
Build and run the project.

Screenshots


Contributing
Contributions are welcome! Feel free to fork the repository and submit pull requests.
